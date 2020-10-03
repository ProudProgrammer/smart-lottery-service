package org.gaborbalazs.smartplatform.lotteryservice.service.generator.impl;

import org.gaborbalazs.smartplatform.lotteryservice.service.domain.GeneratedNumbers;
import org.gaborbalazs.smartplatform.lotteryservice.service.enums.GeneratorType;
import org.gaborbalazs.smartplatform.lotteryservice.service.enums.LotteryType;
import org.gaborbalazs.smartplatform.lotteryservice.service.generator.iface.LotteryNumberGenerator;
import org.gaborbalazs.smartplatform.lotteryservice.service.generator.iface.LotteryNumberGeneratorStrategy;
import org.gaborbalazs.smartplatform.lotteryservice.service.generator.validator.LotteryNumberGeneratorFacadeValidator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class LotteryNumberGeneratorFacade implements LotteryNumberGenerator {

    private final LotteryNumberGeneratorStrategy defaultLotteryNumberGeneratorStrategy;
    private final LotteryNumberGeneratorStrategy experimentalLotteryNumberGeneratorStrategy;
    private final LotteryNumberGeneratorFacadeValidator lotteryNumberGeneratorFacadeValidator;

    public LotteryNumberGeneratorFacade(LotteryNumberGeneratorStrategy defaultLotteryNumberGeneratorStrategy, LotteryNumberGeneratorStrategy experimentalLotteryNumberGeneratorStrategy, LotteryNumberGeneratorFacadeValidator lotteryNumberGeneratorFacadeValidator) {
        this.defaultLotteryNumberGeneratorStrategy = defaultLotteryNumberGeneratorStrategy;
        this.experimentalLotteryNumberGeneratorStrategy = experimentalLotteryNumberGeneratorStrategy;
        this.lotteryNumberGeneratorFacadeValidator = lotteryNumberGeneratorFacadeValidator;
    }

    @Override
    public GeneratedNumbers generate(LotteryType lotteryType, GeneratorType generatorType) throws UnsupportedOperationException {
        List<Integer> drawnNumbers;
        if (lotteryType == LotteryType.JOKER) {
            drawnNumbers = getLotteryNumberGeneratorStrategy(generatorType).generateWitHReplacement(lotteryType.getQuantity(), lotteryType.getPool());
        } else {
            drawnNumbers = getLotteryNumberGeneratorStrategy(generatorType).generateWithoutReplacement(lotteryType.getQuantity(), lotteryType.getPool());
        }
        return GeneratedNumbers.newGeneratedNumbers()
                .lotteryType(lotteryType.name())
                .generatorType(generatorType)
                .generatedNumbers(drawnNumbers)
                .build();
    }

    @Override
    public GeneratedNumbers generate(int quantity, int poolSize, GeneratorType generatorType) throws IllegalArgumentException, UnsupportedOperationException {
        lotteryNumberGeneratorFacadeValidator.validate(quantity, poolSize);
        List<Integer> drawnNumbers = getLotteryNumberGeneratorStrategy(generatorType).generateWithoutReplacement(quantity, poolSize);
        return GeneratedNumbers.newGeneratedNumbers()
                .lotteryType(quantity + "/" + poolSize)
                .generatorType(generatorType)
                .generatedNumbers(drawnNumbers)
                .build();
    }

    private LotteryNumberGeneratorStrategy getLotteryNumberGeneratorStrategy(GeneratorType generatorType) {
        LotteryNumberGeneratorStrategy lotteryNumberGeneratorStrategy;
        if (generatorType == GeneratorType.EXPERIMENTAL) {
            lotteryNumberGeneratorStrategy = experimentalLotteryNumberGeneratorStrategy;
        } else {
            lotteryNumberGeneratorStrategy = defaultLotteryNumberGeneratorStrategy;
        }
        return lotteryNumberGeneratorStrategy;
    }
}
