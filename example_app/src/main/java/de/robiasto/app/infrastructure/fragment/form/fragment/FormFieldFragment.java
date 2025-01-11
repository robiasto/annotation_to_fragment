package de.robiasto.app.infrastructure.fragment.form.fragment;

import de.robiasto.app.infrastructure.fragment.form.FormField;
import de.robiasto.fragment_annotation_core.interfaces.fragment.FragmentDtoInterface;
import de.robiasto.fragment_annotation_core.interfaces.fragment.FragmentInterface;
import de.robiasto.fragment_annotation_core.interfaces.fragment.annotation.FragmentAnnotationMapperInterface;
import de.robiasto.fragment_annotation_core.utilities.templates.AbstractLocalFragment;
import lombok.Getter;

import java.lang.reflect.Field;
import java.text.MessageFormat;
import java.util.Optional;

/**
 * The FormFieldFragment class represents a fragment used for form fields in a web application.
 * It extends the AbstractFragment class and implements the FragmentInterface interface.
 * <p>
 * The FormFieldFragment class has the following properties:
 * - field: the FragmentInterface representing the form field
 * - label: the label of the form field
 * - name: the name of the form field
 * - translated: a boolean indicating whether the label is translated
 * - displayLabel: a boolean indicating whether to display the label
 * - isMandatory: a boolean indicating whether the form field is mandatory
 * <p>
 * The FormFieldFragment class provides the following methods:
 * - getField(): returns the FragmentInterface representing the form field
 * - getName(): returns the name of the form field
 * - getLabel(): returns the label of the form field
 * - isTranslated(): returns whether the label is translated
 * - isDisplayLabel(): returns whether to display the label
 * - isMandatory(): returns whether the form field is mandatory
 * <p>
 * The FormFieldFragment class also includes a static nested class Mapper that implements the FragmentAnnotationMapperInterface interface.
 * The Mapper class is responsible for mapping a FragmentDtoInterface object to a FormFieldFragment object based on the FormField annotation.
 * <p>
 * This class is primarily used for configuring and mapping form fields in a web application.
 */
@Getter
public class FormFieldFragment extends AbstractLocalFragment {

    private final String label;

    private final String name;

    private final boolean translated;

    private final boolean displayLabel;

    private final FragmentInterface field;

    private final boolean isMandatory;

    public FormFieldFragment(
            String name,
            String label,
            boolean translated,
            boolean displayLabel,
            FormField.Colspan colspan,
            FragmentInterface field,
            boolean isMandatory
    ) {
        this.name = name;
        this.label = label;
        this.field = field;
        this.displayLabel = displayLabel;
        this.translated = translated;
        this.addCssClasses(colspan.getTailwindClass());
        this.isMandatory = isMandatory;
    }

    public static class Mapper implements FragmentAnnotationMapperInterface {
        public static FormField getAnnotation(Field field) {
            return Optional.ofNullable(field.getAnnotation(FormField.class))
                           .orElseThrow(() -> new MissingFormFieldConfigurationException(
                                   MessageFormat.format(
                                           "@{0} is not Implemented in {1}.\n"
                                                   + "Add @{0} annotation to attribute {1}.",
                                           FormField.class.getSimpleName(),
                                           field.getName()
                                   )));
        }

        @Override
        public FragmentInterface getFragment(FragmentDtoInterface fragmentDto) {
            FormField formField = getAnnotation(fragmentDto.field());

            return new FormFieldFragment(
                    fragmentDto.field().getName(),
                    formField.label(),
                    formField.labelTranslated(),
                    formField.fragmentType().isDisplayLabel(),
                    formField.colSpan(),
                    fragmentDto.fragmentInterface(),
                    formField.isMandatory()
            );
        }
    }
}
