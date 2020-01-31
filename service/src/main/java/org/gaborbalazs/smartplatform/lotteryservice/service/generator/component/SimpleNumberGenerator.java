package org.gaborbalazs.smartplatform.lotteryservice.service.generator.component;

import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.SortedSet;
import java.util.TreeSet;

@Component
public class SimpleNumberGenerator {

    private final Random random;

    SimpleNumberGenerator(Random threadLocalRandom) {
        this.random = threadLocalRandom;
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
    public SortedSet<Integer> generate(int quantity, int poolSize) throws IllegalArgumentException {
        return generate(quantity, 1, poolSize);
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
    public SortedSet<Integer> generate(int quantity, int lowerLimit, int upperLimit) throws IllegalArgumentException {
        int poolSize = upperLimit - lowerLimit + 1;
        validate(quantity, poolSize);
        SortedSet<Integer> result = new TreeSet<>();
        List<Integer> pool = createFilledPoolWithNumbersBetweenLimits(lowerLimit, upperLimit);
        int poolIndex;
        for (int i = 0; i < quantity; i++) {
            Collections.shuffle(pool, random);
            poolIndex = random.nextInt(poolSize - i);
            result.add(pool.remove(poolIndex));
        }
        return result;
    }

    private List<Integer> createFilledPoolWithNumbersBetweenLimits(int lowerLimit, int upperLimit) {
        List<Integer> pool = new ArrayList<>(upperLimit - lowerLimit);
        for (int i = lowerLimit; i <= upperLimit; i++) {
            pool.add(i);
        }
        return pool;
    }

    private void validate(int quantity, int poolSize) throws IllegalArgumentException {
        if (quantity == 0) {
            throw new IllegalArgumentException("Quantity must not be 0.");
        } else if (poolSize > 1000) {
            String msg = MessageFormat.format("Pool size must not be larger than 1000. PoolSize: {0}", poolSize);
            throw new IllegalArgumentException(msg);
        } else if (poolSize <= quantity) {
            String msg = MessageFormat.format("Pool size must be larger than quantity. Quantity: {0}, PoolSize: {1}", quantity, poolSize);
            throw new IllegalArgumentException(msg);
        }
    }
}
