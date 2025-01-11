package de.robiasto.fragment_annotation_core.interfaces.fragment.annotation;

import de.robiasto.fragment_annotation_core.NoSecurityTestConfig;
import de.robiasto.fragment_annotation_core.interfaces.CompositionProcessorInterface;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Import(NoSecurityTestConfig.class)
public class FragmentAnnotationController {
    public static final String PATH = "/fragment-types-to-fragment-annotation";

    public final CompositionProcessorInterface annotationProcessor;

    public FragmentAnnotationController(CompositionProcessorInterface annotationProcessor) {
        this.annotationProcessor = annotationProcessor;
    }

    @GetMapping(PATH)
    public String invalidSortingDto(Model model) {
        model.addAttribute("fragment", this.annotationProcessor.getComposition(new FragmentAnnotationModel()));

        return "index";
    }
}
