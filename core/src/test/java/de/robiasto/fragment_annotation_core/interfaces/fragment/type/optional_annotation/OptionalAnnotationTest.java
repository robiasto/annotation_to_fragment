package de.robiasto.fragment_annotation_core.interfaces.fragment.type.optional_annotation;

import de.robiasto.fragment_annotation_core.AbstractTest;
import org.jsoup.nodes.Document;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@WebMvcTest(OptionalAnnotationController.class)
@ActiveProfiles("test")
class OptionalAnnotationTest extends AbstractTest {

    @Autowired
    MockMvc mockMvc;

    static Stream<Arguments> testData() {
        return Stream.of(
                Arguments.of(OptionalAnnotationController.WITH_ANNOTATION, "with annotation"),
                Arguments.of(OptionalAnnotationController.WITHOUT_ANNOTATION, "without annotation")
        );
    }

    @Override
    protected MockMvc getMockMvc() {
        return this.mockMvc;
    }

    @ParameterizedTest
    @MethodSource("testData")
    void test(String url, String expected) throws Exception {
        Document doc = this.getDocument(url);

        assertEquals("attribute " + expected, doc.select("body div div.attribute").get(0).text());
        assertEquals(expected, doc.select("body div div.annotation").get(0).text());
    }
}
