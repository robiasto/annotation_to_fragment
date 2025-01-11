package de.robiasto.fragment_annotation_core.interfaces;

import java.util.List;

/**
 * The ExtendedViewInterface is an interface that provides an additional method
 * for obtaining the superclass of a class. This interface must get implemented
 * by view classes that extend other view classes.
 */
public interface ExtendedViewInterface extends AnnotationViewInterface{
    List<AnnotationViewInterface> getAddedViews();
}
