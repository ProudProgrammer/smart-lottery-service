package org.gaborbalazs.smartplatform.lotteryservice.common.enums;

public enum HeaderParameterName {

    CONSUMER_NAME("Consumer-Name"),
    REQUEST_ID("Request-Id");

    private String headerName;

    HeaderParameterName(String headerName) {
        this.headerName = headerName;
    }

    public String getHeaderName() {
        return headerName;
    }
}
