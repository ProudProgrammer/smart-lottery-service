package org.gaborbalazs.smartplatform.lotteryservice.service.domain;

import org.gaborbalazs.smartplatform.lotteryservice.service.enums.GeneratorType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public final class GeneratedNumbers {

    private final String lotteryType;
    private final GeneratorType generatorType;
    private final List<Integer> generatedNumbers;

    private GeneratedNumbers(Builder builder) {
        this.lotteryType = builder.lotteryType;
        this.generatorType = builder.generatorType;
        this.generatedNumbers = builder.generatedNumbers;
    }

    public String getLotteryType() {
        return lotteryType;
    }

    public GeneratorType getGeneratorType() {
        return generatorType;
    }

    public List<Integer> getGeneratedNumbers() {
        return Collections.unmodifiableList(generatedNumbers);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GeneratedNumbers that = (GeneratedNumbers) o;
        return lotteryType.equals(that.lotteryType) &&
                generatorType == that.generatorType &&
                generatedNumbers.equals(that.generatedNumbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lotteryType, generatorType, generatedNumbers);
    }

    public static Builder newGeneratedNumbers() {
        return new Builder();
    }

    public static final class Builder {
        private String lotteryType;
        private GeneratorType generatorType;
        private List<Integer> generatedNumbers;

        private Builder() {
        }

        public GeneratedNumbers build() {
            return new GeneratedNumbers(this);
        }

        public Builder lotteryType(String lotteryType) {
            this.lotteryType = lotteryType;
            return this;
        }

        public Builder generatorType(GeneratorType generatorType) {
            this.generatorType = generatorType;
            return this;
        }

        public Builder generatedNumbers(List<Integer> generatedNumbers) {
            this.generatedNumbers = new ArrayList<>(generatedNumbers);
            return this;
        }
    }
}
