package de.robiasto.fragment_annotation_core.interfaces.fragment.type.mandatory_annotation;

import de.robiasto.fragment_annotation_core.AbstractTest;
import jakarta.servlet.ServletException;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

@WebMvcTest(MandatoryAnnotationController.class)
@ActiveProfiles("test")
class MandatoryAnnotationTest extends AbstractTest {

    @Autowired
    MockMvc mockMvc;

    @Override
    protected MockMvc getMockMvc() {
        return this.mockMvc;
    }

    @Test
    void validTest() throws Exception {
        Document doc = this.getDocument(MandatoryAnnotationController.VALID);

        assertEquals("mandatory type value", doc.select("body div div#annotation-content").text());
        assertEquals("name1", doc.select("body div div#content").text());
    }

    @Test
    void invalidTest() {
        assertEquals(
                "Request processing failed: de.robiasto.fragment_annotation_core.interfaces.fragment.type.mandatory_annotation.fragments.type.MandatoryTypeException: @MandatoryTypeAnnotation is not Implemented in name1.\n" +
                        "Add @MandatoryTypeAnnotation annotation to attribute name1.",
                assertThrowsExactly(
                        ServletException.class,
                        () -> this.getResult(MandatoryAnnotationController.INVALID)
                ).getMessage()
        );
    }
}
