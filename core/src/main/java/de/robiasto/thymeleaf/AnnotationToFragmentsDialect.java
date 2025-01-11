package de.robiasto.thymeleaf;

import org.thymeleaf.dialect.AbstractProcessorDialect;
import org.thymeleaf.processor.IProcessor;
import org.thymeleaf.standard.StandardDialect;

import java.util.HashSet;
import java.util.Set;

/**
 * The AnnotationToFragmentsDialect class is a custom implementation of the AbstractProcessorDialect class.
 * It represents a Thymeleaf dialect that provides a set of processors for processing Thymeleaf templates with
 * annotation-based fragments. This dialect enables the usage of the @value attribute in Thymeleaf templates
 * to replace fragment interface attributes with the corresponding fragment values.
 * <p>
 * This class has a DIALECT_NAME constant representing the name of the dialect, and a PREFIX constant representing
 * the prefix used in the dialect for this processor. It also has a constructor that initializes the super class
 * by passing the DIALECT_NAME, PREFIX, and processor precedence.
 * <p>
 * The getProcessors method returns a Set of IProcessor instances, which includes an instance of the
 * ReplaceFragmentInterfaceAttributeProcessor class. This processor is responsible for replacing attributes of a
 * fragment interface in a Thymeleaf template.
 * <p>
 *
 * @see AbstractProcessorDialect
 * @see ReplaceFragmentInterfaceAttributeProcessor
 */
public class AnnotationToFragmentsDialect extends AbstractProcessorDialect {
    private static final String DIALECT_NAME = "Annotation to Fragments Dialect";

    private static final String PREFIX = "af";

    public AnnotationToFragmentsDialect() {
        super(
                AnnotationToFragmentsDialect.DIALECT_NAME,
                AnnotationToFragmentsDialect.PREFIX,
                StandardDialect.PROCESSOR_PRECEDENCE
        );
    }

    @Override
    public Set<IProcessor> getProcessors(String dialectPrefix) {
        final Set<IProcessor> processors = new HashSet<>();
        processors.add(new ReplaceFragmentInterfaceAttributeProcessor(dialectPrefix));

        return processors;
    }
}
