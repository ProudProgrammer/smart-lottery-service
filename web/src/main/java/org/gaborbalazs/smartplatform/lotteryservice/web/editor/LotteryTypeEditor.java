package org.gaborbalazs.smartplatform.lotteryservice.web.editor;

import org.gaborbalazs.smartplatform.lotteryservice.service.context.RequestContext;
import org.gaborbalazs.smartplatform.lotteryservice.service.enums.LotteryType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.beans.PropertyEditorSupport;
import java.util.Locale;

@Component
public class LotteryTypeEditor extends PropertyEditorSupport {

    private static final String MSG_PATH_VARIABLE_CANNOT_BE_CONVERTED = "convert.pathVariableCannotBeConvertedToLotteryType";

    private final MessageSource messageSource;
    private final RequestContext requestContext;
    private final Logger logger;

    LotteryTypeEditor(MessageSource messageSource, RequestContext requestContext, Logger logger) {
        this.messageSource = messageSource;
        this.requestContext = requestContext;
        this.logger = logger;
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        LotteryType lotteryType = LotteryType.fromPathVariableName(text).orElseThrow(() -> {
            logger.error(messageSource.getMessage(MSG_PATH_VARIABLE_CANNOT_BE_CONVERTED, new Object[]{text}, Locale.US));
            return new IllegalArgumentException(messageSource.getMessage(MSG_PATH_VARIABLE_CANNOT_BE_CONVERTED, new Object[]{text}, requestContext.getLocale()));
        });
        setValue(lotteryType);
    }
}
