package de.robiasto.app.infrastructure.fragment.form.fragment.type.input;

import de.robiasto.app.infrastructure.fragment.form.FormField;
import de.robiasto.app.infrastructure.fragment.form.fragment.FormFieldFragment;
import de.robiasto.fragment_annotation_core.integration.mapper.StringMapper;
import de.robiasto.fragment_annotation_core.interfaces.field.FieldDtoInterface;
import de.robiasto.fragment_annotation_core.interfaces.fragment.FragmentInterface;
import de.robiasto.fragment_annotation_core.interfaces.fragment.type.FragmentTypeMapperInterface;
import de.robiasto.fragment_annotation_core.utilities.templates.AbstractLocalFragment;
import lombok.Getter;

/**
 * The InputFragment class represents a form input fragment in an application.
 * <p>
 * It extends the AbstractFragment class and implements the FragmentInterface interface.
 * <p>
 * Properties:
 * - value: The value of the input field.
 * - name: The name of the input field.
 * - placeholder: The placeholder text of the input field.
 * - type: The type of the input field.
 * - mandatory: Specifies whether the input field is mandatory.
 * <p>
 * Constructors:
 * - InputFragment(String name, String value, String type, String placeholder, boolean mandatory): Initializes an InputFragment object with the specified properties.
 * <p>
 * Methods:
 * - getValue(): Returns the value of the input field.
 * - getName(): Returns the name of the input field.
 * - getPlaceholder(): Returns the placeholder text of the input field.
 * - getType(): Returns the type of the input field.
 * - isMandatory(): Returns whether the input field is mandatory.
 * <p>
 * Inner class:
 * - Mapper: Implements the FragmentTypeMapperInterface interface and is responsible for mapping a FieldDtoInterface object to an InputFragment object.
 */
public class InputFragment extends AbstractLocalFragment {
    @Getter
    protected final String value;

    @Getter
    protected final String name;

    @Getter
    protected final String placeholder;

    @Getter
    protected final String type;

    @Getter
    protected final boolean mandatory;

    public InputFragment(String name, String value, String type, String placeholder, boolean mandatory) {
        this.value = value;
        this.name = name;
        this.type = type;
        this.placeholder = placeholder;
        this.mandatory = mandatory;
    }

    public static class Mapper implements FragmentTypeMapperInterface {
        protected final String type;
        private final StringMapper valueMapper;

        public Mapper(StringMapper mapper, String type) {
            this.valueMapper = mapper;
            this.type = type;
        }

        @Override
        public FragmentInterface getAnnotationMapper(FieldDtoInterface fragmentDto) {
            FormField formFieldAnnotation = FormFieldFragment.Mapper.getAnnotation(fragmentDto.field());

            return new InputFragment(
                    fragmentDto.field().getName(),
                    this.valueMapper.getValue(fragmentDto),
                    type,
                    formFieldAnnotation.placeholder(),
                    formFieldAnnotation.isMandatory()
            );
        }
    }
}
