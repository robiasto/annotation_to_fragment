package de.robiasto.fragment_annotation_core.interfaces.fragment.post_initial;

import de.robiasto.fragment_annotation_core.interfaces.fragment.ProcessType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This is an annotation that can be applied to other annotations to provide additional configuration for the
 * post-initialization process of fragments.
 * <p>
 * The {@code FragmentPostInitialConfiguration} annotation takes two parameters:
 * - {@code mapper}: A class that implements the {@code FragmentPostInitialMapperInterface}. This is responsible for
 * adding annotations to the fragment.
 * - {@code processType}: An enum constant of type {@code ProcessType}. It determines the type of processing to be
 * performed. Three constant values are available: {@code ANY}, {@code TYPE}, and {@code ANNOTATION}.
 * <p>
 * Example usage:
 * {@code
 *
 * @Target(ElementType.FIELD)
 * @Retention(RetentionPolicy.RUNTIME)
 * @FragmentPostInitialConfiguration( mapper = SetFragmentPathMapper.class,
 * processType = ProcessType.ANNOTATION
 * )
 * public @interface SetAnnotationFragmentPath {
 * String value();
 * }
 * }
 * <p>
 * The {@code FragmentPostInitialMapperInterface} is an interface that defines a single method, {@code addAnnotation},
 * which is responsible for adding annotations to the fragment. The method takes a {@code FragmentDtoInterface} and an
 * {@code Annotation} as parameters.
 * <p>
 * The {@code ProcessType} is an enum that represents the different types of processes that can be performed during the
 * post-initialization phase. The enum has three constant values: {@code ANY}, {@code TYPE}, and {@code ANNOTATION}.
 * <p>
 * The {@code FragmentDtoInterface} is an interface that extends the {@code FieldDtoInterface}. It provides access to the
 * field, model, annotation processor, and fragment interface of a fragment DTO.
 * <p>
 * The {@code SetFragmentPathMapper} is a class that implements the {@code FragmentPostInitialMapperInterface}. It adds
 * the path to the fragment by calling the {@code setPath} method of the {@code SetFragmentPathInterface} on the
 * fragment interface.
 * <p>
 * The {@code SetAnnotationFragmentPath} is an annotation that uses the {@code FragmentPostInitialConfiguration} annotation.
 * It configures the post-initialization process to use the {@code SetFragmentPathMapper} and to process annotations.
 * It has a single parameter, {@code value}, which specifies the path to be set on the fragment.
 * <p>
 * The {@code AddCssClassMapper} is a class that implements the {@code FragmentPostInitialMapperInterface}. It adds CSS
 * classes to the fragment by calling the {@code addCssClasses} method of the {@code CssClassFragmentInterface} on the
 * fragment interface.
 * <p>
 * The {@code AddCssClassOnType} is an annotation that uses the {@code FragmentPostInitialConfiguration} annotation.
 * It configures the post-initialization process to use the {@code AddCssClassMapper} and to process types. It has a
 * single parameter, {@code value}, which specifies the CSS classes to be added to the fragment.
 * <p>
 * The {@code AddCssClassOnAnnotation} is an annotation that uses the {@code FragmentPostInitialConfiguration}
 * annotation. It configures the post-initialization process to use the {@code AddCssClassMapper} and to process
 * annotations. It has a single parameter, {@code value}, which specifies the CSS classes to be added to the fragment.
 * <p>
 * The {@code PostInitialWrapperProcessor} is a class that processes fragment DTOs during the post-initialization
 * phase. It takes an array of {@code FragmentDtoInterface} and a {@code ProcessType} as parameters. It uses the
 * {@code AnnotationToDtoUtility} utility class to get the annotations with the {@code FragmentPostInitialConfiguration}
 * annotation from each fragment DTO. It then checks the process type of each annotation and only performs the processing
 * if it matches the given {@code fragmentToProcess} or if the process type is {@code ANY}. It uses the mapper class
 * obtained from the annotation to add the annotation to the fragment.
 * <p>
 * The {@code MapperFactory} is a factory class that provides instances of the mapper classes used in the
 * post-initialization process. It takes a class that implements the {@code FragmentPostInitialMapperInterface}
 * as a parameter and returns an instance of the mapper class. It also performs type checking to ensure that the returned
 * object is an instance of the {@code FragmentPostInitialMapperInterface}.
 * <p>
 * The {@code SetTypeFragmentPath} is an annotation that uses the {@code FragmentPostInitialConfiguration} annotation.
 * It configures the post-initialization process to use the {@code SetFragmentPathMapper} and to process types. It has a
 * single parameter, {@code value}, which specifies the path to be set on the fragment.
 * <p>
 * The {@code FragmentInterface} is an interface that represents a fragment. It provides the {@code setPath} method for
 * setting the path of the fragment, and the {@code addCssClasses} method for adding CSS classes to the fragment.
 * <p>
 * The {@code SetFragmentPathInterface} is an interface that extends the {@code FragmentInterface}. It provides the
 * {@code getPath} and {@code setPath} methods for getting and setting the path of the fragment.
 * <p>
 * The {@code CssClassFragmentInterface} is an interface that extends the {@code FragmentInterface}. It provides the
 * {@code getCssClassesToAdd} and {@code addCssClasses} methods for getting and adding CSS classes to the fragment.
 * <p>
 * The {@code AddCssAnnotationException} is an exception class that is thrown when the fragment does not implement
 * the {@code CssClassFragmentInterface} and an attempt is made to add CSS classes to the fragment.
 */
@Target(ElementType.ANNOTATION_TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface FragmentPostInitialConfiguration {
    Class<? extends FragmentPostInitialMapperInterface> mapper();

    ProcessType processType() default ProcessType.ANY;
}
