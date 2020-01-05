package org.gaborbalazs.smartplatform.lotteryservice.service.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@Configuration
public class RandomConfiguration {

    @Bean
    Random threadLocalRandom() {
        return ThreadLocalRandom.current();
    }
}
