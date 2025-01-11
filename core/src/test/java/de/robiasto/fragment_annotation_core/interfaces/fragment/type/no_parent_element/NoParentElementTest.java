package de.robiasto.fragment_annotation_core.interfaces.fragment.type.no_parent_element;

import de.robiasto.fragment_annotation_core.AbstractTest;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;

@WebMvcTest(NoParentElementController.class)
@ActiveProfiles("test")
class NoParentElementTest extends AbstractTest {

    final String cssQuery = "body div.plain";
    @Autowired
    MockMvc mockMvc;

    @Override
    protected MockMvc getMockMvc() {
        return this.mockMvc;
    }

    @Test
    void testNoParentElements() throws Exception {
        Document doc = this.getDocument(NoParentElementController.ELEMENT);

        assertEquals("name1", doc.select(this.cssQuery).text());
    }
}
