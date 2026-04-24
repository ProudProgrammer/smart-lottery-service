package org.gaborbalazs.smartplatform.lotteryservice.web.domain;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.gaborbalazs.smartplatform.lotteryservice.service.enums.GeneratorType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;

class QuantityPoolSizeGeneratorTypeRequestTest {

    private QuantityPoolSizeGeneratorTypeRequest underTest;

    private Validator validator;

    @BeforeEach
    void setUp() {
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            validator = factory.getValidator();
        }
    }

    @Test
    void testShouldHaveViolationWhenQuantityIsLessThan1() {
        // GIVEN
        int quantity = 0;
        int poolSize = 500;
        GeneratorType generatorType = GeneratorType.DEFAULT;
        underTest = new QuantityPoolSizeGeneratorTypeRequest(quantity, poolSize, generatorType);

        // WHEN
        Set<ConstraintViolation<QuantityPoolSizeGeneratorTypeRequest>> violations = validator.validate(underTest);

        // THEN
        assertFalse(violations.isEmpty());
    }

    @Test
    void testShouldHaveViolationWhenQuantityIsLargerThan100() {
        // GIVEN
        int quantity = 101;
        int poolSize = 500;
        GeneratorType generatorType = GeneratorType.DEFAULT;
        underTest = new QuantityPoolSizeGeneratorTypeRequest(quantity, poolSize, generatorType);

        // WHEN
        Set<ConstraintViolation<QuantityPoolSizeGeneratorTypeRequest>> violations = validator.validate(underTest);

        // THEN
        assertFalse(violations.isEmpty());
    }

    @Test
    void testShouldHaveViolationWhenPoolSizeIsLessThan2() {
        // GIVEN
        int quantity = 1;
        int poolSize = 1;
        GeneratorType generatorType = GeneratorType.DEFAULT;
        underTest = new QuantityPoolSizeGeneratorTypeRequest(quantity, poolSize, generatorType);

        // WHEN
        Set<ConstraintViolation<QuantityPoolSizeGeneratorTypeRequest>> violations = validator.validate(underTest);

        // THEN
        assertFalse(violations.isEmpty());
    }

    @Test
    void testShouldHaveViolationWhenPoolSizeIsLargerThan1000() {
        // GIVEN
        int quantity = 10;
        int poolSize = 1001;
        GeneratorType generatorType = GeneratorType.DEFAULT;
        underTest = new QuantityPoolSizeGeneratorTypeRequest(quantity, poolSize, generatorType);

        // WHEN
        Set<ConstraintViolation<QuantityPoolSizeGeneratorTypeRequest>> violations = validator.validate(underTest);

        // THEN
        assertFalse(violations.isEmpty());
    }
}