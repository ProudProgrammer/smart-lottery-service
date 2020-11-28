package org.gaborbalazs.smartplatform.lotteryservice.service.generator.impl;

import org.gaborbalazs.smartplatform.lotteryservice.service.enums.GeneratorType;
import org.gaborbalazs.smartplatform.lotteryservice.service.generator.iface.LotteryNumberGeneratorStrategy;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
class LotteryNumberGeneratorStrategyProvider {

    private static final String MSG_NO_GENERATOR_FOUND = "No Generator found";

    private final Map<GeneratorType, LotteryNumberGeneratorStrategy> lotteryNumberGeneratorStrategies;

    LotteryNumberGeneratorStrategyProvider(Set<LotteryNumberGeneratorStrategy> lotteryNumberGeneratorStrategies) {
        this.lotteryNumberGeneratorStrategies = lotteryNumberGeneratorStrategies.stream().collect(Collectors.toMap(LotteryNumberGeneratorStrategy::generatorType, lotteryNumberGeneratorStrategy -> lotteryNumberGeneratorStrategy));
    }

    LotteryNumberGeneratorStrategy get(GeneratorType generatorType) {
        return Optional
                .ofNullable(lotteryNumberGeneratorStrategies.get(generatorType))
                .orElseThrow(() -> new IllegalArgumentException(MSG_NO_GENERATOR_FOUND));
    }
}
