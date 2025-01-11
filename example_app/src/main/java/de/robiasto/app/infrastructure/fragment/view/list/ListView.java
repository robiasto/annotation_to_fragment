package de.robiasto.app.infrastructure.fragment.view.list;

import de.robiasto.app.infrastructure.fragment.grid.*;
import de.robiasto.app.infrastructure.fragment.page.default_page.DefaultModelAndView;
import de.robiasto.fragment_annotation_core.annotation.post_init.add_css_class.AddCssClassOnAnnotation;
import de.robiasto.fragment_annotation_core.integration.field.authorize.AuthorizeFragment;
import de.robiasto.fragment_annotation_core.interfaces.AnnotationViewInterface;
import de.robiasto.fragment_annotation_core.interfaces.CompositionProcessorInterface;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

@AllArgsConstructor
@Component
public class ListView<T extends AnnotationViewInterface> {

    private DefaultModelAndView modelAndView;

    private final CompositionProcessorInterface annotationProcessor;

    public void initialize(
            String title,
            String createLink,
            Page<T> page
    ) {
        this.modelAndView.setHeader(title);
        this.modelAndView.setContent(this.annotationProcessor.getComposition(new View<T>(createLink, page)));
    }

    public ModelAndView getModelAndView() {
        return this.modelAndView.getValidatetModelAndView();
    }

    @AllArgsConstructor
    private static class View<T> implements AnnotationViewInterface {
        @GridColFragmentConfiguration(
                fragmentType = FragmentTypes.LINK,
                sorting = 1,
                gridCols = GridColTypes.COL_1,
                gridStart = GridColStartTypes.COL_6
        )
        @LinkConfiguration(text = "Create", type = LinkType.BUTTON)
        @AddCssClassOnAnnotation("text-end max-xl:px-10 max-xl:py-5")
        @AuthorizeFragment("ROLE_ADMIN")
        String createLink;

        @GridColFragmentConfiguration(
                fragmentType = FragmentTypes.TABLE,
                sorting = 2
        )
        final Page<T> rows;
    }
}
