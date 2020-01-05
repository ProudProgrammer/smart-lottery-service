package org.gaborbalazs.smartplatform.lotteryservice.common.enums;

import java.util.List;
import java.util.Optional;

public enum GeneratorType {
    DEFAULT("default"),
    EXPERIMENTAL("experimental");

    private String value;

    GeneratorType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Optional<GeneratorType> fromValue(String value) {
        return List.of(GeneratorType.values()).stream().filter(generatorType -> generatorType.getValue().equals(value)).findFirst();
    }
}
