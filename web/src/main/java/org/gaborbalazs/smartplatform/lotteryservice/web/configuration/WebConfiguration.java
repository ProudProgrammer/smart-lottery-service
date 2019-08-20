package org.gaborbalazs.smartplatform.lotteryservice.web.configuration;

import javax.servlet.http.HttpServletRequest;

import org.gaborbalazs.smartplatform.lotteryservice.common.context.RequestContext;
import org.gaborbalazs.smartplatform.lotteryservice.common.enums.HeaderParameterName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.context.annotation.RequestScope;

@Configuration
class WebConfiguration {

    @Autowired
    private HttpServletRequest httpServletRequest;

    @Bean
    @RequestScope
    RequestContext requestContext() {
        String consumerName = httpServletRequest.getHeader(HeaderParameterName.CONSUMER_NAME.getHeaderName());
        String requestId = httpServletRequest.getHeader(HeaderParameterName.REQUEST_ID.getHeaderName());
        return RequestContext.newBuilder()
                .withConsumerName(consumerName)
                .withRequestId(requestId)
                .withRequestURI(httpServletRequest.getRequestURI())
                .build();
    }
}
