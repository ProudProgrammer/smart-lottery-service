package org.gaborbalazs.smartplatform.lotteryservice.application.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InjectionPoint;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
class GeneralConfiguration {

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    Logger logger(InjectionPoint injectionPoint) {
        return LoggerFactory.getLogger(injectionPoint.getMember().getDeclaringClass());
    }
}
