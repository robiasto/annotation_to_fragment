package de.robiasto.fragment_annotation_core.annotation.post_init.add_css_class;

import de.robiasto.fragment_annotation_core.interfaces.fragment.FragmentDtoInterface;
import de.robiasto.fragment_annotation_core.interfaces.fragment.post_initial.FragmentPostInitialMapperInterface;

import java.lang.annotation.Annotation;

public class AddCssClassMapper implements FragmentPostInitialMapperInterface {
    @Override
    public void addAnnotation(FragmentDtoInterface fragmentDto, Annotation annotation) {
        if (fragmentDto.fragmentInterface() instanceof CssClassFragmentInterface cssClassFragmentInterface) {
            cssClassFragmentInterface.addCssClasses(this.getCssClasses(annotation));

            return;
        }

        throw new AddCssAnnotationException("Fragment must implement CssClassFragmentInterface.");
    }

    private String getCssClasses(Annotation annotation) {
        if (annotation instanceof AddCssClassOnAnnotation addCssClassOnAnnotation) {
            return addCssClassOnAnnotation.value();
        }

        if (annotation instanceof AddCssClassOnType addCssClassOnType) {
            return addCssClassOnType.value();
        }

        return "";
    }
}
