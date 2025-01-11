package de.robiasto.fragment_annotation_core.interfaces.field;

/**
 * The FieldProcessorInterface is an interface that defines methods for processing field data. Classes that implement this interface can perform operations on an array of {@link FieldDtoInterface} objects.
 * <p>
 * The two methods defined in this interface are:
 * - {@code process(FieldDtoInterface[] fields)}: This method takes an array of {@link FieldDtoInterface} objects as input and returns an array of {@link FieldDtoInterface} objects
 * after processing. Implementing classes should define the processing logic according to their requirements.
 * - {@code getPretence()}: This method returns an integer value representing the precedence of the field processor. The field processors are sorted in ascending order of their precedence
 * values during processing. Implementing classes should provide a unique precedence value to determine the processing order.
 * <p>
 * The implementing classes should also provide the necessary documentation for their specific usage and functionality.
 */
public interface FieldProcessorInterface {
    FieldDtoInterface[] process(FieldDtoInterface[] fields);

    int getPretence();
}
