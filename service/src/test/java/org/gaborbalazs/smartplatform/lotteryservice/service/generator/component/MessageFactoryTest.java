package org.gaborbalazs.smartplatform.lotteryservice.service.generator.component;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Locale;

import org.gaborbalazs.smartplatform.lotteryservice.service.context.RequestContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MessageFactoryTest {

    private MessageFactory underTest;
    private RequestContext requestContext;

    @BeforeEach
    void setUp() {
        requestContext = RequestContext.newBuilder().withLocale(Locale.US).build();
        underTest = new MessageFactory(requestContext);
    }

    @Test
    void testCreate() {
        // GIVEN
        String pattern = "There are {0} steps between {1} and {2}.";
        int steps = 1000000;
        String a = "Somewhere";
        String b = "Nowhere";

        // WHEN
        var result = underTest.create(pattern, steps, a, b);

        // THEN
        assertEquals("There are 1,000,000 steps between Somewhere and Nowhere.", result);
    }
}