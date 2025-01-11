package de.robiasto.app.infrastructure.fragment.form.fragment.type.file;

import de.robiasto.app.infrastructure.fragment.form.FileConfiguration;
import de.robiasto.app.infrastructure.fragment.form.FormField;
import de.robiasto.app.infrastructure.fragment.form.fragment.FormFieldFragment;
import de.robiasto.app.infrastructure.fragment.grid.fragments.image.ImageFragment;
import de.robiasto.app.infrastructure.fragment.grid.fragments.link.MissingLinkConfigurationException;
import de.robiasto.fragment_annotation_core.interfaces.field.FieldDtoInterface;
import de.robiasto.fragment_annotation_core.interfaces.fragment.FragmentInterface;
import de.robiasto.fragment_annotation_core.interfaces.fragment.type.FragmentTypeMapperInterface;
import de.robiasto.fragment_annotation_core.utilities.FieldUtility;
import de.robiasto.fragment_annotation_core.utilities.templates.AbstractLocalFragment;
import lombok.AllArgsConstructor;
import org.springframework.util.Assert;

import java.text.MessageFormat;
import java.util.Optional;

@AllArgsConstructor
public class FileFragment extends AbstractLocalFragment {
    public final String name;

    public final String value;

    public final String alt;

    public final boolean mandatory;

    public final String fileType;

    public final ImageFragment image;

    public static class Mapper implements FragmentTypeMapperInterface {

        @Override
        public FragmentInterface getAnnotationMapper(FieldDtoInterface fragmentDto) {
            FormField formFieldAnnotation = FormFieldFragment.Mapper.getAnnotation(fragmentDto.field());
            FileConfiguration config = this.getConfiguration(fragmentDto);
            ImageFragment imageFragment = new ImageFragment(this.getValue(fragmentDto), config.alt());

            imageFragment.addCssClasses("w-32 h-32");

            return new FileFragment(
                    fragmentDto.field().getName(),
                    this.getValue(fragmentDto),
                    config.alt(),
                    formFieldAnnotation.isMandatory(),
                    config.fileType(),
                    imageFragment
            );
        }

        private FileConfiguration getConfiguration(FieldDtoInterface fragmentDto) {
            return Optional.ofNullable(fragmentDto.field().getAnnotation(FileConfiguration.class))
                                               .orElseThrow(() -> new MissingLinkConfigurationException(MessageFormat.format(
                                                       "@{0} is not Implemented in {1}.\n"
                                                               + "Add @{0} annotation to attribute {1}.",
                                                       FileConfiguration.class.getSimpleName(),
                                                       fragmentDto.field().getName()
                                               )));
        }

        private String getValue(FieldDtoInterface fragmentDto) {
            Object value = FieldUtility.getObject(fragmentDto);

            if(value == null) {
                return null;
            }

            Assert.isInstanceOf(String.class, FieldUtility.getObject(fragmentDto), "File fragment value must be string.");

            return (String) FieldUtility.getObject(fragmentDto);
        }
    }
}
