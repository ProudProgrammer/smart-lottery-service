package org.gaborbalazs.smartplatform.lotteryservice.service.generator.iface;

import java.util.Set;

public interface LotteryNumberGenerator {

    /**
     * Lottery number generator method
     * @param quantity is the quantity of drawn numbers
     * @param poolSize is the size of set of numbers
     * @return set of drawn numbers
     */
    Set<Integer> generate(int quantity, int poolSize) throws IllegalArgumentException;
}
