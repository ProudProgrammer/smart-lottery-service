package org.gaborbalazs.smartplatform.lotteryservice.web.filter;

import org.gaborbalazs.smartplatform.lotteryservice.common.enums.HeaderParameterName;
import org.slf4j.MDC;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
class MdcLogEnhancerFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        setUpMDC(servletRequest);
        filterChain.doFilter(servletRequest, servletResponse);
        tearDownMDC();
    }

    private void setUpMDC(ServletRequest servletRequest) {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String consumerName = request.getHeader(HeaderParameterName.CONSUMER_NAME.getHeaderName());
        String requestId = request.getHeader(HeaderParameterName.REQUEST_ID.getHeaderName());
        MDC.put("consumerName", consumerName);
        MDC.put("requestId", requestId);
    }

    private void tearDownMDC() {
        MDC.remove("consumerName");
        MDC.remove("requestId");
    }
}
