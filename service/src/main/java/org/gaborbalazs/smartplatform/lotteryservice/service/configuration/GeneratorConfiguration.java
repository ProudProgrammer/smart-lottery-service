package org.gaborbalazs.smartplatform.lotteryservice.service.configuration;

import org.gaborbalazs.smartplatform.lotteryservice.service.generator.impl.DefaultLotteryNumberGeneratorStrategy;
import org.gaborbalazs.smartplatform.lotteryservice.service.generator.impl.ExperimentalLotteryNumberGeneratorStrategy;
import org.gaborbalazs.smartplatform.lotteryservice.service.generator.impl.LotteryNumberGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class GeneratorConfiguration {

    private DefaultLotteryNumberGeneratorStrategy defaultLotteryNumberGeneratorStrategy;
    private ExperimentalLotteryNumberGeneratorStrategy experimentalLotteryNumberGeneratorStrategy;

    GeneratorConfiguration(DefaultLotteryNumberGeneratorStrategy defaultLotteryNumberGeneratorStrategy, ExperimentalLotteryNumberGeneratorStrategy experimentalLotteryNumberGeneratorStrategy) {
        this.defaultLotteryNumberGeneratorStrategy = defaultLotteryNumberGeneratorStrategy;
        this.experimentalLotteryNumberGeneratorStrategy = experimentalLotteryNumberGeneratorStrategy;
    }

    @Bean
    LotteryNumberGenerator defaultLotteryNumberGenerator() {
        return new LotteryNumberGenerator(defaultLotteryNumberGeneratorStrategy);
    }

    @Bean
    LotteryNumberGenerator experimentalLotteryNumberGenerator() {
        return new LotteryNumberGenerator(experimentalLotteryNumberGeneratorStrategy);
    }
}
