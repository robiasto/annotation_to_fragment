package de.robiasto.app.infrastructure.fragment.view.form;

import de.robiasto.app.infrastructure.fragment.grid.LinkType;
import de.robiasto.app.infrastructure.fragment.grid.fragments.form.FormFragment;
import de.robiasto.app.infrastructure.fragment.grid.fragments.link.LinkFragment;
import de.robiasto.app.infrastructure.fragment.page.default_page.DefaultModelAndView;
import de.robiasto.fragment_annotation_core.interfaces.AnnotationViewInterface;
import de.robiasto.fragment_annotation_core.interfaces.CompositionProcessorInterface;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

@AllArgsConstructor
@Component
public class FormView {
    private DefaultModelAndView modelAndView;

    private final CompositionProcessorInterface annotationProcessor;

    public void initialize(
            String title,
            AnnotationViewInterface annotationView,
            String actionUrl,
            String cancelUrl,
            boolean editable
    ) {
        this.modelAndView.setHeader(title);
        this.modelAndView.setContent(
                new FormFragment(
                        this.annotationProcessor.getComposition(annotationView),
                        actionUrl,
                        new LinkFragment("Back", "", LinkType.BUTTON, cancelUrl),
                        editable
                                ? new LinkFragment("Submit", "", LinkType.SUBMIT, actionUrl)
                                : null,
                        editable
                )
        );
    }

    public ModelAndView getModelAndView() {
        return this.modelAndView.getValidatetModelAndView();
    }
}
