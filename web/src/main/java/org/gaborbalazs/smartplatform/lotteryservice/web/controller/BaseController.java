package org.gaborbalazs.smartplatform.lotteryservice.web.controller;

import org.gaborbalazs.smartplatform.lotteryservice.service.enums.GeneratorType;
import org.gaborbalazs.smartplatform.lotteryservice.service.enums.LotteryType;
import org.gaborbalazs.smartplatform.lotteryservice.web.editor.GeneratorTypeEditor;
import org.gaborbalazs.smartplatform.lotteryservice.web.editor.LotteryTypeEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

public class BaseController {

    @InitBinder
    void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(LotteryType.class, new LotteryTypeEditor());
        webDataBinder.registerCustomEditor(GeneratorType.class, new GeneratorTypeEditor());
    }
}
