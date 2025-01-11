package de.robiasto.app.infrastructure.fragment.form;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface FileConfiguration {
    String alt() default "";

    String translation() default "";

    String fileType() default "jpg";
}
