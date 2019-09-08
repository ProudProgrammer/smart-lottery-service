package org.gaborbalazs.smartplatform.lotteryservice.web.controller;

import java.util.SortedSet;

import org.gaborbalazs.smartplatform.lotteryservice.service.enums.LotteryType;
import org.gaborbalazs.smartplatform.lotteryservice.service.generator.iface.LotteryNumberGenerator;
import org.gaborbalazs.smartplatform.lotteryservice.web.api.LotteryNumberGeneratorApi;
import org.gaborbalazs.smartplatform.lotteryservice.web.api.LotteryNumberGeneratorSwaggerApi;
import org.gaborbalazs.smartplatform.lotteryservice.web.editor.LotteryTypeEditor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
class LotteryNumberGeneratorController implements LotteryNumberGeneratorApi, LotteryNumberGeneratorSwaggerApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(LotteryNumberGeneratorController.class);

    private LotteryNumberGenerator lotteryNumberGenerator;

    LotteryNumberGeneratorController(LotteryNumberGenerator lotteryNumberGenerator) {
        this.lotteryNumberGenerator = lotteryNumberGenerator;
    }

    @Override
    public SortedSet<Integer> generateRandom(LotteryType lotteryType) {
        LOGGER.debug("Controller called - generateRandom()");

        return lotteryNumberGenerator.generate(lotteryType.getQuantity(), lotteryType.getPool());
    }

    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public Person test(@RequestBody Person person) {
        LOGGER.debug("Controller called - test()");

        Person retval = new Person();
        retval.setName("Retval " + person.getName());
        retval.setNickName("Retval " + person.getNickName());
        return retval;
    }

    @InitBinder
    void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(LotteryType.class, new LotteryTypeEditor());
    }
}
