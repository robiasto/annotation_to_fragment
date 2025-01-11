package de.robiasto.app.infrastructure.fragment.grid;

import de.robiasto.app.infrastructure.fragment.grid.fragments.headline.HeadlineFragment;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The HeadlineConfiguration annotation is used to configure a HeadlineFragment.
 * It is a meta-annotation that can be applied to a field to specify the type of the HeadlineFragment.
 * The value() method of the annotation returns the type of the HeadlineFragment, which is an enum defined in the HeadlineFragment class.
 * <p>
 * Example usage:
 * Suppose we have a class Mapper that implements the FragmentTypeMapperInterface.
 * Inside the getAnnotationMapper() method of Mapper, we can create a new instance of HeadlineFragment and pass the value from the HeadlineConfiguration annotation.
 * <p>
 * ```java
 * public static class Mapper implements FragmentTypeMapperInterface {
 * private final StringMapper valueMapper;
 * <p>
 * public Mapper(StringMapper mapper) {
 * this.valueMapper = mapper;
 * }
 *
 * @Override public FragmentInterface getAnnotationMapper(FieldDtoInterface fragmentDto) {
 * return new HeadlineFragment(
 * this.valueMapper.getValue(fragmentDto),
 * Optional.ofNullable(fragmentDto.field().getAnnotation(HeadlineConfiguration.class))
 * .map(HeadlineConfiguration::value)
 * .orElse(Type.H1)
 * );
 * }
 * }
 * ```
 * @see HeadlineFragment.Type
 * @see HeadlineFragment.Mapper
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface HeadlineConfiguration {
    HeadlineFragment.Type value();
}
