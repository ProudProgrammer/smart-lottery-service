package org.gaborbalazs.smartplatform.lotteryservice.service.generator.component;

import org.gaborbalazs.smartplatform.lotteryservice.service.component.MessageResolver;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Component
class EvenOddNumberGenerator {

    private static final String MSG_UPPER_LIMIT_MUST_BE_LARGER_THAN_LOWER = "validate.generator.upperLimitMustBeLargerThanLower";

    private final Random random;
    private final ListShuffler listShuffler;
    private final MessageResolver messageResolver;
    private final Logger logger;

    public EvenOddNumberGenerator(Random random, ListShuffler listShuffler, MessageResolver messageResolver, Logger logger) {
        this.random = random;
        this.listShuffler = listShuffler;
        this.messageResolver = messageResolver;
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
            logger.error(messageResolver.withUSLocale(MSG_UPPER_LIMIT_MUST_BE_LARGER_THAN_LOWER, lowerLimit, upperLimit));
            throw new IllegalArgumentException(messageResolver.withRequestLocale(MSG_UPPER_LIMIT_MUST_BE_LARGER_THAN_LOWER, lowerLimit, upperLimit));
        }
    }
}
