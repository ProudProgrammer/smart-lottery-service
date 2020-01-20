package org.gaborbalazs.smartplatform.lotteryservice.web.editor;

import java.beans.PropertyEditorSupport;

import org.gaborbalazs.smartplatform.lotteryservice.service.enums.LotteryType;

public class LotteryTypeEditor extends PropertyEditorSupport {

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        LotteryType lotteryType = LotteryType.fromPathVariableName(text).orElseThrow(() -> new IllegalArgumentException("Path variable cannot be converted to LotteryType: " + text));
        setValue(lotteryType);
    }
}
