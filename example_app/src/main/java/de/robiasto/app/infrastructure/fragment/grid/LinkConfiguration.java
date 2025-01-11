package de.robiasto.app.infrastructure.fragment.grid;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LinkConfiguration {
    String text() default "";

    String translation() default "";

    LinkType type() default LinkType.LINK;
}
