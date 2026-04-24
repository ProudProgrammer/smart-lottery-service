package org.gaborbalazs.smartplatform.lotteryservice.web.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.gaborbalazs.smartplatform.lotteryservice.service.enums.HeaderParameterName;
import org.slf4j.MDC;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

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

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
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
