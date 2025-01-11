package de.robiasto.app.infrastructure.fragment.form;

import de.robiasto.app.infrastructure.fragment.form.fragment.FormFieldFragment;
import de.robiasto.fragment_annotation_core.integration.field.display.DisplayAnnotation;
import de.robiasto.fragment_annotation_core.integration.field.sorting.SortingAnnotation;
import de.robiasto.fragment_annotation_core.interfaces.DisplayType;
import de.robiasto.fragment_annotation_core.interfaces.fragment.annotation.FragmentAnnotationConfiguration;
import de.robiasto.fragment_annotation_core.interfaces.fragment.type.FragmentTypeConfiguration;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This class represents an annotation used to define form fields.
 * The annotation is used to configure various properties of a form field, such as its fragment type,
 * sorting order, label, placeholder, and other attributes.
 * The annotation can be placed on class fields.
 * <p>
 * This annotation is annotated with the following meta-annotations:
 * - @Target(ElementType.FIELD): Indicates that this annotation can be used on class fields.
 * - @Retention(RetentionPolicy.RUNTIME): Indicates that this annotation should be retained at runtime.
 * - @SortingAnnotation: A custom annotation that specifies the attribute to process for sorting.
 * - @FragmentTypeConfiguration: A custom annotation that configures a mapping for fragment types.
 * - @FragmentAnnotationConfiguration: A custom annotation that configures a mapping between this annotation and a class that implements FragmentAnnotationMapperInterface.
 * <p>
 * The FormField annotation has the following properties:
 * - fragmentType: Specifies the fragment type of the form field.
 * - sorting: Specifies the sorting order of the form field.
 * - label (Optional): Specifies the label of the form field.
 * - placeholder (Optional): Specifies the placeholder of the form field.
 * - labelTranslated (Optional, default: true): Specifies whether the label is translated.
 * - placeholderTranslated (Optional, default: true): Specifies whether the placeholder is translated.
 * - isMandatory (Optional, default: true): Specifies whether the form field is mandatory.
 * - colSpan (Optional, default: Colspan.FULL): Specifies the column span of the form field.
 * <p>
 * The Colspan enum within the FormField annotation defines two possible values for the colSpan property:
 * - HALF: Specifies a column span of "sm:col-span-3".
 * - FULL: Specifies a column span of "sm:col-span-6".
 * <p>
 * This class also includes a static class FormFieldFragment and a static class FormFieldFragment.Mapper.
 * FormFieldFragment is a class that represents a form field fragment and provides methods to access its properties.
 * FormFieldFragment.Mapper is a class that implements the FragmentAnnotationMapperInterface and is responsible for mapping a FragmentDtoInterface object to a FormFieldFragment object
 * based on the FormField annotation.
 * <p>
 * This class is primarily used for configuration and mapping of form fields in a web application.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@SortingAnnotation
@DisplayAnnotation
@FragmentTypeConfiguration
@FragmentAnnotationConfiguration(FormFieldFragment.Mapper.class)
public @interface FormField {
    FormFieldFragmentTypes fragmentType();

    int sorting();

    String label() default "";

    String placeholder() default "";

    DisplayType[] displayType() default {DisplayType.ALL};

    boolean labelTranslated() default true;

    boolean placeholderTranslated() default true;

    boolean isMandatory() default true;

    Colspan colSpan() default Colspan.FULL;

    enum Colspan {
        HALF("sm:col-span-3"),
        FULL("sm:col-span-6");

        private final String tailwindClass;

        Colspan(String tailwindClass) {
            this.tailwindClass = tailwindClass;
        }

        public String getTailwindClass() {
            return tailwindClass;
        }

    }
}
