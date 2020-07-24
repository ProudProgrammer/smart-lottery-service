package org.gaborbalazs.smartplatform.lotteryservice.service.generator.component;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JokerNumberGenerator {

    private final SimpleNumberGenerator simpleNumberGenerator;

    JokerNumberGenerator(SimpleNumberGenerator simpleNumberGenerator) {
        this.simpleNumberGenerator = simpleNumberGenerator;
    }

    public List<Integer> generate(int quantity, int upperLimit) {
        return simpleNumberGenerator.generateWithRecurrence(quantity, upperLimit);
    }
}
