package de.robiasto.app.infrastructure.fragment.grid;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface FormConfiguration {
    String gridCols() default "";

    String cancelUrl();

    boolean editable() default true;
}
