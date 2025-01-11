package de.robiasto.fragment_annotation_core.interfaces.fragment.type.parent_element_two_level;

import de.robiasto.fragment_annotation_core.NoSecurityTestConfig;
import de.robiasto.fragment_annotation_core.interfaces.CompositionProcessorInterface;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Import(NoSecurityTestConfig.class)
public class ParentTwoLevelElementController {
    public static final String PATH = "/with-parent-element-level-two";

    public static final String ELEMENT = PATH;

    public final CompositionProcessorInterface annotationProcessor;

    public ParentTwoLevelElementController(CompositionProcessorInterface annotationProcessor) {
        this.annotationProcessor = annotationProcessor;
    }

    @GetMapping(ELEMENT)
    public String noParentElement(Model model) {
        model.addAttribute("fragment", this.annotationProcessor.getComposition(new TwoLevelRootModel("name1")));

        return "index";
    }
}
