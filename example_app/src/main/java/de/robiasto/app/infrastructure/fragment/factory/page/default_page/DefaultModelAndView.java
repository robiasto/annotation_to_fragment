package de.robiasto.app.infrastructure.fragment.factory.page.default_page;

import de.robiasto.app.infrastructure.fragment.grid.fragments.headline.HeadlineFragment;
import de.robiasto.fragment_annotation_core.interfaces.fragment.FragmentInterface;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;


@Component
public class DefaultModelAndView {
    private final ModelAndView modelAndView;
    private static final String TITLE = "title";
    private static final String HEADER = "header";
    private static final String CONTENT = "content";

    private static final Map<String, String> mustSet = Map.ofEntries(
            Map.entry(TITLE, "Title must be set!"),
            Map.entry(CONTENT, "Content must be set! ")
    );

    protected DefaultModelAndView() {
        this.modelAndView = new ModelAndView(
                "fragments/"
                        + this.getClass().getPackage().getName().replace('.', '/')
                        + '/'
                        + this.getClass().getSimpleName()
                        + ".html"
        );
    }

    public void setTitle(String title) {
        this.modelAndView.addObject(TITLE, title);
    }

    public void setHeader(String headerText) {
        this.setTitle(headerText);

        HeadlineFragment headlineFragment = new HeadlineFragment(headerText, HeadlineFragment.Type.H1);
        headlineFragment.addCssClasses("mb-4");

        this.modelAndView.addObject(HEADER, headlineFragment);
    }

    public void setHeader(FragmentInterface[] headerView) {
        this.modelAndView.addObject(HEADER, headerView);
    }

    public void setHeader(FragmentInterface headerView) {
        this.modelAndView.addObject(HEADER, headerView);
    }

    public void setContent(FragmentInterface[] contentView) {
        this.modelAndView.addObject(CONTENT,contentView);
    }

    public void setContent(FragmentInterface contentView) {
        this.modelAndView.addObject(CONTENT,contentView);
    }

    public ModelAndView getValidatetModelAndView() {
        this.validateModelAndView();

        return this.modelAndView;
    }

    private void validateModelAndView() {
        Map<String, Object> model = this.modelAndView.getModel();

        mustSet.forEach((key, value) -> {
            if(!model.containsKey(key)){
                throw new IllegalArgumentException(value);
            }
        });
    }
}
