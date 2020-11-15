package org.gaborbalazs.smartplatform.lotteryservice.web.configuration;

import org.gaborbalazs.smartplatform.lotteryservice.service.context.RequestContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.FixedLocaleResolver;

/**
 * {@link LocaleResolver} configuration.
 * Validation API sends localized messages based on LocaleResolver.
 */
@Configuration
class LocaleResolverConfiguration {

    RequestContext requestContext;

    LocaleResolverConfiguration(RequestContext requestContext) {
        this.requestContext = requestContext;
    }

    @Bean
    @RequestScope
    LocaleResolver localeResolver() {
        return new FixedLocaleResolver(requestContext.getLocale());
    }
}
