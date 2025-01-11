package de.robiasto.fragment_annotation_core.interfaces.field.sorting;

import de.robiasto.fragment_annotation_core.AbstractTest;
import jakarta.servlet.ServletException;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(SortingTestController.class)
@ActiveProfiles("test")
class SortingTest extends AbstractTest {

    @Autowired
    MockMvc mockMvc;

    static Stream<Arguments> provideValidTestData() {
        return Stream.of(
                Arguments.of(List.of("name", "namePrivate"), SortingTestController.VALID1_SORTING_TEST_PATH),
                Arguments.of(List.of("namePrivate", "name"), SortingTestController.VALID2_SORTING_TEST_PATH)
        );
    }

    @Override
    protected MockMvc getMockMvc() {
        return this.mockMvc;
    }

    @Test
    void testInvalidModelWithInvalidSortingDto() {
        assertThrows(ServletException.class, () -> this.getResult(SortingTestController.INVALID_SORTING_TEST_PATH));
        assertEquals(
                "Request processing failed: de.robiasto.fragment_annotation_core.interfaces.field.FieldException: Equal Order value in namePrivate and name.",
                assertThrowsExactly(ServletException.class, () -> this.getResult(SortingTestController.INVALID_SORTING_TEST_PATH)).getMessage()
        );
    }

    @ParameterizedTest
    @MethodSource("provideValidTestData")
    void testDtoReturnsValidValues(List<String> expected, String path) throws Exception {
        Elements divs = this.getDocument(path).select("div");

        assertEquals(expected.get(0), divs.get(0).text());
        assertEquals(expected.get(1), divs.get(1).text());
    }
}
