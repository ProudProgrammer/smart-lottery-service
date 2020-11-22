package org.gaborbalazs.smartplatform.lotteryservice.web.exception;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
class ExceptionProcessor {

    private static final String POSTFIX = "MessageExtractor(.|\\s)*";
    private static final String MSG_NO_EXTRACTOR_FOUND = "No Extractor found";

    private final Map<String, ExceptionMessageExtractor> exceptionMessageExtractors;

    ExceptionProcessor(Set<ExceptionMessageExtractor> exceptionMessageExtractors) {
        this.exceptionMessageExtractors = exceptionMessageExtractors.stream().collect(Collectors.toMap(this::getClassName, extractor -> extractor));
    }

    String extract(Exception exception) {
        return Optional
                .ofNullable(exceptionMessageExtractors.get(exception.getClass().getSimpleName()))
                .orElseThrow(() -> new IllegalArgumentException(MSG_NO_EXTRACTOR_FOUND))
                .extract(exception);
    }

    private String getClassName(ExceptionMessageExtractor<?> extractor) {
        return extractor.getClass().getSimpleName().replaceAll(POSTFIX, StringUtils.EMPTY);
    }
}
