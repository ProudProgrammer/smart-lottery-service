package org.gaborbalazs.smartplatform.lotteryservice.service.configuration;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.RequestScope;

@Configuration
public class RandomConfiguration {

    @Bean
    @RequestScope
    Random threadLocalRandom() {
        return ThreadLocalRandom.current();
    }
}
