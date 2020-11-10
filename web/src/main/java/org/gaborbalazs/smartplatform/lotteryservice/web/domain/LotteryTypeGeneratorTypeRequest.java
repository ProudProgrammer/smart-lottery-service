package org.gaborbalazs.smartplatform.lotteryservice.web.domain;

import org.gaborbalazs.smartplatform.lotteryservice.service.enums.GeneratorType;
import org.gaborbalazs.smartplatform.lotteryservice.service.enums.LotteryType;

import java.util.Objects;

public class LotteryTypeGeneratorTypeRequest {

    private LotteryType lotteryType;
    private GeneratorType generatorType;

    public LotteryTypeGeneratorTypeRequest(LotteryType lotteryType, GeneratorType generatorType) {
        this.lotteryType = lotteryType;
        this.generatorType = Objects.requireNonNullElse(generatorType, GeneratorType.DEFAULT);
    }

    public LotteryType getLotteryType() {
        return lotteryType;
    }

    public GeneratorType getGeneratorType() {
        return generatorType;
    }

    public void setLotteryType(LotteryType lotteryType) {
        this.lotteryType = lotteryType;
    }

    public void setGeneratorType(GeneratorType generatorType) {
        this.generatorType = generatorType;
    }
}
