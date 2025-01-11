package de.robiasto.app.infrastructure.fragment.grid.fragments.form;

import de.robiasto.app.infrastructure.fragment.grid.FormConfiguration;
import de.robiasto.app.infrastructure.fragment.grid.LinkType;
import de.robiasto.app.infrastructure.fragment.grid.fragments.link.LinkFragment;
import de.robiasto.app.infrastructure.fragment.grid.fragments.link.MissingLinkConfigurationException;
import de.robiasto.fragment_annotation_core.integration.mapper.CompositionMapper;
import de.robiasto.fragment_annotation_core.interfaces.AnnotationFormViewInterface;
import de.robiasto.fragment_annotation_core.interfaces.field.FieldDtoInterface;
import de.robiasto.fragment_annotation_core.interfaces.fragment.FragmentInterface;
import de.robiasto.fragment_annotation_core.interfaces.fragment.type.FragmentTypeMapperInterface;
import de.robiasto.fragment_annotation_core.utilities.FieldUtility;
import de.robiasto.fragment_annotation_core.utilities.templates.AbstractFragment;
import org.springframework.util.Assert;

import java.text.MessageFormat;
import java.util.Optional;

/**
 * The FormFragment class represents a form component in an application. It extends the AbstractFragment class and implements the FragmentInterface interface.
 * <p>
 * The FormFragment class has the following properties:
 * - formFields: An array of FragmentInterface objects representing the form fields.
 * - action: A string representing the action URL for the form.
 * - cancelLink: A LinkFragment object representing the cancel link for the form.
 * - actionLink: A LinkFragment object representing the action link for the form.
 * <p>
 * The FormFragment class has the following methods:
 * - constructor: Creates a new FormFragment object with the specified properties.
 * - getFormFields: Returns the form fields.
 * - getAction: Returns the action URL.
 * - getCancelLink: Returns the cancel link.
 * - getActionLink: Returns the action link.
 * <p>
 * The FormFragment class also contains a static nested class called Mapper, which implements the FragmentTypeMapperInterface interface. This class is used for mapping field data
 * to FormFragment objects.
 * The Mapper class has the following methods:
 * - constructor: Creates a new Mapper object with the specified CompositionMapper object.
 * - getAnnotationMapper: Returns a FormFragment object based on the given field data.
 * - getCancelLink: Returns a LinkFragment object representing the cancel link for the form.
 * - getActionLink: Returns a LinkFragment object representing the action link for the form.
 * - getFormConfiguration: Returns the FormConfiguration annotation for the given field.
 * - getAnnotationFormView: Returns the AnnotationFormViewInterface object for the given field.
 */
public class FormFragment extends AbstractFragment {
    protected final FragmentInterface[] formFields;

    protected final String action;

    private final LinkFragment cancelLink;

    private final LinkFragment actionLink;

    private final boolean editable;

    public FormFragment(
            FragmentInterface[] formFields,
            String action,
            LinkFragment cancelLink,
            LinkFragment actionLink,
            boolean editable
    ) {
        super();
        this.formFields = formFields;
        this.action = action;
        this.cancelLink = cancelLink;
        this.actionLink = actionLink;
        this.editable = editable;
    }

    public FragmentInterface[] getFormFields() {
        return formFields;
    }

    public String getAction() {
        return action;
    }

    public LinkFragment getCancelLink() {
        return cancelLink;
    }

    public LinkFragment getActionLink() {
        return actionLink;
    }

    public boolean isEditable() {
        return editable;
    }

    public static class Mapper implements FragmentTypeMapperInterface {
        private final CompositionMapper compositionMapper;

        public Mapper(CompositionMapper compositionMapper) {
            this.compositionMapper = compositionMapper;
        }

        @Override
        public FragmentInterface getAnnotationMapper(FieldDtoInterface fieldDto) {
            FormConfiguration formConfig = this.getFormConfiguration(fieldDto);
            AnnotationFormViewInterface formViewInterface = this.getAnnotationFormView(fieldDto);

            return new FormFragment(
                    this.compositionMapper.getValue(fieldDto),
                    formViewInterface.getAction(),
                    this.getCancelLink(formConfig),
                    new LinkFragment("", "save", LinkType.SUBMIT, null),
                    formConfig.editable()
            );
        }

        private LinkFragment getCancelLink(FormConfiguration formConfig) {
            LinkFragment cancelLink = new LinkFragment("", "back", LinkType.BUTTON, formConfig.cancelUrl());
            cancelLink.addCssClasses("mr-2");

            return cancelLink;
        }

        private FormConfiguration getFormConfiguration(FieldDtoInterface fieldDto) {
            return Optional.ofNullable(fieldDto.field().getAnnotation(FormConfiguration.class))
                           .orElseThrow(() -> new MissingLinkConfigurationException(MessageFormat.format(
                                   "@{0} is not Implemented in {1}.\n"
                                           + "Add @{0} annotation to attribute {1}.",
                                   FormConfiguration.class.getSimpleName(),
                                   fieldDto.field().getName()
                           )));
        }

        private AnnotationFormViewInterface getAnnotationFormView(FieldDtoInterface fieldDto) {
            Object model = FieldUtility.getObject(fieldDto);
            Assert.isInstanceOf(
                    AnnotationFormViewInterface.class,
                    model,
                    "Model must be an instance of " + AnnotationFormViewInterface.class.getName() + ".");
            return (AnnotationFormViewInterface) model;
        }
    }
}
