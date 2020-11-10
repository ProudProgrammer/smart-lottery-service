package org.gaborbalazs.smartplatform.lotteryservice.web.component;

import org.gaborbalazs.smartplatform.lotteryservice.service.context.RequestContext;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class MessageResolver {

    private final MessageSource messageSource;
    private final RequestContext requestContext;

    public MessageResolver(MessageSource messageSource, RequestContext requestContext) {
        this.messageSource = messageSource;
        this.requestContext = requestContext;
    }

    public String withUSLocale(String messageKey, Object... parameters) {
        return messageSource.getMessage(messageKey, parameters, Locale.US);
    }

    public String withRequestLocale(String messageKey, Object... parameters) {
        return messageSource.getMessage(messageKey, parameters, requestContext.getLocale());
    }
}
