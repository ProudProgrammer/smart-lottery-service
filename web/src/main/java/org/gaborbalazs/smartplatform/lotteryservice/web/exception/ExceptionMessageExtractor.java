package org.gaborbalazs.smartplatform.lotteryservice.web.exception;

public interface ExceptionMessageExtractor<T> {

    String extract(T exception);
}
