package org.gaborbalazs.smartplatform.lotteryservice.web.validator;

import org.gaborbalazs.smartplatform.lotteryservice.service.component.MessageResolver;
import org.gaborbalazs.smartplatform.lotteryservice.service.enums.GeneratorType;
import org.gaborbalazs.smartplatform.lotteryservice.web.domain.QuantityPoolSizeGeneratorTypeRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;

import javax.validation.ConstraintValidatorContext;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class QuantityPoolSizeGeneratorTypeRequestValidatorTest {

    private static final String MSG_POOL_SIZE_MUST_BE_LARGER_THAN_QUANTITY = "validate.generator.poolSizeMustBeLargerThanQuantity";
    private static final String MSG_ONLY_QUANTITY_5_POOL_SIZE_90_SUPPORTED = "validate.generator.onlyQuantity5PoolSize90Supported";
    private static final String RESOLVED_MESSAGE = "resolved message";

    @InjectMocks
    private QuantityPoolSizeGeneratorTypeRequestValidator underTest;

    @Mock
    private MessageResolver messageResolver;

    @Mock
    private Logger logger;

    @Test
    void testShouldReturnTrueWhenGeneratorTypeIsExperimentalAndQuantityIs5AndPoolSizeIs90AndPoolSizeIsLargerThanQuantity() {
        int quantity = 5;
        int poolSize = 90;
        GeneratorType generatorType = GeneratorType.EXPERIMENTAL;
        ConstraintValidatorContext validatorContext = Mockito.mock(ConstraintValidatorContext.class);
        QuantityPoolSizeGeneratorTypeRequest request = createQuantityPoolSizeGeneratorTypeRequest(quantity, poolSize, generatorType);

        boolean result = underTest.isValid(request, validatorContext);

        assertTrue(result);
    }

    @Test
    void testShouldReturnTrueWhenGeneratorTypeIsDefaultAndPoolSizeIsLargerThanQuantity() {
        int quantity = 6;
        int poolSize = 45;
        GeneratorType generatorType = GeneratorType.DEFAULT;
        ConstraintValidatorContext validatorContext = Mockito.mock(ConstraintValidatorContext.class);
        QuantityPoolSizeGeneratorTypeRequest request = createQuantityPoolSizeGeneratorTypeRequest(quantity, poolSize, generatorType);

        boolean result = underTest.isValid(request, validatorContext);

        assertTrue(result);
    }

    @Test
    void testShouldReturnFalseAndSetConstraintViolationMessageWhenPoolSizeIsEqualsQuantity() {
        int quantity = 5;
        int poolSize = 5;
        GeneratorType generatorType = GeneratorType.DEFAULT;
        ConstraintValidatorContext validatorContext = Mockito.mock(ConstraintValidatorContext.class);
        ConstraintValidatorContext.ConstraintViolationBuilder constraintViolationBuilder = Mockito.mock(ConstraintValidatorContext.ConstraintViolationBuilder.class);
        QuantityPoolSizeGeneratorTypeRequest request = createQuantityPoolSizeGeneratorTypeRequest(quantity, poolSize, generatorType);
        when(messageResolver.withUSLocale(MSG_POOL_SIZE_MUST_BE_LARGER_THAN_QUANTITY, quantity, poolSize)).thenReturn(RESOLVED_MESSAGE);
        when(messageResolver.withRequestLocale(MSG_POOL_SIZE_MUST_BE_LARGER_THAN_QUANTITY, quantity, poolSize)).thenReturn(RESOLVED_MESSAGE);
        when(validatorContext.buildConstraintViolationWithTemplate(RESOLVED_MESSAGE)).thenReturn(constraintViolationBuilder);

        boolean result = underTest.isValid(request, validatorContext);

        assertFalse(result);
        verify(logger).error(RESOLVED_MESSAGE);
        verify(validatorContext).disableDefaultConstraintViolation();
        verify(validatorContext).buildConstraintViolationWithTemplate(RESOLVED_MESSAGE);
        verify(constraintViolationBuilder).addConstraintViolation();
    }

    @Test
    void testShouldReturnFalseAndSetConstraintViolationMessageWhenPoolSizeIsLessThanQuantity() {
        int quantity = 5;
        int poolSize = 4;
        GeneratorType generatorType = GeneratorType.DEFAULT;
        ConstraintValidatorContext validatorContext = Mockito.mock(ConstraintValidatorContext.class);
        ConstraintValidatorContext.ConstraintViolationBuilder constraintViolationBuilder = Mockito.mock(ConstraintValidatorContext.ConstraintViolationBuilder.class);
        QuantityPoolSizeGeneratorTypeRequest request = createQuantityPoolSizeGeneratorTypeRequest(quantity, poolSize, generatorType);
        when(messageResolver.withUSLocale(MSG_POOL_SIZE_MUST_BE_LARGER_THAN_QUANTITY, quantity, poolSize)).thenReturn(RESOLVED_MESSAGE);
        when(messageResolver.withRequestLocale(MSG_POOL_SIZE_MUST_BE_LARGER_THAN_QUANTITY, quantity, poolSize)).thenReturn(RESOLVED_MESSAGE);
        when(validatorContext.buildConstraintViolationWithTemplate(RESOLVED_MESSAGE)).thenReturn(constraintViolationBuilder);

        boolean result = underTest.isValid(request, validatorContext);

        assertFalse(result);
        verify(logger).error(RESOLVED_MESSAGE);
        verify(validatorContext).disableDefaultConstraintViolation();
        verify(validatorContext).buildConstraintViolationWithTemplate(RESOLVED_MESSAGE);
        verify(constraintViolationBuilder).addConstraintViolation();
    }

    @Test
    void testShouldReturnFalseAndSetConstraintViolationMessageWhenGeneratorTypeIsExperimentalAndQuantityIs5ButPoolSizeIsNot90() {
        int quantity = 5;
        int poolSize = 45;
        GeneratorType generatorType = GeneratorType.EXPERIMENTAL;
        ConstraintValidatorContext validatorContext = Mockito.mock(ConstraintValidatorContext.class);
        ConstraintValidatorContext.ConstraintViolationBuilder constraintViolationBuilder = Mockito.mock(ConstraintValidatorContext.ConstraintViolationBuilder.class);
        QuantityPoolSizeGeneratorTypeRequest request = createQuantityPoolSizeGeneratorTypeRequest(quantity, poolSize, generatorType);
        when(messageResolver.withUSLocale(MSG_ONLY_QUANTITY_5_POOL_SIZE_90_SUPPORTED, quantity, poolSize)).thenReturn(RESOLVED_MESSAGE);
        when(messageResolver.withRequestLocale(MSG_ONLY_QUANTITY_5_POOL_SIZE_90_SUPPORTED, quantity, poolSize)).thenReturn(RESOLVED_MESSAGE);
        when(validatorContext.buildConstraintViolationWithTemplate(RESOLVED_MESSAGE)).thenReturn(constraintViolationBuilder);

        boolean result = underTest.isValid(request, validatorContext);

        assertFalse(result);
        verify(logger).error(RESOLVED_MESSAGE);
        verify(validatorContext).disableDefaultConstraintViolation();
        verify(validatorContext).buildConstraintViolationWithTemplate(RESOLVED_MESSAGE);
        verify(constraintViolationBuilder).addConstraintViolation();
    }

    @Test
    void testShouldReturnFalseAndSetConstraintViolationMessageWhenGeneratorTypeIsExperimentalAndPoolSizeIs90ButQuantityIsNot5() {
        int quantity = 6;
        int poolSize = 90;
        GeneratorType generatorType = GeneratorType.EXPERIMENTAL;
        ConstraintValidatorContext validatorContext = Mockito.mock(ConstraintValidatorContext.class);
        ConstraintValidatorContext.ConstraintViolationBuilder constraintViolationBuilder = Mockito.mock(ConstraintValidatorContext.ConstraintViolationBuilder.class);
        QuantityPoolSizeGeneratorTypeRequest request = createQuantityPoolSizeGeneratorTypeRequest(quantity, poolSize, generatorType);
        when(messageResolver.withUSLocale(MSG_ONLY_QUANTITY_5_POOL_SIZE_90_SUPPORTED, quantity, poolSize)).thenReturn(RESOLVED_MESSAGE);
        when(messageResolver.withRequestLocale(MSG_ONLY_QUANTITY_5_POOL_SIZE_90_SUPPORTED, quantity, poolSize)).thenReturn(RESOLVED_MESSAGE);
        when(validatorContext.buildConstraintViolationWithTemplate(RESOLVED_MESSAGE)).thenReturn(constraintViolationBuilder);

        boolean result = underTest.isValid(request, validatorContext);

        assertFalse(result);
        verify(logger).error(RESOLVED_MESSAGE);
        verify(validatorContext).disableDefaultConstraintViolation();
        verify(validatorContext).buildConstraintViolationWithTemplate(RESOLVED_MESSAGE);
        verify(constraintViolationBuilder).addConstraintViolation();
    }

    private QuantityPoolSizeGeneratorTypeRequest createQuantityPoolSizeGeneratorTypeRequest(int quantity, int poolSize, GeneratorType generatorType) {
        return new QuantityPoolSizeGeneratorTypeRequest(quantity, poolSize, generatorType);
    }
}