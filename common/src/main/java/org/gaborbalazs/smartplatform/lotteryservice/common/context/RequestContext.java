package org.gaborbalazs.smartplatform.lotteryservice.common.context;

import org.gaborbalazs.smartplatform.lotteryservice.common.enums.HeaderParameterName;

public class RequestContext {

    private final String consumerName;
    private final String requestId;

    public RequestContext(String consumerName, String requestId) {
        this.consumerName = consumerName;
        this.requestId = requestId;
    }

    public String getConsumerName() {
        return consumerName;
    }

    public String getRequestId() {
        return requestId;
    }

    @Override
    public String toString() {
        return "RequestContext[" + HeaderParameterName.CONSUMER_NAME.getHeaderName() + ": " + consumerName + ", " + HeaderParameterName.REQUEST_ID.getHeaderName() + ": "
                + requestId + "]";
    }
}
