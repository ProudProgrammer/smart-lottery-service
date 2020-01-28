package org.gaborbalazs.smartplatform.lotteryservice.service.generator.domain;

public final class Partition {

    private final int occurrence;
    private final int lowerLimit;
    private final int upperLimit;

    public Partition(int occurrence, int lowerLimit, int upperLimit) {
        this.occurrence = occurrence;
        this.lowerLimit = lowerLimit;
        this.upperLimit = upperLimit;
    }

    public int getOccurrence() {
        return occurrence;
    }

    public int getLowerLimit() {
        return lowerLimit;
    }

    public int getUpperLimit() {
        return upperLimit;
    }
}
