package de.robiasto.fragment_annotation_core.interfaces.fragment.type.mandatory_annotation;

import de.robiasto.fragment_annotation_core.NoSecurityTestConfig;
import de.robiasto.fragment_annotation_core.interfaces.CompositionProcessorInterface;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Import(NoSecurityTestConfig.class)
public class MandatoryAnnotationController {
    public static final String PATH = "/mandatory-annotation";

    public static final String VALID = PATH + "/valid";

    public static final String INVALID = PATH + "/invalid";

    public final CompositionProcessorInterface annotationProcessor;

    public MandatoryAnnotationController(CompositionProcessorInterface annotationProcessor) {
        this.annotationProcessor = annotationProcessor;
    }

    @GetMapping(VALID)
    public String valid(Model model) {
        model.addAttribute("fragment", this.annotationProcessor.getComposition(new MandatoryAnnotationModel()));

        return "index";
    }

    @GetMapping(INVALID)
    public String invalid(Model model) {
        model.addAttribute("fragment", this.annotationProcessor.getComposition(new MandatoryAnnotationModelInvalid()));

        return "index";
    }

}
