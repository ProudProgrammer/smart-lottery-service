package org.gaborbalazs.smartplatform.lotteryservice.service.generator.iface;

import org.gaborbalazs.smartplatform.lotteryservice.service.domain.DrawnNumbers;

import java.util.SortedSet;

/**
 * Strategy for lottery number generation, for example default, experimental.
 */
public interface LotteryNumberGeneratorStrategy {

    /**
     * Lottery number generator method.
     *
     * @param quantity is the quantity of drawn numbers
     * @param poolSize is the size of set of numbers
     * @return set of drawn numbers
     */
    SortedSet<Integer> generate(int quantity, int poolSize) throws IllegalArgumentException;
}
