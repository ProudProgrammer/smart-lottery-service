package org.gaborbalazs.smartplatform.lotteryservice.web.domain;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

public final class QuantityAndPoolSize {

    @Min(1)
    @Max(100)
    @ApiModelProperty(value = "Quantity of drawn numbers", name = "quantity", required = true)
    private final int quantity;

    @Min(2)
    @Max(1000)
    @ApiModelProperty(value = "Pool size of numbers", name = "poolSize", required = true)
    private final int poolSize;

    public QuantityAndPoolSize(int quantity, int poolSize) {
        this.quantity = quantity;
        this.poolSize = poolSize;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getPoolSize() {
        return poolSize;
    }
}
