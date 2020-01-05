package org.gaborbalazs.smartplatform.lotteryservice.web.facade;

import org.gaborbalazs.smartplatform.lotteryservice.common.enums.GeneratorType;
import org.gaborbalazs.smartplatform.lotteryservice.common.enums.LotteryType;
import org.gaborbalazs.smartplatform.lotteryservice.service.generator.impl.LotteryNumberGenerator;
import org.springframework.stereotype.Component;

import java.util.SortedSet;

@Component
public class LotteryNumberGeneratorFacade {

    private LotteryNumberGenerator defaultLotteryNumberGenerator;
    private LotteryNumberGenerator experimentalLotteryNumberGenerator;

    public LotteryNumberGeneratorFacade(LotteryNumberGenerator defaultLotteryNumberGenerator, LotteryNumberGenerator experimentalLotteryNumberGenerator) {
        this.defaultLotteryNumberGenerator = defaultLotteryNumberGenerator;
        this.experimentalLotteryNumberGenerator = experimentalLotteryNumberGenerator;
    }

    public SortedSet<Integer> generate(LotteryType lotteryType, GeneratorType generatorType) {
        LotteryNumberGenerator lotteryNumberGenerator = getLotteryNumberGenerator(generatorType);
        return lotteryNumberGenerator.generate(lotteryType);
    }

    private LotteryNumberGenerator getLotteryNumberGenerator(GeneratorType generatorType) {
        LotteryNumberGenerator lotteryNumberGenerator;
        if (generatorType == GeneratorType.EXPERIMENTAL) {
            lotteryNumberGenerator = experimentalLotteryNumberGenerator;
        } else {
            lotteryNumberGenerator = defaultLotteryNumberGenerator;
        }
        return lotteryNumberGenerator;
    }
}
