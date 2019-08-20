package org.gaborbalazs.smartplatform.lotteryservice.service.configuration;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class GeneratorConfiguration {

    @Bean
    Random threadLocalRandom() {
        return ThreadLocalRandom.current();
    }
}
