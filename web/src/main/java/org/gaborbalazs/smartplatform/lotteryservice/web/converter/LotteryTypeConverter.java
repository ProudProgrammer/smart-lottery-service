package org.gaborbalazs.smartplatform.lotteryservice.web.converter;

import java.beans.PropertyEditorSupport;

import org.gaborbalazs.smartplatform.lotteryservice.service.enums.LotteryType;

public class LotteryTypeConverter extends PropertyEditorSupport {

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        LotteryType lotteryType = LotteryType.fromName(text).orElseThrow(() -> new IllegalArgumentException("Path variable cannot be converted to LotteryType: " + text));
        setValue(lotteryType);
    }
}
