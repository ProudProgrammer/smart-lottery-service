package org.gaborbalazs.smartplatform.lotteryservice.web.component;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RequestIdFactory {

    private final Logger logger;

    RequestIdFactory(Logger logger) {
        this.logger = logger;
    }

    public String create(String requestId) {
        if (StringUtils.isBlank(requestId)) {
            requestId = UUID.randomUUID().toString();
            logger.debug("RequestId is not specified. RequestId has been generated: " + requestId);
        } else {
            logger.debug("RequestId is specified: " + requestId);
        }
        return requestId;
    }
}
