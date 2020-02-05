package org.gaborbalazs.smartplatform.lotteryservice.integrationtest.base;

import org.gaborbalazs.smartplatform.lotteryservice.service.enums.LotteryType;

public class LotteryNumberGeneratorTestBase extends TestBase {

    protected String getLotteryNumberGeneratorUrl(LotteryType lotteryType) {
        return "/lottery/" + lotteryType.getPathVariableName() + "/numbers/random";
    }
}
