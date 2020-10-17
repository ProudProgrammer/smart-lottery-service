package org.gaborbalazs.smartplatform.lotteryservice.web.validator;

import org.gaborbalazs.smartplatform.lotteryservice.service.context.RequestContext;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.springframework.context.MessageSource;

@ExtendWith(MockitoExtension.class)
class QuantityAndPoolSizeValidatorTest {

    private static final String MSG_QUANTITY_MUST_NOT_BE_0 = "validate.generator.quantityMustNotBe0";
    private static final String MSG_POOL_SIZE_MUST_NOT_BE_0 = "validate.generator.poolSizeMustNotBe0";
    private static final String MSG_POOL_SIZE_MUST_NOT_BE_LARGER_THAN = "validate.generator.poolSizeMustNotBeLargerThan";
    private static final String MSG_POOL_SIZE_MUST_BE_LARGER_THAN_QUANTITY = "validate.generator.poolSizeMustBeLargerThanQuantity";

    @InjectMocks
    QuantityAndPoolSizeValidator underTest;

    @Mock
    private MessageSource messageSource;

    @Mock
    private RequestContext requestContext;

    @Mock
    private Logger logger;

    @Test
    void test() {

    }
}