package de.robiasto.fragment_annotation_core.interfaces.fragment;

/**
 * This interface represents a fragment in an application.
 * Fragments are used to display reusable UI components.
 */
public interface FragmentInterface {
    /**
     * used in ReplaceFragmentInterfaceAttributeProcessor to get the fragment path and function
     */
    String getPath();
}
