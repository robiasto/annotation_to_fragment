package de.robiasto.fragment_annotation_core.annotation.post_init.set_fragment_path;

import de.robiasto.fragment_annotation_core.interfaces.fragment.ProcessType;
import de.robiasto.fragment_annotation_core.interfaces.fragment.post_initial.FragmentPostInitialConfiguration;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@FragmentPostInitialConfiguration(
        mapper = SetFragmentPathMapper.class,
        processType = ProcessType.ANNOTATION
)
public @interface SetAnnotationFragmentPath {
    String path();

    String functionName();
}
