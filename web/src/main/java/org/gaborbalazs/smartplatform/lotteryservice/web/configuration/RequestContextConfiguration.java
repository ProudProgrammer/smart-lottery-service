package org.gaborbalazs.smartplatform.lotteryservice.web.configuration;

import org.gaborbalazs.smartplatform.lotteryservice.service.context.RequestContext;
import org.gaborbalazs.smartplatform.lotteryservice.service.enums.HeaderParameterName;
import org.gaborbalazs.smartplatform.lotteryservice.web.component.LocaleFactory;
import org.gaborbalazs.smartplatform.lotteryservice.web.component.RequestIdFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.RequestScope;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

/**
 * {@link RequestContext} configuration.
 * When RequestId header is blank UUID.randomUUID().toString() is used.
 * When Locale header is blank Locale.US is used.
 */
@Configuration
class RequestContextConfiguration {

    private final HttpServletRequest httpServletRequest;
    private final LocaleFactory localeFactory;
    private final RequestIdFactory requestIdFactory;

    RequestContextConfiguration(HttpServletRequest httpServletRequest, LocaleFactory localeFactory, RequestIdFactory requestIdFactory) {
        this.httpServletRequest = httpServletRequest;
        this.localeFactory = localeFactory;
        this.requestIdFactory = requestIdFactory;
    }

    @Bean
    @RequestScope
    RequestContext requestContext() {
        String consumerName = httpServletRequest.getHeader(HeaderParameterName.CONSUMER_NAME.getHeaderName());
        return RequestContext.newBuilder()
                .withConsumerName(consumerName)
                .withRequestId(getRequestId())
                .withRequestURI(httpServletRequest.getRequestURI())
                .withRequestQuery(httpServletRequest.getQueryString())
                .withLocale(getLocal())
                .build();
    }

    private String getRequestId() {
        String requestIdHeader = httpServletRequest.getHeader(HeaderParameterName.REQUEST_ID.getHeaderName());
        return requestIdFactory.create(requestIdHeader);
    }

    private Locale getLocal() {
        String localeAsString = httpServletRequest.getHeader(HeaderParameterName.LOCALE.getHeaderName());
        return localeFactory.create(localeAsString);
    }
}
