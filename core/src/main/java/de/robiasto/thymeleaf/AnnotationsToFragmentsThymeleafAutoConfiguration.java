package de.robiasto.thymeleaf;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;

/**
 * The `AnnotationsToFragmentsThymeleafAutoConfiguration` class is an auto-configuration class that configures the Thymeleaf
 * dialect `AnnotationToFragmentsDialect` with the necessary beans.
 * <p>
 * The `AnnotationToFragmentsDialect` class is a custom Thymeleaf dialect that provides a set of processors for processing
 * Thymeleaf templates with annotation-based fragments. This dialect enables the usage of the `@value` attribute in Thymeleaf
 * templates to replace fragment interface attributes with the corresponding fragment values.
 * <p>
 * The `AnnotationToFragmentsThymeleafAutoConfiguration` class is conditionally enabled when the application is a web application.
 * It is annotated with `@AutoConfiguration`, which allows it to be automatically configured as part of the Spring Boot
 * auto-configuration process.
 * <p>
 * The class defines a `@Bean` method `annotationToFragmentDialect()` which creates and returns an instance of the
 * `AnnotationToFragmentsDialect`. This bean is then automatically registered with Thymeleaf as a dialect by Spring Boot's
 * auto-configuration mechanism.
 * <p>
 * Note that the class does not provide examples or usage instructions.
 * <p>
 *
 * @see AnnotationToFragmentsDialect
 * @since 1.0.0
 */
@AutoConfiguration
@ConditionalOnWebApplication
public class AnnotationsToFragmentsThymeleafAutoConfiguration {
    @Bean
    public AnnotationToFragmentsDialect annotationToFragmentDialect() {
        return new AnnotationToFragmentsDialect();
    }
}
