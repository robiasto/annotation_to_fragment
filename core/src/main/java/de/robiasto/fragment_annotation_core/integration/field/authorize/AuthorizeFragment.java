package de.robiasto.fragment_annotation_core.integration.field.authorize;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The {@code AuthorizeFragment} annotation is used to specify the authorization groups required for accessing a field in an annotation view. This annotation can be applied to fields
 * in classes implementing the {@link de.robiasto.fragment_annotation_core.interfaces.AnnotationViewInterface} interface.
 * <p>
 * The {@code AuthorizeFragment} annotation takes an array of {@link String} values as its parameter, representing the authorization groups required to access the field. When a field
 * is annotated with {@code AuthorizeFragment}, the field will only be visible if the current authenticated user has at least one of the specified authorization groups.
 * <p>
 * This annotation is used in conjunction with the {@link de.robiasto.fragment_annotation_core.interfaces.field.FieldDtoInterface} interface and the {@link de.robiasto.fragment_annotation_core.interfaces.field.FieldProcessorInterface} interface in the following usage example:
 * <p>
 * This annotation can be used to define the authorization requirements for specific fields in an annotation view. Only users with the specified authorization groups will be able
 * to see and access the annotated fields.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthorizeFragment {
    String[] value();
}
