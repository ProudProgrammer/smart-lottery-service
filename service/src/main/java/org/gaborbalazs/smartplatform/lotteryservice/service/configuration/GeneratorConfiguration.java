package org.gaborbalazs.smartplatform.lotteryservice.service.configuration;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GeneratorConfiguration {

    @Bean
    public Random threadLocalRandom() {
        return ThreadLocalRandom.current();
    }
}
