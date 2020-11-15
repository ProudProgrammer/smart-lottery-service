package org.gaborbalazs.smartplatform.lotteryservice.web.editor;

import org.gaborbalazs.smartplatform.lotteryservice.service.component.MessageResolver;
import org.gaborbalazs.smartplatform.lotteryservice.service.enums.GeneratorType;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import java.beans.PropertyEditorSupport;

@Component
class GeneratorTypeEditor extends PropertyEditorSupport {

    private static final String MSG_REQUEST_PARAM_CANNOT_BE_CONVERTED = "convert.requestParamCannotBeConvertedToGeneratorType";

    private final MessageResolver messageResolver;
    private final Logger logger;

    GeneratorTypeEditor(MessageResolver messageResolver, Logger logger) {
        this.messageResolver = messageResolver;
        this.logger = logger;
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        GeneratorType generatorType = GeneratorType.fromValue(text).orElseThrow(() -> {
            logger.error(messageResolver.withUSLocale(MSG_REQUEST_PARAM_CANNOT_BE_CONVERTED, text));
            return new IllegalArgumentException(messageResolver.withRequestLocale(MSG_REQUEST_PARAM_CANNOT_BE_CONVERTED, text));
        });
        setValue(generatorType);
    }
}
