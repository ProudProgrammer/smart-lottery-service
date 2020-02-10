package org.gaborbalazs.smartplatform.lotteryservice.service.enums;

public enum HeaderParameterName {

    CONSUMER_NAME("Consumer-Name"),
    REQUEST_ID("Request-Id"),
    GENERATOR_TYPE("Generator-Type"),
    LOCALE("Locale");

    private String headerName;

    HeaderParameterName(String headerName) {
        this.headerName = headerName;
    }

    public String getHeaderName() {
        return headerName;
    }
}
