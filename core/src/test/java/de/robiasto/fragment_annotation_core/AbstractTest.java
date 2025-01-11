package de.robiasto.fragment_annotation_core;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public abstract class AbstractTest {
    protected abstract MockMvc getMockMvc();

    protected Document getDocument(String path) throws Exception {
        ResultActions resultActions = this.getResult(path).andExpect(status().isOk());

        return Jsoup.parse(resultActions.andReturn().getResponse().getContentAsString());
    }

    protected ResultActions getResult(String path) throws Exception {
        return this.getMockMvc().perform(get(path));
    }
}
