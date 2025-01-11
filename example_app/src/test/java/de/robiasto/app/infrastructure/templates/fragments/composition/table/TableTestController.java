package de.robiasto.app.infrastructure.templates.fragments.composition.table;

import de.robiasto.fragment_annotation_core.interfaces.CompositionProcessorInterface;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class TableTestController {
    private static final String PATH = "/table-test";
    public static final String ROW = PATH + "/row";
    public static final String TABLE = PATH + "/table";
    public static final String TABLE_MANY_ROWS = PATH + "/table2";
    public final CompositionProcessorInterface annotationProcessor;

    public TableTestController(CompositionProcessorInterface annotationProcessor) {
        this.annotationProcessor = annotationProcessor;
    }

    @GetMapping(ROW)
    public String rowTest(Model model) {
        model.addAttribute("fragment", this.annotationProcessor.getComposition(new RowTestModel(1)));

        return "index";
    }

    @GetMapping(TABLE)
    public String tableTest(Model model) {
        model.addAttribute("fragment", this.annotationProcessor.getComposition(new TableTestModel(
                List.of(
                        new RowTestModel(1),
                        new RowTestModel(2)
                )
        )));

        return "index";
    }

    @GetMapping(TABLE_MANY_ROWS)
    public String tableTestManyRows(Model model) {
        model.addAttribute(
                "fragment",
                this.annotationProcessor.getComposition(
                        new TableTestModel(
                                List.of(
                                        new RowTestModel(1),
                                        new RowTestModel(1),
                                        new RowTestModel(1),
                                        new RowTestModel(1),
                                        new RowTestModel(1),
                                        new RowTestModel(1),
                                        new RowTestModel(1),
                                        new RowTestModel(1),
                                        new RowTestModel(1),
                                        new RowTestModel(1),
                                        new RowTestModel(1),
                                        new RowTestModel(1),
                                        new RowTestModel(1),
                                        new RowTestModel(1),
                                        new RowTestModel(1),
                                        new RowTestModel(1),
                                        new RowTestModel(1),
                                        new RowTestModel(1),
                                        new RowTestModel(1),
                                        new RowTestModel(1),
                                        new RowTestModel(1),
                                        new RowTestModel(1),
                                        new RowTestModel(1),
                                        new RowTestModel(1),
                                        new RowTestModel(1),
                                        new RowTestModel(1),
                                        new RowTestModel(1),
                                        new RowTestModel(1),
                                        new RowTestModel(1),
                                        new RowTestModel(1),
                                        new RowTestModel(1),
                                        new RowTestModel(1),
                                        new RowTestModel(1),
                                        new RowTestModel(1),
                                        new RowTestModel(1),
                                        new RowTestModel(1),
                                        new RowTestModel(1),
                                        new RowTestModel(1),
                                        new RowTestModel(1),
                                        new RowTestModel(1),
                                        new RowTestModel(1),
                                        new RowTestModel(1),
                                        new RowTestModel(1),
                                        new RowTestModel(1),
                                        new RowTestModel(1),
                                        new RowTestModel(1),
                                        new RowTestModel(1),
                                        new RowTestModel(1),
                                        new RowTestModel(1),
                                        new RowTestModel(1),
                                        new RowTestModel(1),
                                        new RowTestModel(1),
                                        new RowTestModel(1),
                                        new RowTestModel(1),
                                        new RowTestModel(1),
                                        new RowTestModel(1),
                                        new RowTestModel(1),
                                        new RowTestModel(1),
                                        new RowTestModel(1),
                                        new RowTestModel(1),
                                        new RowTestModel(2)
                                )
                        )
                )
        );

        return "index";
    }
}
