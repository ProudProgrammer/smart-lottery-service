package org.gaborbalazs.smartplatform.lotteryservice.service.generator.component;

import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class SimpleNumberGenerator {

    private final Random random;
    private final MessageFactory messageFactory;
    private final Logger logger;

    SimpleNumberGenerator(Random threadLocalRandom, MessageFactory messageFactory, Logger logger) {
        this.random = threadLocalRandom;
        this.messageFactory = messageFactory;
        this.logger = logger;
    }

    /**
     * Number generator method based on quantity and pool size.
     * It draws as many numbers as quantity from a pool with lower limit of 1 (inclusive) and upper limit of pool size (inclusive).
     *
     * @param quantity is the number of drawn numbers
     * @param poolSize is the upper limit (inclusive) of drawn numbers
     * @return is set of drawn numbers
     * @throws IllegalArgumentException if quantity is larger or equals than pool size
     * @throws IllegalArgumentException if pool size is larger than 1000
     * @throws IllegalArgumentException if quantity is 0
     */
    public List<Integer> generateUniqueNumbersFromSamePool(int quantity, int poolSize) throws IllegalArgumentException {
        return generateUniqueNumbersFromSamePool(quantity, 1, poolSize);
    }

    /**
     * Number generator method based on quantity, lower limit and upper limit.
     * It draws as many numbers as quantity from a pool with lower limit (inclusive) and upper limit (inclusive).
     *
     * @param quantity   is the number of drawn numbers
     * @param lowerLimit is the lower limit (inclusive) of drawn numbers
     * @param upperLimit is the upper limit (inclusive) of drawn numbers
     * @return is set of drawn numbers
     * @throws IllegalArgumentException if quantity is larger or equals than pool size
     * @throws IllegalArgumentException if pool size is larger than 1000
     * @throws IllegalArgumentException if quantity is 0
     */
    public List<Integer> generateUniqueNumbersFromSamePool(int quantity, int lowerLimit, int upperLimit) throws IllegalArgumentException {
        int poolSize = upperLimit - lowerLimit + 1;
        validateQuantityAndPoolSize(quantity, poolSize);
        List<Integer> result = new ArrayList<>();
        List<Integer> pool = createFilledPoolWithNumbersBetweenLimits(lowerLimit, upperLimit);
        int poolIndex;
        for (int i = 0; i < quantity; i++) {
            Collections.shuffle(pool, random);
            poolIndex = random.nextInt(poolSize - i);
            result.add(pool.remove(poolIndex));
        }
        Collections.sort(result);
        return result;
    }

    /**
     * Number generator method based on quantity and upper limit.
     * Numbers do not have to be unique.
     * It draws as many numbers as quantity from a pool with lower limit 0 (inclusive) and upper limit (exclusive).
     *
     * @param quantity   is the number of drawn numbers
     * @param upperLimit is the upper limit (inclusive) of drawn numbers
     * @return is list of drawn numbers
     * @throws IllegalArgumentException if quantity or upperLimit are negative
     */
    public List<Integer> generateWithRecurrence(int quantity, int upperLimit) throws IllegalArgumentException {
        validateQuantityAndUpperLimit(quantity, upperLimit);
        return Stream.generate(() -> random.nextInt(upperLimit)).limit(quantity).collect(Collectors.toList());
    }

    private List<Integer> createFilledPoolWithNumbersBetweenLimits(int lowerLimit, int upperLimit) {
        List<Integer> pool = new ArrayList<>(upperLimit - lowerLimit);
        for (int i = lowerLimit; i <= upperLimit; i++) {
            pool.add(i);
        }
        return pool;
    }

    private void validateQuantityAndPoolSize(int quantity, int poolSize) throws IllegalArgumentException {
        if (quantity < 1 || poolSize < 1) {
            String msg = "Quantity and upper limit can not be smaller than 1.";
            logger.error(msg);
            throw new IllegalArgumentException(msg);
        } else if (poolSize > 1000) {
            String msg = messageFactory.create("Pool size must not be larger than {0}. Pool size: {1}", 1000, poolSize);
            logger.error(msg);
            throw new IllegalArgumentException(msg);
        } else if (poolSize <= quantity) {
            String msg = messageFactory.create("Pool size must be larger than quantity. Quantity: {0}, pool size: {1}", quantity, poolSize);
            logger.error(msg);
            throw new IllegalArgumentException(msg);
        }
    }

    private void validateQuantityAndUpperLimit(int quantity, int upperLimit) throws IllegalArgumentException {
        if (quantity < 1 || upperLimit < 1) {
            String msg = "Quantity and upper limit can not be smaller than 1.";
            logger.error(msg);
            throw new IllegalArgumentException(msg);
        }
    }
}
