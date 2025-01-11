package de.robiasto.fragment_annotation_core.annotation.post_init.set_fragment_path;


import de.robiasto.fragment_annotation_core.interfaces.fragment.FragmentDtoInterface;
import de.robiasto.fragment_annotation_core.interfaces.fragment.post_initial.FragmentPostInitialMapperInterface;

import java.lang.annotation.Annotation;

@SuppressWarnings("java:S6201")
public class SetFragmentPathMapper implements FragmentPostInitialMapperInterface {

    @Override
    public void addAnnotation(FragmentDtoInterface fragmentDto, Annotation annotation) {
        if (fragmentDto.fragmentInterface() instanceof SetFragmentPathInterface fragmentInterface) {
            this.getPath(fragmentInterface, annotation);
        }

        throw new SetFragmentPathException("Fragment must implement SetFragmentPathInterface.");
    }

    private String getPath(SetFragmentPathInterface fragmentDto, Annotation annotation) {
        if (annotation instanceof SetTypeFragmentPath setTypeFragmentPath) {
            fragmentDto.setPath(setTypeFragmentPath.path(), setTypeFragmentPath.functionName());
        }

        if (annotation instanceof SetAnnotationFragmentPath setAnnotationFragmentPath) {
            fragmentDto.setPath(setAnnotationFragmentPath.path(), setAnnotationFragmentPath.functionName());
        }

        throw new SetFragmentPathException("Fragment must implement setTypeFragmentPath or SetTypeFragmentPath.");
    }
}
