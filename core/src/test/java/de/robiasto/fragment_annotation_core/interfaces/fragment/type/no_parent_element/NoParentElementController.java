package de.robiasto.fragment_annotation_core.interfaces.fragment.type.no_parent_element;

import de.robiasto.fragment_annotation_core.NoSecurityTestConfig;
import de.robiasto.fragment_annotation_core.interfaces.CompositionProcessorInterface;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Import(NoSecurityTestConfig.class)
public class NoParentElementController {
    public static final String PATH = "/no-parent-element";

    public static final String ELEMENT = PATH;

    public final CompositionProcessorInterface annotationProcessor;

    public NoParentElementController(CompositionProcessorInterface annotationProcessor) {
        this.annotationProcessor = annotationProcessor;
    }

    @GetMapping(ELEMENT)
    public String noParentElement(Model model) {
        model.addAttribute("fragment", this.annotationProcessor.getComposition(new NoParentElementModel("name1")));

        return "index";
    }
}
