package de.robiasto.fragment_annotation_core.interfaces.fragment.type;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation used to configure a mapping for fragment types.
 * The annotation is used on other annotations and specifies the attribute to map.
 * By default, the attribute to map is "fragmentType".
 * <p>
 * Example usage:
 *
 * @Target(ElementType.TYPE)
 * @Retention(RetentionPolicy.RUNTIME)
 * @FragmentTypeConfiguration(attributeToMap = "type")
 * public @interface MyAnnotation {
 * String type();
 * }
 * @since 1.0
 */
@Target({ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface FragmentTypeConfiguration {
    String attributeToMap() default "fragmentType";
}
