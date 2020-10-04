package org.gaborbalazs.smartplatform.lotteryservice.web.controller;

import org.gaborbalazs.smartplatform.lotteryservice.service.enums.GeneratorType;
import org.gaborbalazs.smartplatform.lotteryservice.service.enums.LotteryType;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

import java.beans.PropertyEditorSupport;

@ControllerAdvice
public class InitBinderAdvice {

    private final PropertyEditorSupport lotteryTypeEditor;
    private final PropertyEditorSupport generatorTypeEditor;

    InitBinderAdvice(PropertyEditorSupport lotteryTypeEditor, PropertyEditorSupport generatorTypeEditor) {
        this.lotteryTypeEditor = lotteryTypeEditor;
        this.generatorTypeEditor = generatorTypeEditor;
    }

    @InitBinder
    void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(LotteryType.class, lotteryTypeEditor);
        webDataBinder.registerCustomEditor(GeneratorType.class, generatorTypeEditor);
    }
}
