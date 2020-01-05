package org.gaborbalazs.smartplatform.lotteryservice.web.editor;

import org.gaborbalazs.smartplatform.lotteryservice.common.enums.GeneratorType;

import java.beans.PropertyEditorSupport;

public class GeneratorTypeEditor extends PropertyEditorSupport {

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        GeneratorType generatorType = GeneratorType.fromValue(text).orElseThrow(() -> new IllegalArgumentException("Request param cannot be converted to GeneratorType: " + text));
        setValue(generatorType);
    }
}
