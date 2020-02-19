package org.gaborbalazs.smartplatform.lotteryservice.integrationtest.component;

import java.io.UnsupportedEncodingException;

import org.junit.jupiter.api.Assertions;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MvcResult;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.Option;

@Component
public class DocumentContextFactory {

    public DocumentContext create(MvcResult mvcResult) {
        MockHttpServletResponse response = mvcResult.getResponse();
        Assertions.assertNotNull(response);
        String content = null;
        try {
            content = response.getContentAsString();
        } catch (UnsupportedEncodingException e) {
            Assertions.fail("Encoding not supported.");
        }
        Assertions.assertNotNull(content);
        return JsonPath
                .using(Configuration
                        .defaultConfiguration()
                        .addOptions(Option.SUPPRESS_EXCEPTIONS)
                        .addOptions(Option.DEFAULT_PATH_LEAF_TO_NULL))
                .parse(content);
    }
}
