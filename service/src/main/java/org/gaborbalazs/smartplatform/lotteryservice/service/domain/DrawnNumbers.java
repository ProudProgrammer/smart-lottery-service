package org.gaborbalazs.smartplatform.lotteryservice.service.domain;

import org.gaborbalazs.smartplatform.lotteryservice.service.enums.GeneratorType;

import java.util.Collections;
import java.util.Objects;
import java.util.SortedSet;

public final class DrawnNumbers {

    private final GeneratorType generatorType;
    private final SortedSet<Integer> drawnNumbers;

    public DrawnNumbers(GeneratorType generatorType, SortedSet<Integer> drawnNumbers) {
        this.generatorType = generatorType;
        this.drawnNumbers = drawnNumbers;
    }

    public GeneratorType getGeneratorType() {
        return generatorType;
    }

    public SortedSet<Integer> getDrawnNumbers() {
        return Collections.unmodifiableSortedSet(drawnNumbers);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DrawnNumbers that = (DrawnNumbers) o;
        return generatorType == that.generatorType &&
                Objects.equals(drawnNumbers, that.drawnNumbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(generatorType, drawnNumbers);
    }
}
