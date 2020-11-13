package org.gaborbalazs.smartplatform.lotteryservice.web.validator;

import org.gaborbalazs.smartplatform.lotteryservice.service.component.MessageResolver;
import org.gaborbalazs.smartplatform.lotteryservice.service.enums.GeneratorType;
import org.gaborbalazs.smartplatform.lotteryservice.service.enums.LotteryType;
import org.gaborbalazs.smartplatform.lotteryservice.web.domain.LotteryTypeGeneratorTypeRequest;
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
class LotteryTypeGeneratorTypeRequestValidatorTest {

    private static final String MSG_ONLY_FIVE_OUT_OF_NINETY_LOTTERY_TYPE_SUPPORTED = "validate.generator.onlyFiveOutOfNinetyLotteryTypeSupported";
    private static final String RESOLVED_MESSAGE = "resolved message";

    @InjectMocks
    private LotteryTypeGeneratorTypeRequestValidator underTest;

    @Mock
    private MessageResolver messageResolver;

    @Mock
    private Logger logger;

    @Test
    void testShouldReturnTrueWhenGeneratorTypeIsExperimentalAndLotteryTypeIsFiveOutOfNinety() {
        LotteryType lotteryType = LotteryType.FIVE_OUT_OF_NINETY;
        GeneratorType generatorType = GeneratorType.EXPERIMENTAL;
        ConstraintValidatorContext validatorContext = Mockito.mock(ConstraintValidatorContext.class);
        LotteryTypeGeneratorTypeRequest request = createLotteryTypeGeneratorTypeRequest(lotteryType, generatorType);

        boolean result = underTest.isValid(request, validatorContext);

        assertTrue(result);
    }

    @Test
    void testShouldReturnTrueWhenGeneratorTypeIsDefault() {
        LotteryType lotteryType = LotteryType.SIX_OUT_OF_FORTY_FIVE;
        GeneratorType generatorType = GeneratorType.DEFAULT;
        ConstraintValidatorContext validatorContext = Mockito.mock(ConstraintValidatorContext.class);
        LotteryTypeGeneratorTypeRequest request = createLotteryTypeGeneratorTypeRequest(lotteryType, generatorType);

        boolean result = underTest.isValid(request, validatorContext);

        assertTrue(result);
    }

    @Test
    void testShouldReturnFalseAndSetConstraintViolationMessageWhenGeneratorTypeIsExperimentalButLotteryTypeIsNotFiveOutOfNinety() {
        LotteryType lotteryType = LotteryType.SCANDINAVIAN;
        GeneratorType generatorType = GeneratorType.EXPERIMENTAL;
        ConstraintValidatorContext validatorContext = Mockito.mock(ConstraintValidatorContext.class);
        ConstraintValidatorContext.ConstraintViolationBuilder constraintViolationBuilder = Mockito.mock(ConstraintValidatorContext.ConstraintViolationBuilder.class);
        LotteryTypeGeneratorTypeRequest request = createLotteryTypeGeneratorTypeRequest(lotteryType, generatorType);
        when(messageResolver.withUSLocale(MSG_ONLY_FIVE_OUT_OF_NINETY_LOTTERY_TYPE_SUPPORTED)).thenReturn(RESOLVED_MESSAGE);
        when(messageResolver.withRequestLocale(MSG_ONLY_FIVE_OUT_OF_NINETY_LOTTERY_TYPE_SUPPORTED)).thenReturn(RESOLVED_MESSAGE);
        when(validatorContext.buildConstraintViolationWithTemplate(RESOLVED_MESSAGE)).thenReturn(constraintViolationBuilder);

        boolean result = underTest.isValid(request, validatorContext);

        assertFalse(result);
        verify(logger).error(RESOLVED_MESSAGE);
        verify(validatorContext).disableDefaultConstraintViolation();
        verify(validatorContext).buildConstraintViolationWithTemplate(RESOLVED_MESSAGE);
        verify(constraintViolationBuilder).addConstraintViolation();
    }

    private LotteryTypeGeneratorTypeRequest createLotteryTypeGeneratorTypeRequest(LotteryType lotteryType, GeneratorType generatorType) {
        return new LotteryTypeGeneratorTypeRequest(lotteryType, generatorType);
    }
}