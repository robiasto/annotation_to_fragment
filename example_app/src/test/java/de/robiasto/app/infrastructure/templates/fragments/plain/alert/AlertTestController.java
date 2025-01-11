package de.robiasto.app.infrastructure.templates.fragments.plain.alert;

import de.robiasto.app.infrastructure.fragment.plain.alert.AlertFragment;
import de.robiasto.fragment_annotation_core.interfaces.CompositionProcessorInterface;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
class AlertTestController {
    private static final String PATH = "/alert-test";
    private static final String TEXT = "/text";
    private static final String TRANDSALTION = "/transliteration";
    private static final String ERROR = "/error";
    private static final String SUCCESS = "/success";

    public static final String ERROR_TEXT_URL = PATH + TEXT + ERROR;
    public static final String ERROR_TEXT_TEXT = "Alert error";
    public static final String ERROR_TRANSLATION_URL = PATH + TRANDSALTION + ERROR;
    public static final String ERROR_TRANSLATION_TEXT = "test.alert.error";

    public static final String SUCCESS_TEXT_URL = PATH + TEXT + SUCCESS;
    public static final String SUCCESS_TEXT_TEXT = "Alert success";
    public static final String SUCCESS_TRANSLATION_URL = PATH + TRANDSALTION + SUCCESS;
    public static final String SUCCESS_TRANSLATION_TEXT = "test.alert.success";

    public final CompositionProcessorInterface annotationProcessor;

    public AlertTestController(CompositionProcessorInterface annotationProcessor) {
        this.annotationProcessor = annotationProcessor;
    }

    @GetMapping(ERROR_TEXT_URL)
    public String alertTextError(Model model) {
        model.addAttribute("fragment", AlertFragment.withText(ERROR_TEXT_TEXT, AlertFragment.Type.ERROR));

        return "index";
    }

    @GetMapping(ERROR_TRANSLATION_URL)
    public String alertTranslationError(Model model) {
        model.addAttribute("fragment", AlertFragment.withTranslation(ERROR_TRANSLATION_TEXT, AlertFragment.Type.ERROR));

        return "index";
    }

    @GetMapping(SUCCESS_TEXT_URL)
    public String alertTextSuccess(Model model) {
        model.addAttribute("fragment", AlertFragment.withText(SUCCESS_TEXT_TEXT, AlertFragment.Type.SUCCESS));

        return "index";
    }

    @GetMapping(SUCCESS_TRANSLATION_URL)
    public String alertTranslationSuccess(Model model) {
        model.addAttribute("fragment", AlertFragment.withTranslation(SUCCESS_TRANSLATION_TEXT, AlertFragment.Type.SUCCESS));

        return "index";
    }
}
