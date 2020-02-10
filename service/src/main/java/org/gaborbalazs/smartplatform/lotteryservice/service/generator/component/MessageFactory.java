package org.gaborbalazs.smartplatform.lotteryservice.service.generator.component;

import java.text.MessageFormat;

import org.gaborbalazs.smartplatform.lotteryservice.service.context.RequestContext;
import org.springframework.stereotype.Component;

@Component
public class MessageFactory {

    private RequestContext requestContext;

    MessageFactory(RequestContext requestContext) {
        this.requestContext = requestContext;
    }

    public String create(String pattern, Object... arguments) {
        MessageFormat messageFormat = new MessageFormat(pattern, requestContext.getLocale());
        return messageFormat.format(arguments);
    }
}
