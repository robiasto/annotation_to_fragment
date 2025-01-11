package de.robiasto.app.infrastructure.templates.fragments.plain.pagination;

import de.robiasto.app.AbstractTest;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;
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
import static org.junit.jupiter.api.Assertions.assertTrue;

@WebMvcTest(PaginationTestController.class)
@ActiveProfiles("mvcTest")
@WithMockUser(username = "test", roles = "ADMIN")
class PaginationTest extends AbstractTest {

    @Autowired
    MockMvc mockMvc;


    @Override
    protected MockMvc getMockMvc() {
        return this.mockMvc;
    }

    @Test
    void testThatSummaryIsDisplayed() throws Exception {
        Document doc = this.getDocument(PaginationTestController.getPath(200, 10, 1, true, 5));

        assertEquals(1, doc.select("#pagination-summary").size());
    }

    @Test
    void testThatSummaryIsNotDisplayed() throws Exception {
        Document doc = this.getDocument(PaginationTestController.getPath(200, 10, 1, false, 5));

        assertEquals(0, doc.select("#pagination-summary").size());
    }

    @ParameterizedTest
    @MethodSource("provideDataTestSummaryText")
    void testSummaryText(String url, int firstModelShown, int lastModelShown, int allPages) throws Exception {
        Document doc = this.getDocument(url);

        assertEquals(
                String.format(
                        "Showing <strong>%s</strong> to <strong>%s</strong> of <strong>%s</strong> results",
                        firstModelShown,
                        lastModelShown,
                        allPages
                ),

                doc.select("#pagination-summary").html());
    }

    static Stream<Arguments> provideDataTestSummaryText() {
        return Stream.of(
                Arguments.of(
                        PaginationTestController.getPath(200, 10, 0, true, 5),
                        1, 10, 200
                ),
                Arguments.of(
                        PaginationTestController.getPath(100, 30, 0, true, 5),
                        1, 30, 100
                ),
                Arguments.of(
                        PaginationTestController.getPath(500, 5, 5, true, 5),
                        26, 30, 500
                )
        );
    }

    @ParameterizedTest
    @MethodSource("provideDataTestActivePages")
    void testActivePages(String url, int activePage, boolean prevIsActive, boolean nextIsActive) throws Exception {
        Document doc = this.getDocument(url);

        assertTrue(doc.select("#pagination-page-" + activePage).hasAttr("disabled"), "Active page should be " + activePage);
        assertEquals(prevIsActive, doc.select("#pagination-previous").hasAttr("disabled"), "Previous page should be " + prevIsActive);
        assertEquals(nextIsActive, doc.select("#pagination-next").hasAttr("disabled"), "Next page should be " + nextIsActive);
    }

    static Stream<Arguments> provideDataTestActivePages() {
        return Stream.of(
                Arguments.of(
                        PaginationTestController.getPath(200, 10, 0, true, 5),
                        1, true, false
                ),
                Arguments.of(
                        PaginationTestController.getPath(200, 10, 19, true, 5),
                        20, false, true
                ),
                Arguments.of(
                        PaginationTestController.getPath(100, 30, 1, true, 5),
                        2, false, false
                ),
                Arguments.of(
                        PaginationTestController.getPath(500, 5, 5, true, 5),
                        6, false, false
                )
        );
    }

    @Test
    void testDisplayedOnePage() throws Exception {
        Document doc = this.getDocument(PaginationTestController.getPath(10, 10, 0, true, 5));
        assertEquals(0, doc.select("[pagination-page]").size());
    }

    @ParameterizedTest
    @MethodSource("provideDataTestDisplayedPages")
    void testDisplayedPages(String url, int firstPage, int pagesDisplayed) throws Exception {
        Document doc = this.getDocument(url);
        Elements displayedPages = doc.select("[pagination-page]");

        assertEquals(pagesDisplayed, displayedPages.size(), "Number of displayed pages should be " + pagesDisplayed);
        assertEquals(String.valueOf(firstPage), displayedPages.first().attr("pagination-page"), "First page should be " + firstPage);
        assertEquals(String.valueOf(firstPage+pagesDisplayed-1), displayedPages.last().attr("pagination-page"), "Last page should be " + (firstPage+pagesDisplayed-1));
    }

    static Stream<Arguments> provideDataTestDisplayedPages() {
        return Stream.of(
                Arguments.of(
                        PaginationTestController.getPath(20, 10, 0, true, 5),
                        1, 2
                ),
                Arguments.of(
                        PaginationTestController.getPath(11, 10, 0, true, 5),
                        1, 2
                ),
                Arguments.of(
                        PaginationTestController.getPath(200, 10, 19, true, 5),
                        16, 5
                ),
                Arguments.of(
                        PaginationTestController.getPath(200, 10, 20, false, 6),
                        19, 6
                ),
                Arguments.of(
                        PaginationTestController.getPath(1000, 30, 5, true, 5),
                        4, 5
                ),
                Arguments.of(
                        PaginationTestController.getPath(500, 5, 5, false, 5),
                        4, 5
                )
        );
    }
}
