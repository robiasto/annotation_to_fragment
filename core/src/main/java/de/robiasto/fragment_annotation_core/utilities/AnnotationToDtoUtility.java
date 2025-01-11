package de.robiasto.fragment_annotation_core.utilities;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The AnnotationToDtoUtility class provides utility methods for retrieving annotation information and converting it to AnnotationDto objects.
 */
public class AnnotationToDtoUtility {

    /**
     * Returns an {@link AnnotationDto} object based on the specified field and meta-annotation class.
     *
     * @param field               the field to retrieve the annotation from
     * @param metaAnnotationClass the class of the meta-annotation
     * @param <M>                 the type of the meta-annotation
     * @return the {@link AnnotationDto} object containing the annotation and its meta-annotation, or {@code null} if no matching annotation is found
     * @throws UtilityException if there are more than one annotation with the specified meta-annotation class
     */
    public static <M extends Annotation> AnnotationDto<M> getAnnotationDto(Field field, Class<M> metaAnnotationClass) {
        List<AnnotationDto<M>> annotations = AnnotationToDtoUtility.getAnnotationDtos(field, metaAnnotationClass);

        if (annotations.isEmpty()) {
            return null;
        }

        if (annotations.size() > 1) {
            throw new UtilityException("There are more than one annotation with meta annotation: " + metaAnnotationClass.getName());
        }

        return annotations.get(0);
    }

    /**
     * Returns a list of {@link AnnotationDto} objects based on the specified field and meta-annotation class.
     *
     * @param field               the field to retrieve the annotations from
     * @param metaAnnotationClass the class of the meta-annotation
     * @param <M>                 the type of the meta-annotation
     * @return the list of {@link AnnotationDto} objects containing the annotations and their meta-annotations
     */
    public static <M extends Annotation> List<AnnotationDto<M>> getAnnotationDtos(
            Field field,
            Class<M> metaAnnotationClass
    ) {
        List<AnnotationDto<M>> annotations = new ArrayList<>();

        Arrays.stream(field.getAnnotations()).forEach(annotation -> {
            if (annotation.annotationType().isAnnotationPresent(metaAnnotationClass)) {
                annotations.add(new AnnotationDto<>(
                        annotation,
                        annotation.annotationType().getAnnotation(metaAnnotationClass),
                        field
                ));
            }
        });

        return annotations;
    }

    public record AnnotationDto<A>(Annotation annotation, A metaAnnotation, Field field) {
    }
}
