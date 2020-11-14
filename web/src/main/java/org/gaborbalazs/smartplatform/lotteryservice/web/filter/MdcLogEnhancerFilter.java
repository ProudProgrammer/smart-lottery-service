package org.gaborbalazs.smartplatform.lotteryservice.web.filter;

import org.gaborbalazs.smartplatform.lotteryservice.service.enums.HeaderParameterName;
import org.slf4j.MDC;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
class MdcLogEnhancerFilter implements Filter {

    private static final String CONSUMER_NAME = "consumerName";
    private static final String REQUEST_ID = "requestId";

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        setUpMDC(servletRequest);
        filterChain.doFilter(servletRequest, servletResponse);
        tearDownMDC();
    }

    private void setUpMDC(ServletRequest servletRequest) {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String consumerName = request.getHeader(HeaderParameterName.CONSUMER_NAME.getValue());
        String requestId = request.getHeader(HeaderParameterName.REQUEST_ID.getValue());
        MDC.put(CONSUMER_NAME, consumerName);
        MDC.put(REQUEST_ID, requestId);
    }

    private void tearDownMDC() {
        MDC.remove(CONSUMER_NAME);
        MDC.remove(REQUEST_ID);
    }
}
