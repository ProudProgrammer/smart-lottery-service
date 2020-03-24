package org.gaborbalazs.smartplatform.lotteryservice.service.generator.component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.springframework.stereotype.Component;

@Component
class EvenOddNumberGenerator {

    private final Random random;
    private final ListShuffler listShuffler;
    private final MessageFactory messageFactory;
    private final Logger logger;

    EvenOddNumberGenerator(Random threadLocalRandom, ListShuffler listShuffler, MessageFactory messageFactory, Logger logger) {
        this.random = threadLocalRandom;
        this.listShuffler = listShuffler;
        this.messageFactory = messageFactory;
        this.logger = logger;
    }

    int generateEvenNumber(int lowerLimit, int upperLimit, Collection<Integer> exceptions) {
        List<Integer> evens = apply(true, lowerLimit, upperLimit);
        return generate(evens, exceptions);
    }

    int generateOddNumber(int lowerLimit, int upperLimit, Collection<Integer> exceptions) {
        List<Integer> odds = apply(false, lowerLimit, upperLimit);
        return generate(odds, exceptions);
    }

    private List<Integer> apply(boolean getEvenNumbers, int lowerLimit, int upperLimit) {
        validate(lowerLimit, upperLimit);
        List<Integer> result = new ArrayList<>();
        for (int i = lowerLimit; i <= upperLimit; i++) {
            if (getEvenNumbers && i % 2 == 0) {
                result.add(i);
            } else if (!getEvenNumbers && i % 2 != 0) {
                result.add(i);
            }
        }
        return result;
    }

    private int generate(List<Integer> numbers, Collection<Integer> exceptions) {
        numbers = numbers.stream().filter(number -> !exceptions.contains(number)).collect(Collectors.toList());
        numbers = listShuffler.shuffle(numbers);
        return numbers.get(random.nextInt(numbers.size()));
    }

    private void validate(int lowerLimit, int upperLimit) {
        if (upperLimit <= lowerLimit) {
            String msg = messageFactory.create("Upper limit must be larger than lower limit. Lower limit: {0}, upper limit: {1}", lowerLimit, upperLimit);
            logger.error(msg);
            throw new IllegalArgumentException(msg);
        }
    }
}
