package org.gaborbalazs.smartplatform.lotteryservice.web.exception;

interface ExceptionMessageExtractor<T extends Exception> {

    String MSG_INPUT_PARAMETER_NOT_APPROPRIATE = "validate.generator.inputParameterNotAppropriate";

    String extract(T exception);
}
