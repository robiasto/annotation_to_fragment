package de.robiasto.fragment_annotation_core.interfaces.fragment.annotation.fragments;

import de.robiasto.fragment_annotation_core.interfaces.fragment.FragmentDtoInterface;
import de.robiasto.fragment_annotation_core.interfaces.fragment.FragmentInterface;
import de.robiasto.fragment_annotation_core.interfaces.fragment.annotation.FragmentAnnotationMapperInterface;

public class WithHeadlineFragment implements FragmentInterface {

    protected final FragmentInterface fragment;

    protected final String headlineText;

    public WithHeadlineFragment(FragmentInterface fragment, String headlineText) {

        this.fragment = fragment;
        this.headlineText = headlineText;
    }

    public FragmentInterface getFragment() {
        return fragment;
    }

    public String getHeadlineText() {
        return headlineText;
    }

    @Override
    public String getPath() {
        return "fragments/annotation/withHeadlineFragment.html :: withHeadlineFragment";
    }

    public static class FragmentMapper implements FragmentAnnotationMapperInterface {
        @Override
        public FragmentInterface getFragment(FragmentDtoInterface fragmentDto) {
            return new WithHeadlineFragment(
                    fragmentDto.fragmentInterface(),
                    fragmentDto.field().getAnnotation(WithHeadlineAnnotation.class).headlineText()
            );
        }
    }
}
