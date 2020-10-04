package org.gaborbalazs.smartplatform.lotteryservice.integrationtest.component;

import java.util.Map;

import org.gaborbalazs.smartplatform.lotteryservice.service.enums.HeaderParameterName;
import org.springframework.stereotype.Component;

@Component
public class LotteryNumberGeneratorHeaderFactory {

    private static final Map<String, String> DEFAULT_REQUEST_HEADERS = Map.of(
            HeaderParameterName.REQUEST_ID.getValue(), "test0000-0000-0000-0000-test00000000",
            HeaderParameterName.CONSUMER_NAME.getValue(), "test",
            HeaderParameterName.LOCALE.getValue(), "hu_HU");

    public Map<String, String> getDefaultRequestHeaders() {
        return DEFAULT_REQUEST_HEADERS;
    }

    public String getDefaultRequestIdHeader() {
        return DEFAULT_REQUEST_HEADERS.get(HeaderParameterName.REQUEST_ID.getValue());
    }

    public String getDefaultConsumerNameHeader() {
        return DEFAULT_REQUEST_HEADERS.get(HeaderParameterName.CONSUMER_NAME.getValue());
    }

    public String getDefaultLocaleHeader() {
        return DEFAULT_REQUEST_HEADERS.get(HeaderParameterName.LOCALE.getValue());
    }
}
