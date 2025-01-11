package de.robiasto.fragment_annotation_core.interfaces.fragment.type.parent_element_two_level;

import de.robiasto.fragment_annotation_core.AbstractTest;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;

@WebMvcTest(ParentTwoLevelElementController.class)
@ActiveProfiles("test")
class ParentElementTwoLevelTest extends AbstractTest {

    final String cssQuery = "body div.root-view div.parent-view div.root-view div.parent-view div.plain";
    @Autowired
    MockMvc mockMvc;

    @Override
    protected MockMvc getMockMvc() {
        return this.mockMvc;
    }

    @Test
    void testElement() throws Exception {
        Document doc = this.getDocument(ParentTwoLevelElementController.ELEMENT);

        assertEquals("name1 parent view", doc.select(cssQuery).text());
    }

}
