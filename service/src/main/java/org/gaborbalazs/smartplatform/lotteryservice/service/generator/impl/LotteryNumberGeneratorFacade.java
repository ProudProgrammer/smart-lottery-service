package org.gaborbalazs.smartplatform.lotteryservice.service.generator.impl;

import org.gaborbalazs.smartplatform.lotteryservice.service.domain.GeneratedNumbers;
import org.gaborbalazs.smartplatform.lotteryservice.service.enums.GeneratorType;
import org.gaborbalazs.smartplatform.lotteryservice.service.enums.LotteryType;
import org.gaborbalazs.smartplatform.lotteryservice.service.generator.iface.LotteryNumberGenerator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class LotteryNumberGeneratorFacade implements LotteryNumberGenerator {

    private final LotteryNumberGeneratorStrategyProvider lotteryNumberGeneratorStrategyProvider;

    LotteryNumberGeneratorFacade(LotteryNumberGeneratorStrategyProvider lotteryNumberGeneratorStrategyProvider) {
        this.lotteryNumberGeneratorStrategyProvider = lotteryNumberGeneratorStrategyProvider;
    }

    @Override
    public GeneratedNumbers generate(LotteryType lotteryType, GeneratorType generatorType) throws UnsupportedOperationException {
        List<Integer> drawnNumbers;
        if (lotteryType == LotteryType.JOKER) {
            drawnNumbers = lotteryNumberGeneratorStrategyProvider.get(generatorType).generateWitHReplacement(lotteryType.getQuantity(), lotteryType.getPool());
        } else {
            drawnNumbers = lotteryNumberGeneratorStrategyProvider.get(generatorType).generateWithoutReplacement(lotteryType.getQuantity(), lotteryType.getPool());
        }
        return GeneratedNumbers.newGeneratedNumbers()
                .lotteryType(lotteryType.name())
                .generatorType(generatorType)
                .generatedNumbers(drawnNumbers)
                .build();
    }

    @Override
    public GeneratedNumbers generate(int quantity, int poolSize, GeneratorType generatorType) throws IllegalArgumentException, UnsupportedOperationException {
        List<Integer> drawnNumbers = lotteryNumberGeneratorStrategyProvider.get(generatorType).generateWithoutReplacement(quantity, poolSize);
        return GeneratedNumbers.newGeneratedNumbers()
                .lotteryType(quantity + "/" + poolSize)
                .generatorType(generatorType)
                .generatedNumbers(drawnNumbers)
                .build();
    }
}
