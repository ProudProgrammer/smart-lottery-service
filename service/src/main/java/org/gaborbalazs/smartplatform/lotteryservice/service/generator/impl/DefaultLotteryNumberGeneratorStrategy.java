package org.gaborbalazs.smartplatform.lotteryservice.service.generator.impl;

import org.gaborbalazs.smartplatform.lotteryservice.service.generator.iface.LotteryNumberGeneratorStrategy;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.*;

@Service
public class DefaultLotteryNumberGeneratorStrategy implements LotteryNumberGeneratorStrategy {

    private Random random;

    DefaultLotteryNumberGeneratorStrategy(Random threadLocalRandom) {
        this.random = threadLocalRandom;
    }

    @Override
    public SortedSet<Integer> generate(int quantity, int poolSize) throws IllegalArgumentException {
        if (!validate(quantity, poolSize)) {
            String msg = MessageFormat.format("Pool size must be larger than quantity! Quantity: {0}, PoolSize: {1}", quantity, poolSize);
            throw new IllegalArgumentException(msg);
        }
        SortedSet<Integer> result = new TreeSet<>();
        List<Integer> pool = createFilledPool(poolSize);
        int poolIndex;
        for (int i = 0; i < quantity; i++) {
            Collections.shuffle(pool);
            poolIndex = random.nextInt(poolSize - i);
            result.add(pool.get(poolIndex));
            pool.remove(poolIndex);
        }
        return result;
    }

    private List<Integer> createFilledPool(int poolSize) {
        List<Integer> pool = new ArrayList<>(poolSize);
        for (int i = 0; i < poolSize; i++) {
            pool.add(i + 1);
        }
        return pool;
    }

    private boolean validate(int quantity, int poolSize) {
        return poolSize > quantity;
    }
}
