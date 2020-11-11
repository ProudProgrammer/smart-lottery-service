package org.gaborbalazs.smartplatform.lotteryservice.web.editor;

import org.gaborbalazs.smartplatform.lotteryservice.service.component.MessageResolver;
import org.gaborbalazs.smartplatform.lotteryservice.service.enums.LotteryType;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import java.beans.PropertyEditorSupport;

@Component
public class LotteryTypeEditor extends PropertyEditorSupport {

    private static final String MSG_PATH_VARIABLE_CANNOT_BE_CONVERTED = "convert.pathVariableCannotBeConvertedToLotteryType";

    private final MessageResolver messageResolver;
    private final Logger logger;

    public LotteryTypeEditor(MessageResolver messageResolver, Logger logger) {
        this.messageResolver = messageResolver;
        this.logger = logger;
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        LotteryType lotteryType = LotteryType.fromPathVariableName(text).orElseThrow(() -> {
            logger.error(messageResolver.withUSLocale(MSG_PATH_VARIABLE_CANNOT_BE_CONVERTED, text));
            return new IllegalArgumentException(messageResolver.withRequestLocale(MSG_PATH_VARIABLE_CANNOT_BE_CONVERTED, text));
        });
        setValue(lotteryType);
    }
}
