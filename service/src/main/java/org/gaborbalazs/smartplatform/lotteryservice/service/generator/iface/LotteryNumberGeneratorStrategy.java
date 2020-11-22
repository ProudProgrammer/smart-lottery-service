package org.gaborbalazs.smartplatform.lotteryservice.service.generator.iface;

import org.gaborbalazs.smartplatform.lotteryservice.service.enums.GeneratorType;

import java.util.List;

/**
 * Strategy for lottery number generation, for example default, experimental.
 */
public interface LotteryNumberGeneratorStrategy {

    /**
     * Shows which {@link GeneratorType} belongs to the strategy.
     *
     * @return generatorType
     */
    GeneratorType generatorType();

    /**
     * Lottery number generator method without number replacement.
     *
     * @param quantity is the quantity of drawn numbers
     * @param poolSize is the size of set of numbers
     * @return set of drawn numbers
     */
    List<Integer> generateWithoutReplacement(int quantity, int poolSize);

    /**
     * Lottery number generator method with number replacement.
     *
     * @param quantity   is the quantity of drawn numbers
     * @param upperLimit is the upper limit (exclusive) for generated numbers
     * @return set of drawn numbers
     */
    List<Integer> generateWitHReplacement(int quantity, int upperLimit);
}
