package org.gaborbalazs.smartplatform.lotteryservice.service.generator.iface;

import java.util.SortedSet;

import org.gaborbalazs.smartplatform.lotteryservice.service.enums.GeneratorType;
import org.gaborbalazs.smartplatform.lotteryservice.service.enums.LotteryType;

public interface LotteryNumberGenerator {

    /**
     * Lottery number generator method based on lottery type.
     *
     * @param lotteryType is the type of the lottery
     * @param generatorType is the type of the number generator
     * @return the drawn numbers
     * @throws IllegalArgumentException when quantity is larger or equals than pool size
     * @throws UnsupportedOperationException when the generator type does not support the type of lottery
     */
    SortedSet<Integer> generate(LotteryType lotteryType, GeneratorType generatorType) throws IllegalArgumentException, UnsupportedOperationException;

    /**
     * Lottery number generator method based on quantity and pool size.
     *
     * @param quantity is the number of drawn numbers
     * @param poolSize is the pool of numbers
     * @param generatorType is the type of the number generator
     * @return the drawn numbers
     * @throws IllegalArgumentException when quantity is larger or equals than pool size
     * @throws UnsupportedOperationException when the generator type does not support the type of lottery
     */
    SortedSet<Integer> generate(int quantity, int poolSize, GeneratorType generatorType) throws IllegalArgumentException, UnsupportedOperationException;
}
