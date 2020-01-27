package org.gaborbalazs.smartplatform.lotteryservice.service.generator.domain;

public final class Partition {

    private final int counter;
    private final int lowerLimit;
    private final int upperLimit;

    public Partition(int counter, int lowerLimit, int upperLimit) {
        this.counter = counter;
        this.lowerLimit = lowerLimit;
        this.upperLimit = upperLimit;
    }

    public int getCounter() {
        return counter;
    }

    public int getLowerLimit() {
        return lowerLimit;
    }

    public int getUpperLimit() {
        return upperLimit;
    }
}
