package org.gaborbalazs.smartplatform.lotteryservice.service.generator.domain;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Partition partition = (Partition) o;
        return occurrence == partition.occurrence &&
                lowerLimit == partition.lowerLimit &&
                upperLimit == partition.upperLimit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(occurrence, lowerLimit, upperLimit);
    }

    @Override
    public String toString() {
        return "Partition{" +
                "occurrence=" + occurrence +
                ", lowerLimit=" + lowerLimit +
                ", upperLimit=" + upperLimit +
                '}';
    }
}
