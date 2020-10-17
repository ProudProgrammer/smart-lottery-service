package org.gaborbalazs.smartplatform.lotteryservice.web.domain;

import io.swagger.annotations.ApiModelProperty;

public final class QuantityAndPoolSize {

    @ApiModelProperty(value = "Quantity of drawn numbers", name = "quantity", required = true)
    private final int quantity;

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
