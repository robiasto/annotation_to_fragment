package de.robiasto.fragment_annotation_core.interfaces.fragment.type.plainFragment;

import de.robiasto.fragment_annotation_core.AbstractTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;

@WebMvcTest(PlainFragmentController.class)
@ActiveProfiles("test")
class PlainFragmentTest extends AbstractTest {

    @Autowired
    MockMvc mockMvc;

    @Override
    protected MockMvc getMockMvc() {
        return this.mockMvc;
    }

    @Test
    void validTest() throws Exception {
        assertEquals("plain fragment content", this.getDocument(PlainFragmentController.PATH).select("body div.plain").text());
    }

}
