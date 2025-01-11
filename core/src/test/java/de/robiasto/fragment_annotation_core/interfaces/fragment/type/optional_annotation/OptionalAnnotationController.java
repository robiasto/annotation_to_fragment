package de.robiasto.fragment_annotation_core.interfaces.fragment.type.optional_annotation;

import de.robiasto.fragment_annotation_core.NoSecurityTestConfig;
import de.robiasto.fragment_annotation_core.interfaces.CompositionProcessorInterface;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Import(NoSecurityTestConfig.class)
public class OptionalAnnotationController {
    public static final String PATH = "/optional-annotation";

    public static final String WITH_ANNOTATION = PATH + "/with";

    public static final String WITHOUT_ANNOTATION = PATH + "/without";

    public final CompositionProcessorInterface annotationProcessor;

    public OptionalAnnotationController(CompositionProcessorInterface annotationProcessor) {
        this.annotationProcessor = annotationProcessor;
    }

    @GetMapping(WITH_ANNOTATION)
    public String with(Model model) {
        model.addAttribute("fragment", this.annotationProcessor.getComposition(new WithOptionalAnnotationModel()));

        return "index";
    }

    @GetMapping(WITHOUT_ANNOTATION)
    public String without(Model model) {
        model.addAttribute("fragment", this.annotationProcessor.getComposition(new WithoutOptionalAnnotationModel()));

        return "index";
    }
}
