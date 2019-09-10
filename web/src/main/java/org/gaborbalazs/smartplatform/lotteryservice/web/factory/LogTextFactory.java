package org.gaborbalazs.smartplatform.lotteryservice.web.factory;

import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.gaborbalazs.smartplatform.lotteryservice.web.wrapper.BufferedRequestWrapper;
import org.gaborbalazs.smartplatform.lotteryservice.web.wrapper.BufferedResponseWrapper;
import org.springframework.util.StringUtils;

public class LogTextFactory {

    private static final String DEFAULT_REQUEST_MESSAGE_PREFIX = "Incoming request [";
    private static final String DEFAULT_REQUEST_MESSAGE_SUFFIX = "]";

    private static final String DEFAULT_RESPONSE_MESSAGE_PREFIX = "Outgoing response [";
    private static final String DEFAULT_RESPONSE_MESSAGE_SUFFIX = "]";

    private boolean includeRequestQueryString = true;
    private boolean includeRequestClientInfo = true;
    private boolean includeRequestHeaders = true;
    private boolean includeRequestPayload = true;
    private String requestMessagePrefix = DEFAULT_REQUEST_MESSAGE_PREFIX;
    private String requestMessageSuffix = DEFAULT_REQUEST_MESSAGE_SUFFIX;

    private boolean includeResponseHeaders = true;
    private boolean includeResponsePayload = true;
    private String responseMessagePrefix = DEFAULT_RESPONSE_MESSAGE_PREFIX;
    private String responseMessageSuffix = DEFAULT_RESPONSE_MESSAGE_SUFFIX;

    public String createRequestLogText(BufferedRequestWrapper requestWrapper) {
        StringBuilder logBuilder = new StringBuilder(requestMessagePrefix);
        injectUri(requestWrapper, logBuilder);
        if (includeRequestQueryString) {
            injectQueryString(requestWrapper, logBuilder);
        }
        if (includeRequestClientInfo) {
            injectSeparator(logBuilder, true);
            injectClientInfo(requestWrapper, logBuilder);
        }
        if (includeRequestHeaders) {
            injectSeparator(logBuilder, true);
            Map<String, String> headers = createHeadersMap(requestWrapper);
            injectHeaders(headers, logBuilder);
        }
        if (includeRequestPayload) {
            injectSeparator(logBuilder, true);
            injectBody(logBuilder, requestWrapper.getBody());
        }
        logBuilder.append(requestMessageSuffix);
        return logBuilder.toString();
    }

    public String createResponseLogText(BufferedResponseWrapper responseWrapper) {
        boolean includedSomething = false;
        StringBuilder logBuilder = new StringBuilder(responseMessagePrefix);
        if (includeResponseHeaders) {
            includedSomething = true;
            Map<String, String> headers = createHeadersMap(responseWrapper);
            injectHeaders(headers, logBuilder);
        }
        if (includeResponsePayload) {
            injectSeparator(logBuilder, includedSomething);
            injectBody(logBuilder, responseWrapper.getBody());
        }
        logBuilder.append(responseMessageSuffix);
        return logBuilder.toString();
    }

    public void setIncludeRequestQueryString(boolean includeRequestQueryString) {
        this.includeRequestQueryString = includeRequestQueryString;
    }

    public void setIncludeRequestClientInfo(boolean includeRequestClientInfo) {
        this.includeRequestClientInfo = includeRequestClientInfo;
    }

    public void setIncludeRequestHeaders(boolean includeRequestHeaders) {
        this.includeRequestHeaders = includeRequestHeaders;
    }

    public void setIncludeRequestPayload(boolean includeRequestPayload) {
        this.includeRequestPayload = includeRequestPayload;
    }

    public void setRequestMessagePrefix(String requestMessagePrefix) {
        this.requestMessagePrefix = requestMessagePrefix;
    }

    public void setRequestMessageSuffix(String requestMessageSuffix) {
        this.requestMessageSuffix = requestMessageSuffix;
    }

    public void setIncludeResponseHeaders(boolean includeResponseHeaders) {
        this.includeResponseHeaders = includeResponseHeaders;
    }

    public void setIncludeResponsePayload(boolean includeResponsePayload) {
        this.includeResponsePayload = includeResponsePayload;
    }

    public void setResponseMessagePrefix(String responseMessagePrefix) {
        this.responseMessagePrefix = responseMessagePrefix;
    }

    public void setResponseMessageSuffix(String responseMessageSuffix) {
        this.responseMessageSuffix = responseMessageSuffix;
    }

    private void injectSeparator(StringBuilder logBuilder, boolean includedSomething) {
        if (includedSomething) {
            logBuilder.append("; ");
        }
    }

    private void injectUri(HttpServletRequest request, StringBuilder logBuilder) {
        logBuilder.append("uri=");
        logBuilder.append(request.getRequestURI());
    }

    private void injectQueryString(HttpServletRequest request, StringBuilder logBuilder) {
        if (!StringUtils.isEmpty(request.getQueryString())) {
            logBuilder.append("?");
            logBuilder.append(request.getQueryString());
        }
    }

    private void injectClientInfo(HttpServletRequest request, StringBuilder logBuilder) {
        logBuilder.append("client=");
        logBuilder.append(request.getRemoteAddr());
    }

    private void injectHeaders(Map<String, String> headers, StringBuilder logBuilder) {
        logBuilder.append("headers=[");
        headers.forEach((key, value) -> {
            logBuilder.append(key);
            logBuilder.append(":\"");
            logBuilder.append(value);
            logBuilder.append("\", ");
        });
        if (logBuilder.charAt(logBuilder.length() - 2) == ',') {
            logBuilder.delete(logBuilder.length() - 2, logBuilder.length());
        }
        logBuilder.append("]");
    }

    private void injectBody(StringBuilder logBuilder, String body) {
        logBuilder.append("payload=");
        logBuilder.append(body);
    }

    private Map<String, String> createHeadersMap(HttpServletRequest request) {
        Map<String, String> headers = new LinkedHashMap<>();
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            String header = request.getHeader(headerName);
            headers.put(headerName, header);
        }
        return headers;
    }

    private Map<String, String> createHeadersMap(HttpServletResponse response) {
        Map<String, String> headers = new LinkedHashMap<>();
        response.getHeaderNames().forEach(headerName -> headers.put(headerName, response.getHeader(headerName)));
        return headers;
    }
}
