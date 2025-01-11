package de.robiasto.fragment_annotation_core.interfaces.fragment.annotation;

import de.robiasto.fragment_annotation_core.AbstractTest;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;

@WebMvcTest(FragmentAnnotationController.class)
@ActiveProfiles("test")
class FragmentAnnotationTest extends AbstractTest {

    final String cssQuery = "body div.with-headline";
    @Autowired
    MockMvc mockMvc;

    @Override
    protected MockMvc getMockMvc() {
        return this.mockMvc;
    }

    @Test
    void testNoParentElements() throws Exception {
        Document doc = this.getDocument(FragmentAnnotationController.PATH);

        assertEquals("Headline Text", doc.select(this.cssQuery + " h1").text());
        assertEquals("name", doc.select(this.cssQuery + " div.fragment").text());
    }
}
