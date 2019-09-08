package org.gaborbalazs.smartplatform.lotteryservice.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.gaborbalazs.smartplatform.lotteryservice.common.enums.HeaderParameterName;
import org.slf4j.MDC;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class MdcLogEnhancerFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String consumerName = request.getHeader(HeaderParameterName.CONSUMER_NAME.getHeaderName());
        String requestId = request.getHeader(HeaderParameterName.REQUEST_ID.getHeaderName());
        MDC.put("consumerName", consumerName);
        MDC.put("requestId", requestId);
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
