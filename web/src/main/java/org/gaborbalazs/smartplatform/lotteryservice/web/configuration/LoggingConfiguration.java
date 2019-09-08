package org.gaborbalazs.smartplatform.lotteryservice.web.configuration;

import org.gaborbalazs.smartplatform.lotteryservice.web.factory.LogTextFactory;
import org.gaborbalazs.smartplatform.lotteryservice.web.filter.CustomLoggingFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoggingConfiguration {

    @Value("${customloggingfilter.logrequest:#{null}}")
    private Boolean logRequest;

    @Value("${customloggingfilter.logresponse:#{null}}")
    private Boolean logResponse;

    @Value("${customloggingfilter.request-querystring:#{null}}")
    private Boolean includeRequestQueryString;

    @Value("${customloggingfilter.request-clientinfo:#{null}}")
    private Boolean includeRequestClientInfo;

    @Value("${customloggingfilter.request-headers:#{null}}")
    private Boolean includeRequestHeaders;

    @Value("${customloggingfilter.request-payload:#{null}}")
    private Boolean includeRequestPayload;

    @Value("${customloggingfilter.request-messageprefix:#{null}}")
    private String requestMessagePrefix;

    @Value("${customloggingfilter.request-messagesuffix:#{null}}")
    private String requestMessageSuffix;

    @Value("${customloggingfilter.response-headers:#{null}}")
    private Boolean includeResponseHeaders;

    @Value("${customloggingfilter.response-payload:#{null}}")
    private Boolean includeResponsePayload;

    @Value("${customloggingfilter.response-messageprefix:#{null}}")
    private String responseMessagePrefix;

    @Value("${customloggingfilter.response-messagesuffix:#{null}}")
    private String responseMessageSuffix;

    @Bean
    CustomLoggingFilter customLoggingFilter() {
        CustomLoggingFilter customLoggingFilter = new CustomLoggingFilter(createLogTextFactory());
        if (logRequest != null) {
            customLoggingFilter.setLogRequest(logRequest);
        }
        if (logResponse != null) {
            customLoggingFilter.setLogResponse(logResponse);
        }
        return customLoggingFilter;
    }

    private LogTextFactory createLogTextFactory() {
        LogTextFactory logTextFactory = new LogTextFactory();
        if (includeRequestQueryString != null) {
            logTextFactory.setIncludeRequestQueryString(includeRequestQueryString);
        }
        if (includeRequestClientInfo != null) {
            logTextFactory.setIncludeRequestClientInfo(includeRequestClientInfo);
        }
        if (includeRequestHeaders != null) {
            logTextFactory.setIncludeRequestHeaders(includeRequestHeaders);
        }
        if (includeRequestPayload != null) {
            logTextFactory.setIncludeRequestPayload(includeRequestPayload);
        }
        if (requestMessagePrefix != null) {
            logTextFactory.setRequestMessagePrefix(requestMessagePrefix);
        }
        if (requestMessageSuffix != null) {
            logTextFactory.setRequestMessageSuffix(requestMessageSuffix);
        }
        if (includeResponseHeaders != null) {
            logTextFactory.setIncludeResponseHeaders(includeResponseHeaders);
        }
        if (includeResponsePayload != null) {
            logTextFactory.setIncludeResponsePayload(includeResponsePayload);
        }
        if (responseMessagePrefix != null) {
            logTextFactory.setResponseMessagePrefix(responseMessagePrefix);
        }
        if (responseMessageSuffix != null) {
            logTextFactory.setResponseMessageSuffix(responseMessageSuffix);
        }
        return logTextFactory;
    }
}
