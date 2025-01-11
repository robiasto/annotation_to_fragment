package de.robiasto.fragment_annotation_core.annotation.post_init.add_css_class;

import de.robiasto.fragment_annotation_core.interfaces.fragment.ProcessType;
import de.robiasto.fragment_annotation_core.interfaces.fragment.post_initial.FragmentPostInitialConfiguration;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@FragmentPostInitialConfiguration(
        mapper = AddCssClassMapper.class,
        processType = ProcessType.ANNOTATION
)
public @interface AddCssClassOnAnnotation {
    String value();
}
