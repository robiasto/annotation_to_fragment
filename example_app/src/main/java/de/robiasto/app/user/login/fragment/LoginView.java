package de.robiasto.app.user.login.fragment;

import de.robiasto.app.infrastructure.fragment.form.FormField;
import de.robiasto.app.infrastructure.fragment.form.FormFieldFragmentTypes;
import de.robiasto.app.infrastructure.fragment.grid.LinkType;
import de.robiasto.app.infrastructure.fragment.grid.fragments.form.FormFragment;
import de.robiasto.app.infrastructure.fragment.grid.fragments.link.LinkFragment;
import de.robiasto.app.infrastructure.fragment.plain.alert.AlertFragment;
import de.robiasto.fragment_annotation_core.interfaces.AnnotationViewInterface;
import de.robiasto.fragment_annotation_core.interfaces.CompositionProcessorInterface;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

@Component
public class LoginView {
    private final CompositionProcessorInterface annotationProcessor;
    @Getter
    private final ModelAndView modelAndView;

    public LoginView(CompositionProcessorInterface annotationProcessor) {
        this.annotationProcessor = annotationProcessor;
        this.modelAndView = new ModelAndView(
                "fragments/"
                        + this.getClass().getPackage().getName().replace('.', '/')
                        + '/'
                        + this.getClass().getSimpleName()
                        + ".html"
        );
    }

    public void addAlert(String message, AlertFragment.Type type) {
        this.modelAndView.addObject("alert", AlertFragment.withTranslation(message, type));
    }

    public void addForm(String username, String password) {
        this.modelAndView.addObject("form",
                          new FormFragment(
                                  this.annotationProcessor.getComposition(new LoginForm(username, password)),
                                  "login",
                                  null,
                                  new LinkFragment(
                                          "Sign in",
                                          "",
                                          LinkType.SUBMIT,
                                          null
                                  ),
                                  true
                          )
        );
    }

    @Getter
    @AllArgsConstructor
    private static class LoginForm implements AnnotationViewInterface {
        @FormField(sorting = 1, label = "login.username", fragmentType = FormFieldFragmentTypes.INPUT_TEXT)
        String username;

        @FormField(sorting = 2, label = "login.password", fragmentType = FormFieldFragmentTypes.PASSWORD)
        String password;
    }
}
