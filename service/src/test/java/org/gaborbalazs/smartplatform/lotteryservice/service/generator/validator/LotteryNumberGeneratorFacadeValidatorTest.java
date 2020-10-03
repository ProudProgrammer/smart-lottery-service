package org.gaborbalazs.smartplatform.lotteryservice.service.generator.validator;

import org.gaborbalazs.smartplatform.lotteryservice.service.context.RequestContext;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.springframework.context.MessageSource;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class LotteryNumberGeneratorFacadeValidatorTest {

    @InjectMocks
    LotteryNumberGeneratorFacadeValidator underTest;

    @Mock
    private MessageSource messageSource;

    @Mock
    private RequestContext requestContext;

    @Mock
    private Logger logger;

    @ParameterizedTest
    @CsvSource({"0,90", "5,0", "5,1001", "5,5", "6,5"})
    void testGenerateShouldThrowException(int quantity, int poolSize) {
        // GIVEN
        String msg = "msg";
        Class<IllegalArgumentException> expectedExceptionClass = IllegalArgumentException.class;

        // WHEN
        // THEN
        assertThrows(expectedExceptionClass, () -> underTest.validate(quantity, poolSize));
    }
}