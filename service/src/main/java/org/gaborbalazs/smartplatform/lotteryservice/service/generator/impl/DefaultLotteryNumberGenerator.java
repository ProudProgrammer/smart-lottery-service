package org.gaborbalazs.smartplatform.lotteryservice.service.generator.impl;

import org.gaborbalazs.smartplatform.lotteryservice.service.domain.DrawnNumbers;
import org.gaborbalazs.smartplatform.lotteryservice.service.enums.GeneratorType;
import org.gaborbalazs.smartplatform.lotteryservice.service.generator.component.SimpleNumberGenerator;
import org.gaborbalazs.smartplatform.lotteryservice.service.generator.iface.LotteryNumberGeneratorStrategy;
import org.springframework.stereotype.Service;

@Service("defaultLotteryNumberGeneratorStrategy")
public class DefaultLotteryNumberGenerator implements LotteryNumberGeneratorStrategy {

    private final SimpleNumberGenerator simpleNumberGenerator;

    DefaultLotteryNumberGenerator(SimpleNumberGenerator simpleNumberGenerator) {
        this.simpleNumberGenerator = simpleNumberGenerator;
    }

    @Override
    public DrawnNumbers generate(int quantity, int poolSize) throws IllegalArgumentException {
        return new DrawnNumbers(GeneratorType.DEFAULT, simpleNumberGenerator.generate(quantity, poolSize));
    }
}
