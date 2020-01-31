package org.gaborbalazs.smartplatform.lotteryservice.service.generator.iface;

import org.gaborbalazs.smartplatform.lotteryservice.service.enums.GeneratorType;
import org.gaborbalazs.smartplatform.lotteryservice.service.enums.LotteryType;

import java.util.SortedSet;

public interface LotteryNumberGenerator {

    /**
     * Lottery number generator method based on lottery type.
     *
     * @param lotteryType   is the type of the lottery
     * @param generatorType is the type of the number generator
     * @return the drawn numbers
     * @throws UnsupportedOperationException if generator type does not support the type of lottery
     */
    SortedSet<Integer> generate(LotteryType lotteryType, GeneratorType generatorType) throws UnsupportedOperationException;

    /**
     * Lottery number generator method based on quantity and pool size.
     *
     * @param quantity      is the number of drawn numbers
     * @param poolSize      is the pool of numbers
     * @param generatorType is the type of the number generator
     * @return the drawn numbers
     * @throws IllegalArgumentException      if quantity is larger or equals than pool size
     * @throws IllegalArgumentException      if pool size is larger than 1000
     * @throws IllegalArgumentException      if quantity is 0
     * @throws UnsupportedOperationException if generator type does not support the type of lottery
     */
    SortedSet<Integer> generate(int quantity, int poolSize, GeneratorType generatorType) throws IllegalArgumentException, UnsupportedOperationException;
}
