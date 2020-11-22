package org.gaborbalazs.smartplatform.lotteryservice.service.generator.impl;

import org.gaborbalazs.smartplatform.lotteryservice.service.domain.GeneratedNumbers;
import org.gaborbalazs.smartplatform.lotteryservice.service.enums.GeneratorType;
import org.gaborbalazs.smartplatform.lotteryservice.service.enums.LotteryType;
import org.gaborbalazs.smartplatform.lotteryservice.service.generator.iface.LotteryNumberGenerator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class LotteryNumberGeneratorFacade implements LotteryNumberGenerator {

    private final LotteryNumberGeneratorProcessor lotteryNumberGeneratorProcessor;

    LotteryNumberGeneratorFacade(LotteryNumberGeneratorProcessor lotteryNumberGeneratorProcessor) {
        this.lotteryNumberGeneratorProcessor = lotteryNumberGeneratorProcessor;
    }

    @Override
    public GeneratedNumbers generate(LotteryType lotteryType, GeneratorType generatorType) throws UnsupportedOperationException {
        List<Integer> drawnNumbers;
        if (lotteryType == LotteryType.JOKER) {
            drawnNumbers = lotteryNumberGeneratorProcessor.generateWitHReplacement(lotteryType.getQuantity(), lotteryType.getPool(), generatorType);
        } else {
            drawnNumbers = lotteryNumberGeneratorProcessor.generateWithoutReplacement(lotteryType.getQuantity(), lotteryType.getPool(), generatorType);
        }
        return GeneratedNumbers.newGeneratedNumbers()
                .lotteryType(lotteryType.name())
                .generatorType(generatorType)
                .generatedNumbers(drawnNumbers)
                .build();
    }

    @Override
    public GeneratedNumbers generate(int quantity, int poolSize, GeneratorType generatorType) throws IllegalArgumentException, UnsupportedOperationException {
        List<Integer> drawnNumbers = lotteryNumberGeneratorProcessor.generateWithoutReplacement(quantity, poolSize, generatorType);
        return GeneratedNumbers.newGeneratedNumbers()
                .lotteryType(quantity + "/" + poolSize)
                .generatorType(generatorType)
                .generatedNumbers(drawnNumbers)
                .build();
    }
}
