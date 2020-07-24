package org.gaborbalazs.smartplatform.lotteryservice.service.generator.impl;

import org.gaborbalazs.smartplatform.lotteryservice.service.generator.component.JokerNumberGenerator;
import org.gaborbalazs.smartplatform.lotteryservice.service.generator.component.SimpleNumberGenerator;
import org.gaborbalazs.smartplatform.lotteryservice.service.generator.iface.LotteryNumberGeneratorStrategy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("defaultLotteryNumberGeneratorStrategy")
public class DefaultLotteryNumberGenerator implements LotteryNumberGeneratorStrategy {

    private final SimpleNumberGenerator simpleNumberGenerator;
    private final JokerNumberGenerator jokerNumberGenerator;

    DefaultLotteryNumberGenerator(SimpleNumberGenerator simpleNumberGenerator, JokerNumberGenerator jokerNumberGenerator) {
        this.simpleNumberGenerator = simpleNumberGenerator;
        this.jokerNumberGenerator = jokerNumberGenerator;
    }

    @Override
    public List<Integer> generateWithoutReplacement(int quantity, int poolSize) throws IllegalArgumentException {
        return simpleNumberGenerator.generateUniqueNumbersFromSamePool(quantity, poolSize);
    }

    @Override
    public List<Integer> generateWitHReplacement(int quantity, int upperLimit) throws IllegalArgumentException {
        return jokerNumberGenerator.generate(quantity, upperLimit);
    }
}
