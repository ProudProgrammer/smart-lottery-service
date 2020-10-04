package org.gaborbalazs.smartplatform.lotteryservice.service.generator.impl;

import org.gaborbalazs.smartplatform.lotteryservice.service.generator.component.ExperimentalFiveOutOfNinetyNumberGenerator;
import org.gaborbalazs.smartplatform.lotteryservice.service.generator.iface.LotteryNumberGeneratorStrategy;
import org.gaborbalazs.smartplatform.lotteryservice.service.generator.validator.ExperimentalLotteryNumberGeneratorValidator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("experimentalLotteryNumberGeneratorStrategy")
public class ExperimentalLotteryNumberGenerator implements LotteryNumberGeneratorStrategy {

    private final ExperimentalFiveOutOfNinetyNumberGenerator experimentalFiveOutOfNinetyNumberGenerator;
    private final ExperimentalLotteryNumberGeneratorValidator experimentalLotteryNumberGeneratorValidator;

    ExperimentalLotteryNumberGenerator(ExperimentalFiveOutOfNinetyNumberGenerator experimentalFiveOutOfNinetyNumberGenerator, ExperimentalLotteryNumberGeneratorValidator experimentalLotteryNumberGeneratorValidator) {
        this.experimentalFiveOutOfNinetyNumberGenerator = experimentalFiveOutOfNinetyNumberGenerator;
        this.experimentalLotteryNumberGeneratorValidator = experimentalLotteryNumberGeneratorValidator;
    }

    @Override
    public List<Integer> generateWithoutReplacement(int quantity, int poolSize) throws IllegalArgumentException, UnsupportedOperationException {
        experimentalLotteryNumberGeneratorValidator.validate(quantity, poolSize);
        return experimentalFiveOutOfNinetyNumberGenerator.generate();
    }

    @Override
    public List<Integer> generateWitHReplacement(int quantity, int upperLimit) throws UnsupportedOperationException {
        throw experimentalLotteryNumberGeneratorValidator.unsupportedOperation();
    }
}
