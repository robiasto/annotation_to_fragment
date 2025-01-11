package de.robiasto.fragment_annotation_core.integration.field.display;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface DisplayAnnotation {
    String attributeToProcess() default "displayType";
}
