package org.gaborbalazs.smartplatform.lotteryservice.service.enums;

public enum HeaderParameterName {

    CONSUMER_NAME("Consumer-Name"),
    REQUEST_ID("Request-Id"),
    LOCALE("Locale");

    private String value;

    HeaderParameterName(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
