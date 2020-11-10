package org.gaborbalazs.smartplatform.lotteryservice.service.generator.iface;

import org.gaborbalazs.smartplatform.lotteryservice.service.domain.GeneratedNumbers;
import org.gaborbalazs.smartplatform.lotteryservice.service.enums.GeneratorType;
import org.gaborbalazs.smartplatform.lotteryservice.service.enums.LotteryType;

public interface LotteryNumberGenerator {

    /**
     * Lottery number generator method based on lottery type.
     *
     * @param lotteryType   is the type of the lottery
     * @param generatorType is the type of the number generator
     * @return the drawn numbers
     */
    GeneratedNumbers generate(LotteryType lotteryType, GeneratorType generatorType);

    /**
     * Lottery number generator method based on quantity and pool size.
     *
     * @param quantity      is the number of drawn numbers
     * @param poolSize      is the pool of numbers
     * @param generatorType is the type of the number generator
     * @return the drawn numbers
     */
    GeneratedNumbers generate(int quantity, int poolSize, GeneratorType generatorType);
}
