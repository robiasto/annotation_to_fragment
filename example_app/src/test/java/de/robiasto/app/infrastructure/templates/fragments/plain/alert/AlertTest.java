package de.robiasto.app.infrastructure.templates.fragments.plain.alert;

import de.robiasto.app.AbstractTest;
import de.robiasto.app.infrastructure.fragment.plain.alert.AlertFragment;
import org.jsoup.nodes.Document;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@WebMvcTest(AlertTestController.class)
@ActiveProfiles("mvcTest")
@WithMockUser(username = "test", roles = "ADMIN")
class AlertTest extends AbstractTest {

    @Autowired
    MockMvc mockMvc;

    @Override
    protected MockMvc getMockMvc() {
        return this.mockMvc;
    }

    @ParameterizedTest
    @MethodSource("provideValidTestData")
    void testAlert(String url, AlertFragment.Type alertType) throws Exception {
        Document doc = this.getDocument(url);

        assertEquals(1, doc.select(".alert-" + alertType.status).size());
        assertEquals("Alert " + alertType.status, doc.select(".alert-" + alertType.status + " .flex .ml-3 p").text());
    }

    static Stream<Arguments> provideValidTestData() {
        return Stream.of(
                Arguments.of(AlertTestController.ERROR_TEXT_URL, AlertFragment.Type.ERROR),
                Arguments.of(AlertTestController.ERROR_TRANSLATION_URL, AlertFragment.Type.ERROR),
                Arguments.of(AlertTestController.SUCCESS_TEXT_URL, AlertFragment.Type.SUCCESS),
                Arguments.of(AlertTestController.SUCCESS_TRANSLATION_URL, AlertFragment.Type.SUCCESS)
        );
    }
}
