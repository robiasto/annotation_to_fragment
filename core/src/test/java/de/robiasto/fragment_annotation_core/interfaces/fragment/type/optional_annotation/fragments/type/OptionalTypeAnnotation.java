package de.robiasto.fragment_annotation_core.interfaces.fragment.type.optional_annotation.fragments.type;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface OptionalTypeAnnotation {
    String value();
}
