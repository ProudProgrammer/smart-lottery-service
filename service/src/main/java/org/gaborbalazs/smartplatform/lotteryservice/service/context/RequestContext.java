package org.gaborbalazs.smartplatform.lotteryservice.service.context;

import java.time.LocalDate;
import java.util.Locale;

import org.gaborbalazs.smartplatform.lotteryservice.service.enums.HeaderParameterName;
import org.springframework.core.style.ToStringCreator;

public class RequestContext {

    private final String consumerName;
    private final String requestId;
    private final String requestURI;
    private final Locale locale;

    public RequestContext(Builder builder) {
        this.consumerName = builder.consumerName;
        this.requestId = builder.requestId;
        this.requestURI = builder.requestURI;
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

    public Locale getLocale() {
        return locale;
    }

    @Override
    public String toString() {
        return new ToStringCreator(this)
                .append(HeaderParameterName.CONSUMER_NAME.getHeaderName(), consumerName)
                .append(HeaderParameterName.REQUEST_ID.getHeaderName(), requestId)
                .append("RequestURI", requestURI)
                .append(HeaderParameterName.LOCALE.getHeaderName(), locale)
                .toString();
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static final class Builder {
        private String consumerName;
        private String requestId;
        private String requestURI;
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

        public Builder withLocale(Locale locale) {
            this.locale = locale;
            return this;
        }

        public RequestContext build() {
            return new RequestContext(this);
        }
    }
}
