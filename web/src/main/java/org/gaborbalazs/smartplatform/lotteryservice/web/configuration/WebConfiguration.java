package org.gaborbalazs.smartplatform.lotteryservice.web.configuration;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.gaborbalazs.smartplatform.lotteryservice.service.context.RequestContext;
import org.gaborbalazs.smartplatform.lotteryservice.service.enums.HeaderParameterName;
import org.slf4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.RequestScope;

@Configuration
class WebConfiguration {

    private final HttpServletRequest httpServletRequest;
    private final Logger logger;

    WebConfiguration(HttpServletRequest httpServletRequest, Logger logger) {
        this.httpServletRequest = httpServletRequest;
        this.logger = logger;
    }

    @Bean
    @RequestScope
    RequestContext requestContext() {
        String consumerName = httpServletRequest.getHeader(HeaderParameterName.CONSUMER_NAME.getHeaderName());
        String requestId = httpServletRequest.getHeader(HeaderParameterName.REQUEST_ID.getHeaderName());
        Locale locale = getLocal(httpServletRequest.getHeader(HeaderParameterName.LOCALE.getHeaderName()));
        return RequestContext.newBuilder()
                .withConsumerName(consumerName)
                .withRequestId(requestId)
                .withRequestURI(httpServletRequest.getRequestURI())
                .withLocale(locale)
                .build();
    }

    private Locale getLocal(String localeAsString) {
        Locale locale;
        if (StringUtils.isNotBlank(localeAsString)) {
            locale = Locale.forLanguageTag(localeAsString);
            logger.debug("Request specifies locale. Locale will be used: " + locale.getDisplayName());
        } else {
            locale = Locale.getDefault();
            logger.debug("Request does not specify locale. Default locale will be used: " + locale.getDisplayName());
        }
        return locale;
    }
}
