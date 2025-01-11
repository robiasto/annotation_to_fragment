package de.robiasto.fragment_annotation_core.integration.field.authorize;

import de.robiasto.fragment_annotation_core.interfaces.field.FieldDtoInterface;
import de.robiasto.fragment_annotation_core.interfaces.field.FieldProcessorInterface;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;

/**
 * The {@code AuthorizeProcessor} class is a component that implements the {@link FieldProcessorInterface} interface. It provides functionality to process an array of {@link FieldDtoInterface}
 * objects by filtering them based on the authorization groups of the current authenticated user.
 * <p>
 * The {@code process} method takes an array of {@link FieldDtoInterface} objects as input and returns a new array of {@link FieldDtoInterface} objects after processing. It filters
 * the input fields by checking if each field is annotated with the {@link AuthorizeFragment} annotation. If a field is not annotated, it is considered authorized. If a field is annotated
 * , it checks if the current authenticated user has any of the specified authorization groups in the {@link AuthorizeFragment} annotation. Only fields that are authorized will be
 * included in the output array.
 * <p>
 * The {@code getPretence} method returns an integer value representing the precedence of the field processor. This value is used to determine the processing order of multiple field
 * processors. The field processors are sorted in ascending order of their precedence values during processing.
 * <p>
 * This class is annotated with {@code @Component}, indicating that it is a Spring component and can be autodetected and autowired by the Spring framework. The class name should be
 * provided as the value of the {@code @Component} annotation when using it as a bean.
 * <p>
 * This class should be used as a component for processing fields based on authorization requirements. It can be injected into other classes and used in conjunction with the {@link
 * FieldDtoInterface} and {@link AuthorizeFragment} interfaces.
 */
@Component
public class AuthorizeProcessor implements FieldProcessorInterface {
    public FieldDtoInterface[] process(FieldDtoInterface[] fieldDtos) {
        Collection<? extends GrantedAuthority> authGroups = SecurityContextHolder.getContext()
                                                                                 .getAuthentication()
                                                                                 .getAuthorities();
        return Arrays.stream(fieldDtos)
                     .filter(fieldDto -> {
                         if (!fieldDto.field().isAnnotationPresent(AuthorizeFragment.class)) {
                             return true;
                         }

                         return authGroups
                                 .stream()
                                 .anyMatch(authGroup ->
                                                   Arrays.stream(
                                                                 fieldDto
                                                                         .field()
                                                                         .getAnnotation(AuthorizeFragment.class)
                                                                         .value()
                                                         )
                                                         .anyMatch(
                                                                 annotationValue ->
                                                                         authGroup
                                                                                 .getAuthority()
                                                                                 .equals(annotationValue)
                                                         )
                                 );
                     })
                     .toArray(FieldDtoInterface[]::new);
    }

    @Override
    public int getPretence() {
        return 100;
    }
}
