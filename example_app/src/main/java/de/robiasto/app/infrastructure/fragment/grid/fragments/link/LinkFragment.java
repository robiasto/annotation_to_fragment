package de.robiasto.app.infrastructure.fragment.grid.fragments.link;

import de.robiasto.app.infrastructure.fragment.grid.LinkConfiguration;
import de.robiasto.app.infrastructure.fragment.grid.LinkType;
import de.robiasto.app.infrastructure.fragment.grid.fragments.link.confirmation.ConfirmationFragment;
import de.robiasto.fragment_annotation_core.integration.mapper.StringMapper;
import de.robiasto.fragment_annotation_core.interfaces.field.FieldDtoInterface;
import de.robiasto.fragment_annotation_core.interfaces.fragment.FragmentInterface;
import de.robiasto.fragment_annotation_core.interfaces.fragment.type.FragmentTypeMapperInterface;
import de.robiasto.fragment_annotation_core.utilities.templates.AbstractLocalFragment;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.text.MessageFormat;
import java.util.Optional;

@Getter
public class LinkFragment extends AbstractLocalFragment {
    protected final String text;

    protected final String translation;

    protected final String url;

    public LinkFragment(String text, String translation, LinkType type, String url) {
        this.text = text;
        this.translation = translation;
        this.url = url;
        this.fragmentFunction = type.getFragmentFunction();
    }

    @AllArgsConstructor
    public static class Mapper implements FragmentTypeMapperInterface {
        final StringMapper valueMapper;

        @Override
        public FragmentInterface getAnnotationMapper(FieldDtoInterface fragmentDto) {
            LinkConfiguration config = Optional.ofNullable(fragmentDto.field().getAnnotation(LinkConfiguration.class))
                                               .orElseThrow(() -> new MissingLinkConfigurationException(MessageFormat.format(
                                                       "@{0} is not Implemented in {1}.\n"
                                                               + "Add @{0} annotation to attribute {1}.",
                                                       LinkConfiguration.class.getSimpleName(),
                                                       fragmentDto.field().getName()
                                               )));

            FragmentInterface fragment = new LinkFragment(
                    config.text(),
                    config.translation(),
                    config.type(),
                    this.valueMapper.getValue(fragmentDto)
            );

            if(config.type().isConfirmation()) {
                return new ConfirmationFragment(fragment);
            }

            return fragment;
        }
    }
}
