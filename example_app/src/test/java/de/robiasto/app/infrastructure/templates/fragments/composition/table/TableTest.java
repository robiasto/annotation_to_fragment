package de.robiasto.app.infrastructure.templates.fragments.composition.table;

import de.robiasto.app.AbstractTest;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;

@WebMvcTest(TableTestController.class)
@ActiveProfiles("mvcTest")
@WithMockUser(username = "test", roles = "ADMIN")
class TableTest extends AbstractTest {

    @Autowired
    MockMvc mockMvc;

    @Override
    protected MockMvc getMockMvc() {
        return this.mockMvc;
    }

    @Test
    void testRow() throws Exception {
        Document doc = this.getDocument(TableTestController.ROW);

        assertEquals(1, doc.select(".text-paragraph").size());
        assertEquals(1, doc.select(".link").size());
        assertEquals(1, doc.select(".link-fragment").size());
    }

    @Test
    void testTable() throws Exception {
        Document doc = this.getDocument(TableTestController.TABLE);

        assertEquals(2, doc.select("tbody tr").size());
    }

    @Test
    void testTableManyRows() throws Exception {
        Document doc = this.getDocument(TableTestController.TABLE_MANY_ROWS);

        assertEquals(61, doc.select("tbody tr").size());
    }
}
