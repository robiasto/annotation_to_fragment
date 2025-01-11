package de.robiasto.app.infrastructure.templates.fragments.plain.pagination;

import de.robiasto.app.infrastructure.fragment.plain.pagination.PaginationFragment;
import de.robiasto.fragment_annotation_core.interfaces.AnnotationViewInterface;
import de.robiasto.fragment_annotation_core.interfaces.CompositionProcessorInterface;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Controller
class PaginationTestController {
    public static final String PATH = "/pagination-test/model-createModels/{model-createModels}/models-per-site/{models-per-site}/page-number/{page-number}/page-show-summary/{page-show-summary}/pages-display/{pages-display}";
    public final CompositionProcessorInterface annotationProcessor;

    public static String getPath(
             int createModels,
             int modelsPerSite,
             int pageNumber,
             boolean showSummary,
             int pagesDisplayed
    ) {
        return PATH
                .replaceFirst("\\{model-createModels}", String.valueOf(createModels))
                .replaceFirst("\\{models-per-site}", String.valueOf(modelsPerSite))
                .replaceFirst("\\{page-number}", String.valueOf(pageNumber))
                .replaceFirst("\\{page-show-summary}", String.valueOf(showSummary))
                .replaceFirst("\\{pages-display}", String.valueOf(pagesDisplayed));
    }


    public PaginationTestController(CompositionProcessorInterface annotationProcessor) {
        this.annotationProcessor = annotationProcessor;
    }

    @GetMapping(PATH)
    public String test(
            @PathVariable("model-createModels") int createModels,
            @PathVariable("models-per-site") int modelsPerSite,
            @PathVariable("page-number") int pageNumber,
            @PathVariable("page-show-summary") boolean showSummary,
            @PathVariable("pages-display") int pagesDisplay,

            Model model
    ) {

        Pageable pageable = PageRequest.of(pageNumber, modelsPerSite, Sort.by("title").ascending());

        List<AnnotationViewInterface> items = this.getList(createModels);


        model.addAttribute("fragment",
                           new PaginationFragment(
                                   new PageImpl<>(items, pageable,items.size()),
                                   pagesDisplay,
                                   showSummary
                           )
        );

        return "index";
    }

    private List<AnnotationViewInterface> getList(int pageNumber) {
        List<AnnotationViewInterface> list = new ArrayList<>();

        for (int i = 0; i < pageNumber; i++) {
            list.add(new PaginationModel());
        }

        return list;
    }

    @AllArgsConstructor
    public static class PaginationModel implements AnnotationViewInterface {
    }
}
