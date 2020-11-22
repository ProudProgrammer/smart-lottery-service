package org.gaborbalazs.smartplatform.lotteryservice.service.generator.impl;

import org.gaborbalazs.smartplatform.lotteryservice.service.enums.GeneratorType;
import org.gaborbalazs.smartplatform.lotteryservice.service.generator.iface.LotteryNumberGeneratorStrategy;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
class LotteryNumberGeneratorProcessor {

    private static final String MSG_NO_EXTRACTOR_FOUND = "No Extractor found";

    private final Map<GeneratorType, LotteryNumberGeneratorStrategy> lotteryNumberGeneratorStrategies;

    LotteryNumberGeneratorProcessor(Set<LotteryNumberGeneratorStrategy> lotteryNumberGeneratorStrategies) {
        this.lotteryNumberGeneratorStrategies = lotteryNumberGeneratorStrategies.stream().collect(Collectors.toMap(LotteryNumberGeneratorStrategy::generatorType, lotteryNumberGeneratorStrategy -> lotteryNumberGeneratorStrategy));
    }

    List<Integer> generateWithoutReplacement(int quantity, int poolSize, GeneratorType generatorType) {
        return Optional
                .ofNullable(lotteryNumberGeneratorStrategies.get(generatorType))
                .orElseThrow(() -> new IllegalArgumentException(MSG_NO_EXTRACTOR_FOUND))
                .generateWithoutReplacement(quantity, poolSize);
    }

    List<Integer> generateWitHReplacement(int quantity, int upperLimit, GeneratorType generatorType) {
        return Optional
                .ofNullable(lotteryNumberGeneratorStrategies.get(generatorType))
                .orElseThrow(() -> new IllegalArgumentException(MSG_NO_EXTRACTOR_FOUND))
                .generateWitHReplacement(quantity, upperLimit);
    }
}
