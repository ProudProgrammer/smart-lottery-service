package org.gaborbalazs.smartplatform.lotteryservice.service.generator.impl;

import org.gaborbalazs.smartplatform.lotteryservice.service.enums.LotteryType;
import org.gaborbalazs.smartplatform.lotteryservice.service.generator.iface.LotteryNumberGeneratorStrategy;

import java.util.SortedSet;

public class LotteryNumberGenerator {

    private final LotteryNumberGeneratorStrategy lotteryNumberGeneratorStrategy;

    public LotteryNumberGenerator(LotteryNumberGeneratorStrategy lotteryNumberGeneratorStrategy) {
        this.lotteryNumberGeneratorStrategy = lotteryNumberGeneratorStrategy;
    }

    /**
     * Lottery number generator method
     *
     * @param lotteryType is the type of the lottery
     * @return set of drawn numbers
     */
    public SortedSet<Integer> generate(LotteryType lotteryType) {
        return lotteryNumberGeneratorStrategy.generate(lotteryType.getQuantity(), lotteryType.getPool());
    }
}
