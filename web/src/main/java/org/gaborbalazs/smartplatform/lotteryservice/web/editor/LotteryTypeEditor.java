package org.gaborbalazs.smartplatform.lotteryservice.web.editor;

import org.gaborbalazs.smartplatform.lotteryservice.service.enums.LotteryType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.PropertyEditorSupport;

public class LotteryTypeEditor extends PropertyEditorSupport {

    private static final Logger LOGGER = LoggerFactory.getLogger(LotteryTypeEditor.class);

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        LotteryType lotteryType = LotteryType.fromPathVariableName(text).orElseThrow(() -> {
            String msg = "Path variable cannot be converted to LotteryType: " + text;
            LOGGER.error(msg);
            return new IllegalArgumentException(msg);
        });
        setValue(lotteryType);
    }
}
