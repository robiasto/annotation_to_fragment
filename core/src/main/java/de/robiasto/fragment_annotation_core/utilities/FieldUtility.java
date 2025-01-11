package de.robiasto.fragment_annotation_core.utilities;

import de.robiasto.fragment_annotation_core.interfaces.AnnotationViewInterface;
import de.robiasto.fragment_annotation_core.interfaces.ExtendedViewInterface;
import de.robiasto.fragment_annotation_core.interfaces.field.FieldDtoInterface;
import org.springframework.data.domain.Page;
import org.springframework.util.Assert;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * The FieldUtility class provides utility methods for working with fields and annotations.
 * It contains static methods to retrieve field values, retrieve fields from a model object,
 * retrieve annotation views, and retrieve values from an annotation object.
 */
@SuppressWarnings("java:S3011")
public class FieldUtility {
    private FieldUtility() {
    }

    /**
     * Retrieves the value of a field from a given object.
     *
     * @param fieldDtoInterface the field DTO interface containing the field and model information
     * @return the value of the field retrieved from the model object
     */
    public static Object getObject(FieldDtoInterface fieldDtoInterface) {
        return FieldUtility.getObject(fieldDtoInterface.model(), fieldDtoInterface.field());
    }

    /**
     * Retrieves the value of a field from a given object.
     *
     * @param model the model object from which to retrieve the field value
     * @param field the field that needs to be retrieved
     * @return the value of the field retrieved from the model object
     * @throws UtilityException if there is an exception while retrieving the value
     */
    public static Object getObject(Object model, Field field) {
        field.setAccessible(true);

        try {
            return field.get(model);
        } catch (IllegalArgumentException | IllegalAccessException e) {
            throw new UtilityException(e.getMessage());
        }
    }

    /**
     * Retrieves all the fields from a given model object.
     *
     * @param model the model object from which to retrieve the fields
     * @return a Stream containing all the fields retrieved from the model object
     */
    public static Stream<Field> getFieldsFromModel(Object model) {
        if (model instanceof ExtendedViewInterface extendedViewInterface) {

            return Stream.concat(
                    FieldUtility.getFieldsFromClass(extendedViewInterface.getClass().getSuperclass()),
                    FieldUtility.getFieldsFromClass(extendedViewInterface.getClass())
            );
        }

        return getFieldsFromClass(model.getClass());
    }

    /**
     * Retrieves all the fields from the given class.
     *
     * @param clazz the class from which to retrieve the fields
     * @return a Stream containing all the fields retrieved from the class
     */
    @SuppressWarnings("java:S3864")
    private static Stream<Field> getFieldsFromClass(Class<?> clazz) {
        return Stream
                .of(clazz.getDeclaredFields())
                .peek(field -> field.setAccessible(true));
    }


    /**
     * Retrieves the {@link AnnotationViewInterface} object from a given {@link FieldDtoInterface} object.
     *
     * @param fieldDto the field DTO interface containing the field and model information
     * @return the {@link AnnotationViewInterface} object retrieved from the model object
     * @throws IllegalArgumentException if the object retrieved from the model object is not an instance of {@link AnnotationViewInterface}
     */
    public static AnnotationViewInterface getAnnotationView(FieldDtoInterface fieldDto) {
        Object object = FieldUtility.getObject(fieldDto.model(), fieldDto.field());

        if (object instanceof AnnotationViewInterface annotationViewInterface) {
            return annotationViewInterface;
        }

        throw new UtilityException("Object type should be annotationViewInterface.");
    }

    /**
     * Retrieves a list of AnnotationViewInterface objects from a given FieldDtoInterface.
     *
     * @param fieldDto the FieldDtoInterface containing the field and model information
     * @return a list of AnnotationViewInterface objects retrieved from the model object
     * @throws UtilityException if the model object is of an invalid type
     */
    public static List<AnnotationViewInterface> getAnnotationViewList(FieldDtoInterface fieldDto) {
        Object model = FieldUtility.getObject(
                fieldDto.model(),
                fieldDto.field()
        );

        if (model instanceof Page<?> page) {
            return FieldUtility.getTypeSafeAnnotationViewList((page).getContent());
        }

        if (model instanceof AnnotationViewInterface[] obj) {
            return Arrays.stream(obj).toList();
        }

        if (model instanceof List<?> list) {
            return FieldUtility.getTypeSafeAnnotationViewList(list);
        }

        throw new UtilityException("Invalid Table Fragment Model");
    }

    /**
     * Retrieves a list of type-safe AnnotationViewInterface objects from a given List of unknown objects.
     *
     * @param unknownList the list of unknown objects
     * @return a list of type-safe AnnotationViewInterface objects retrieved from the unknown list
     */
    private static List<AnnotationViewInterface> getTypeSafeAnnotationViewList(List<?> unknownList) {
        List<AnnotationViewInterface> annotationViewInterfaces = new ArrayList<>();
        unknownList.forEach(unknownListItem -> {
            Assert.isInstanceOf(AnnotationViewInterface.class, unknownListItem);
            annotationViewInterfaces.add((AnnotationViewInterface) unknownListItem);
        });

        return annotationViewInterfaces;
    }

    /**
     * Retrieves the value of a method by name from an {@link Annotation} object.
     *
     * @param annotation the annotation object from which to retrieve the value
     * @param name       the name of the method to invoke
     * @return the value returned by invoking the method on the annotation object
     * @throws UtilityException if there is an exception while retrieving the value
     */
    public static Object getValueByName(Annotation annotation, String name) {
        try {
            Method method = annotation.getClass().getDeclaredMethod(name);

            return method.invoke(annotation);
        } catch (Exception e) {
            throw new UtilityException(e.getMessage());
        }
    }
}
