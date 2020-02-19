package org.gaborbalazs.smartplatform.lotteryservice.integrationtest.base;

import org.gaborbalazs.smartplatform.lotteryservice.application.LotteryServiceApplication;
import org.gaborbalazs.smartplatform.lotteryservice.integrationtest.component.DocumentContextFactory;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = LotteryServiceApplication.class)
@AutoConfigureMockMvc
@ActiveProfiles("integration-test")
class TestBase {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected DocumentContextFactory documentContextFactory;
}
