package org.gaborbalazs.smartplatform.lotteryservice.web.configuration;

import org.gaborbalazs.smartplatform.loggingfilter.configuration.LogConfiguration;
import org.gaborbalazs.smartplatform.loggingfilter.filter.CustomLoggingFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnExpression("${customloggingfilter.enabled:false}")
class FilterConfiguration {

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
        return new CustomLoggingFilter(createConfiguration());
    }

    private LogConfiguration createConfiguration() {
        LogConfiguration logConfiguration = new LogConfiguration();
        if (logRequest != null) {
            logConfiguration.setLogRequest(logRequest);
        }
        if (includeRequestQueryString != null) {
            logConfiguration.setIncludeRequestQueryString(includeRequestQueryString);
        }
        if (includeRequestClientInfo != null) {
            logConfiguration.setIncludeRequestClientInfo(includeRequestClientInfo);
        }
        if (includeRequestHeaders != null) {
            logConfiguration.setIncludeRequestHeaders(includeRequestHeaders);
        }
        if (includeRequestPayload != null) {
            logConfiguration.setIncludeRequestPayload(includeRequestPayload);
        }
        if (requestMessagePrefix != null) {
            logConfiguration.setRequestMessagePrefix(requestMessagePrefix);
        }
        if (requestMessageSuffix != null) {
            logConfiguration.setRequestMessageSuffix(requestMessageSuffix);
        }
        if (logResponse != null) {
            logConfiguration.setLogResponse(logResponse);
        }
        if (includeResponseHeaders != null) {
            logConfiguration.setIncludeResponseHeaders(includeResponseHeaders);
        }
        if (includeResponsePayload != null) {
            logConfiguration.setIncludeResponsePayload(includeResponsePayload);
        }
        if (responseMessagePrefix != null) {
            logConfiguration.setResponseMessagePrefix(responseMessagePrefix);
        }
        if (responseMessageSuffix != null) {
            logConfiguration.setResponseMessageSuffix(responseMessageSuffix);
        }
        return logConfiguration;
    }
}
