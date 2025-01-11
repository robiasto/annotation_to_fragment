package de.robiasto.fragment_annotation_core.interfaces.fragment.type.plainFragment;

import de.robiasto.fragment_annotation_core.NoSecurityTestConfig;
import de.robiasto.fragment_annotation_core.fragments.type.Plain;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Import(NoSecurityTestConfig.class)
public class PlainFragmentController {
    public static final String PATH = "/plain-fragment";

    @GetMapping(PATH)
    public String valid(Model model) {
        model.addAttribute("fragment", new Plain("plain fragment content"));

        return "index";
    }
}
