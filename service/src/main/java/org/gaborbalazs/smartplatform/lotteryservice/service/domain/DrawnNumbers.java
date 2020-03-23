package org.gaborbalazs.smartplatform.lotteryservice.service.domain;

import org.gaborbalazs.smartplatform.lotteryservice.service.enums.GeneratorType;

import java.util.Collections;
import java.util.Objects;
import java.util.SortedSet;

public final class DrawnNumbers {

    private final String lotteryType;
    private final GeneratorType generatorType;
    private final SortedSet<Integer> drawnNumbers;

    private DrawnNumbers(Builder builder) {
        this.lotteryType = builder.lotteryType;
        this.generatorType = builder.generatorType;
        this.drawnNumbers = builder.drawnNumbers;
    }

    public String getLotteryType() {
        return lotteryType;
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
        return Objects.equals(lotteryType, that.lotteryType) &&
                generatorType == that.generatorType &&
                Objects.equals(drawnNumbers, that.drawnNumbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lotteryType, generatorType, drawnNumbers);
    }

    public static Builder newDrawnNumbers() {
        return new Builder();
    }

    public static final class Builder {
        private String lotteryType;
        private GeneratorType generatorType;
        private SortedSet<Integer> drawnNumbers;

        private Builder() {
        }

        public DrawnNumbers build() {
            return new DrawnNumbers(this);
        }

        public Builder lotteryType(String lotteryType) {
            this.lotteryType = lotteryType;
            return this;
        }

        public Builder generatorType(GeneratorType generatorType) {
            this.generatorType = generatorType;
            return this;
        }

        public Builder drawnNumbers(SortedSet<Integer> drawnNumbers) {
            this.drawnNumbers = drawnNumbers;
            return this;
        }
    }
}
