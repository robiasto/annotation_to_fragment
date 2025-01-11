package de.robiasto.fragment_annotation_core.interfaces.fragment.type.parent_element_one_level;

import de.robiasto.fragment_annotation_core.NoSecurityTestConfig;
import de.robiasto.fragment_annotation_core.interfaces.CompositionProcessorInterface;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Import(NoSecurityTestConfig.class)
public class ParentElementController {
    public static final String PATH = "/with-parent-element-level-one";

    public static final String ELEMENT = PATH;

    public final CompositionProcessorInterface annotationProcessor;

    public ParentElementController(CompositionProcessorInterface annotationProcessor) {
        this.annotationProcessor = annotationProcessor;
    }

    @GetMapping(ELEMENT)
    public String noParentElement(Model model) {
        model.addAttribute("fragment", this.annotationProcessor.getComposition(new OneLevelRootModel("name1")));

        return "index";
    }
}
