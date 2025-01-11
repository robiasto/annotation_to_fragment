package de.robiasto.fragment_annotation_core.interfaces.fragment.type;

/**
 * Interface representing a fragment type enum. The enum should implement this interface to provide a mapping
 * between the enum values and the corresponding fragment mappers. It requires implementing the {@code getFragmentMapper()}
 * method which returns the fragment mapper for each enum value.
 *
 * @see FragmentTypeMapperInterface
 */
public interface FragmentTypeEnumInterface {
    FragmentTypeMapperInterface getFragmentMapper();

}
