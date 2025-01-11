package de.robiasto.fragment_annotation_core.interfaces.fragment.type;

import de.robiasto.fragment_annotation_core.interfaces.fragment.FragmentDtoInterface;
import de.robiasto.fragment_annotation_core.interfaces.fragment.FragmentInterface;

/**
 * The FragmentAnnotationMapperInterface is an interface that defines the method for mapping a FragmentDtoInterface to a FragmentInterface.
 * It includes the following method:
 * - `getFragment` takes a FragmentDtoInterface object as a parameter and returns a FragmentInterface object representing the mapped fragment.
 * <p>
 * This interface is used to map a data transfer object (FragmentDtoInterface) to a fragment (FragmentInterface). The getFragment method is responsible for performing the mapping
 * <p>
 * operation and returning the mapped fragment. The implementation of this method should follow the business logic defined by the application.
 * <p>
 * To use the FragmentAnnotationMapperInterface, you need to provide an implementation of this interface that defines the getFragment method.
 * You can then use the implemented class to map FragmentDtoInterface objects to FragmentInterface objects.
 * es a standardized way to map the data transfer objects to fragments, allowing for easier integration and maintenance of the application.
 * <p>
 * Note: This interface extends the FragmentDtoInterface interface, which provides additional methods for manipulating fragment data transfer objects.
 * Refer to the FragmentDtoInterface documentation for more details.
 */
public interface FragmentAnnotationMapperInterface {
    FragmentInterface getFragment(FragmentDtoInterface fragmentDto);
}
