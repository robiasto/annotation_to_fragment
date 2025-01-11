package de.robiasto.fragment_annotation_core.interfaces.field.sorting;

import de.robiasto.fragment_annotation_core.NoSecurityTestConfig;
import de.robiasto.fragment_annotation_core.interfaces.CompositionProcessorInterface;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Import(NoSecurityTestConfig.class)
public class SortingTestController {
    public static final String PATH = "/model-to-sorting-test";

    public static final String INVALID_SORTING_TEST_PATH = PATH + "/invalid-sorting";
    public static final String VALID1_SORTING_TEST_PATH = PATH + "/valid-sorting-1";
    public static final String VALID2_SORTING_TEST_PATH = PATH + "/valid-sorting-2";

    public final CompositionProcessorInterface annotationProcessor;

    public SortingTestController(CompositionProcessorInterface annotationProcessor) {
        this.annotationProcessor = annotationProcessor;
    }

    @GetMapping(INVALID_SORTING_TEST_PATH)
    public String invalidSortingDto(Model model) {
        model.addAttribute("fragment", this.annotationProcessor.getComposition(new SortingModelInvalid()));

        return "index";
    }

    @GetMapping(VALID1_SORTING_TEST_PATH)
    public String valid1(Model model) {
        model.addAttribute("fragment", this.annotationProcessor.getComposition(new SortingModel1()));

        return "index";
    }

    @GetMapping(VALID2_SORTING_TEST_PATH)
    public String valid2(Model model) {
        model.addAttribute("fragment", this.annotationProcessor.getComposition(new SortingModel2()));

        return "index";
    }
}
