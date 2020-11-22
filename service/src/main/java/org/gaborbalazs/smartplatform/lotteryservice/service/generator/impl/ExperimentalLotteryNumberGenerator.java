package org.gaborbalazs.smartplatform.lotteryservice.service.generator.impl;

import org.gaborbalazs.smartplatform.lotteryservice.service.enums.GeneratorType;
import org.gaborbalazs.smartplatform.lotteryservice.service.generator.component.ExperimentalFiveOutOfNinetyNumberGenerator;
import org.gaborbalazs.smartplatform.lotteryservice.service.generator.iface.LotteryNumberGeneratorStrategy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("experimentalLotteryNumberGeneratorStrategy")
class ExperimentalLotteryNumberGenerator implements LotteryNumberGeneratorStrategy {

    private static final GeneratorType GENERATOR_TYPE = GeneratorType.EXPERIMENTAL;

    private final ExperimentalFiveOutOfNinetyNumberGenerator experimentalFiveOutOfNinetyNumberGenerator;

    ExperimentalLotteryNumberGenerator(ExperimentalFiveOutOfNinetyNumberGenerator experimentalFiveOutOfNinetyNumberGenerator) {
        this.experimentalFiveOutOfNinetyNumberGenerator = experimentalFiveOutOfNinetyNumberGenerator;
    }

    @Override
    public GeneratorType generatorType() {
        return GENERATOR_TYPE;
    }

    @Override
    public List<Integer> generateWithoutReplacement(int quantity, int poolSize) throws UnsupportedOperationException {
        if (!(quantity == 5 && poolSize == 90)) {
            throw new UnsupportedOperationException("Not supported");
        }
        return experimentalFiveOutOfNinetyNumberGenerator.generate();
    }

    @Override
    public List<Integer> generateWitHReplacement(int quantity, int upperLimit) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Not supported");
    }
}
