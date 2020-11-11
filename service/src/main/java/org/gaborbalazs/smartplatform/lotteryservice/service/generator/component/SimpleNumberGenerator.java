package org.gaborbalazs.smartplatform.lotteryservice.service.generator.component;

import org.gaborbalazs.smartplatform.lotteryservice.service.component.MessageResolver;
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

    private static final String MSG_QUANTITY_AND_UPPER_LIMIT_CANNOT_BE_SMALLER_THAN_1 = "validate.generator.quantityAndUpperLimitCannotBeSmallerThan1";
    private static final String MSG_POOL_SIZE_MUST_NOT_BE_LARGER_THAN = "validate.generator.poolSizeMustNotBeLargerThan";
    private static final String MSG_POOL_SIZE_MUST_BE_LARGER_THAN_QUANTITY = "validate.generator.poolSizeMustBeLargerThanQuantity";

    private static final int MAX_POOL_SIZE = 1000;

    private final Random random;
    private final MessageResolver messageResolver;
    private final Logger logger;

    SimpleNumberGenerator(Random threadLocalRandom, MessageResolver messageResolver, Logger logger) {
        this.random = threadLocalRandom;
        this.messageResolver = messageResolver;
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
            logger.error(messageResolver.withUSLocale(MSG_QUANTITY_AND_UPPER_LIMIT_CANNOT_BE_SMALLER_THAN_1));
            throw new IllegalArgumentException(messageResolver.withRequestLocale(MSG_QUANTITY_AND_UPPER_LIMIT_CANNOT_BE_SMALLER_THAN_1));
        } else if (poolSize > MAX_POOL_SIZE) {
            logger.error(messageResolver.withUSLocale(MSG_POOL_SIZE_MUST_NOT_BE_LARGER_THAN, MAX_POOL_SIZE, poolSize));
            throw new IllegalArgumentException(messageResolver.withRequestLocale(MSG_POOL_SIZE_MUST_NOT_BE_LARGER_THAN, MAX_POOL_SIZE, poolSize));
        } else if (poolSize <= quantity) {
            logger.error(messageResolver.withUSLocale(MSG_POOL_SIZE_MUST_NOT_BE_LARGER_THAN, quantity, poolSize));
            throw new IllegalArgumentException(messageResolver.withRequestLocale(MSG_POOL_SIZE_MUST_BE_LARGER_THAN_QUANTITY, quantity, poolSize));
        }
    }

    private void validateQuantityAndUpperLimit(int quantity, int upperLimit) throws IllegalArgumentException {
        if (quantity < 1 || upperLimit < 1) {
            logger.error(messageResolver.withUSLocale(MSG_QUANTITY_AND_UPPER_LIMIT_CANNOT_BE_SMALLER_THAN_1));
            throw new IllegalArgumentException(messageResolver.withRequestLocale(MSG_QUANTITY_AND_UPPER_LIMIT_CANNOT_BE_SMALLER_THAN_1));
        }
    }
}
