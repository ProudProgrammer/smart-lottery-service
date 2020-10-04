package org.gaborbalazs.smartplatform.lotteryservice.service.context;

import org.gaborbalazs.smartplatform.lotteryservice.service.enums.HeaderParameterName;
import org.springframework.core.style.ToStringCreator;

import java.util.Locale;

public class RequestContext {

    private final String consumerName;
    private final String requestId;
    private final String requestURI;
    private final String requestQuery;
    private final Locale locale;

    public RequestContext(Builder builder) {
        this.consumerName = builder.consumerName;
        this.requestId = builder.requestId;
        this.requestURI = builder.requestURI;
        this.requestQuery = builder.requestQuery;
        this.locale = builder.locale;
    }

    public String getConsumerName() {
        return consumerName;
    }

    public String getRequestId() {
        return requestId;
    }

    public String getRequestURI() {
        return requestURI;
    }

    public String getRequestQuery() {
        return requestQuery;
    }

    public Locale getLocale() {
        return locale;
    }

    @Override
    public String toString() {
        return new ToStringCreator(this)
                .append(HeaderParameterName.CONSUMER_NAME.getValue(), consumerName)
                .append(HeaderParameterName.REQUEST_ID.getValue(), requestId)
                .append("RequestURI", requestURI)
                .append("RequestQuery", requestQuery)
                .append(HeaderParameterName.LOCALE.getValue(), locale)
                .toString();
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static final class Builder {
        private String consumerName;
        private String requestId;
        private String requestURI;
        private String requestQuery;
        private Locale locale;

        private Builder() {
        }

        public Builder withConsumerName(String consumerName) {
            this.consumerName = consumerName;
            return this;
        }

        public Builder withRequestId(String requestId) {
            this.requestId = requestId;
            return this;
        }

        public Builder withRequestURI(String requestURI) {
            this.requestURI = requestURI;
            return this;
        }

        public Builder withRequestQuery(String requestQuery) {
            this.requestQuery = requestQuery;
            return this;
        }

        public Builder withLocale(Locale locale) {
            this.locale = locale;
            return this;
        }

        public RequestContext build() {
            return new RequestContext(this);
        }
    }
}