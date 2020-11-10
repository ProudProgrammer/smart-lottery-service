package org.gaborbalazs.smartplatform.lotteryservice.web.domain;

import org.gaborbalazs.smartplatform.lotteryservice.service.enums.GeneratorType;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Objects;

public final class QuantityPoolSizeGeneratorTypeRequest {

    @Min(1)
    @Max(100)
    private final int quantity;

    @Min(2)
    @Max(1000)
    private final int poolSize;

    private final GeneratorType generatorType;

    public QuantityPoolSizeGeneratorTypeRequest(int quantity, int poolSize, GeneratorType generatorType) {
        this.quantity = quantity;
        this.poolSize = poolSize;
        this.generatorType = Objects.requireNonNullElse(generatorType, GeneratorType.DEFAULT);
    }

    public int getQuantity() {
        return quantity;
    }

    public int getPoolSize() {
        return poolSize;
    }

    public GeneratorType getGeneratorType() {
        return generatorType;
    }
}
