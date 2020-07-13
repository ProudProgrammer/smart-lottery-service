package org.gaborbalazs.smartplatform.lotteryservice.service.enums;

import java.util.List;
import java.util.Optional;

public enum LotteryType {
    FIVE_OUT_OF_NINETY(5, 90, "five-out-of-ninety"),
    SIX_OUT_OF_FORTY_FIVE(6, 45, "six-out-of-forty-five"),
    SCANDINAVIAN(7, 35, "scandinavian"),
    JOKER(6, 10, "joker");

    private final int quantity;
    private final int pool;
    private final String pathVariableName;

    LotteryType(int quantity, int pool, String pathVariableName) {
        this.quantity = quantity;
        this.pool = pool;
        this.pathVariableName = pathVariableName;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getPool() {
        return pool;
    }

    public String getPathVariableName() {
        return pathVariableName;
    }

    public static Optional<LotteryType> fromPathVariableName(String pathVariableName) {
        return List.of(LotteryType.values()).stream().filter(lotteryType -> lotteryType.getPathVariableName().equals(pathVariableName)).findFirst();
    }
}
