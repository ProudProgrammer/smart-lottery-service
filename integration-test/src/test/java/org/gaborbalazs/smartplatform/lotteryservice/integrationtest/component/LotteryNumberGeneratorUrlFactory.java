package org.gaborbalazs.smartplatform.lotteryservice.integrationtest.component;

import org.gaborbalazs.smartplatform.lotteryservice.service.enums.GeneratorType;
import org.gaborbalazs.smartplatform.lotteryservice.service.enums.LotteryType;
import org.springframework.stereotype.Component;

@Component
public class LotteryNumberGeneratorUrlFactory {

    private static final String BASE_PATH = "/lottery/";
    private static final String BASE_PATH_WITH_NUMBERS_AND_QUANTITY = "/lottery/numbers?quantity=";

    public String create(LotteryType lotteryType) {
        return create(lotteryType.getPathVariableName());
    }

    public String create(LotteryType lotteryType, GeneratorType generatorType) {
        return BASE_PATH + lotteryType.getPathVariableName() + "/numbers?generatorType=" + generatorType.getValue();
    }

    public String create(String lotteryType) {
        return BASE_PATH + lotteryType + "/numbers";
    }

    public String create(int quantity, int poolSize) {
        return BASE_PATH_WITH_NUMBERS_AND_QUANTITY + quantity + "&poolSize=" + poolSize;
    }

    public String create(int quantity, int poolSize, GeneratorType generatorType) {
        return BASE_PATH_WITH_NUMBERS_AND_QUANTITY + quantity + "&poolSize=" + poolSize + "&generatorType=" + generatorType.getValue();
    }
}
