package org.gaborbalazs.smartplatform.lotteryservice.web.controller;

import java.util.Set;

import org.gaborbalazs.smartplatform.lotteryservice.common.context.RequestContext;
import org.gaborbalazs.smartplatform.lotteryservice.service.enums.LotteryType;
import org.gaborbalazs.smartplatform.lotteryservice.service.generator.iface.LotteryNumberGenerator;
import org.gaborbalazs.smartplatform.lotteryservice.web.converter.LotteryTypeConverter;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lottery")
public class LotteryNumberGeneratorController {

    private RequestContext requestContext;
    private LotteryNumberGenerator lotteryNumberGenerator;

    LotteryNumberGeneratorController(RequestContext requestContext, LotteryNumberGenerator lotteryNumberGenerator) {
        this.requestContext = requestContext;
        this.lotteryNumberGenerator = lotteryNumberGenerator;
    }

    @GetMapping("/{lotteryType}/numbers/random")
    public Set<Integer> generateRandom(@PathVariable("lotteryType") LotteryType lotteryType) {
        return lotteryNumberGenerator.generate(lotteryType.getQuantity(), lotteryType.getPool());
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(LotteryType.class, new LotteryTypeConverter());
    }
}
