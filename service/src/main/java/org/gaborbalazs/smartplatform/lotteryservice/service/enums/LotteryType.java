package org.gaborbalazs.smartplatform.lotteryservice.service.enums;

import java.util.List;
import java.util.Optional;

public enum LotteryType {
    FIVE_OUT_OF_NINETY(5, 90, "five-out-of-ninety"),
    SIX_OUT_OF_FORTY_FIVE(6, 45, "six-out-of-forty-five"),
    SCANDINAVIAN(7, 35, "scandinavian");

    private int quantity;
    private int pool;
    private String name;

    LotteryType(int quantity, int pool, String name) {
        this.quantity = quantity;
        this.pool = pool;
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getPool() {
        return pool;
    }

    public String getName() {
        return name;
    }

    public static Optional<LotteryType> fromName(String name) {
        return List.of(LotteryType.values()).stream().filter(lotteryType -> lotteryType.getName().equals(name)).findFirst();
    }
}
