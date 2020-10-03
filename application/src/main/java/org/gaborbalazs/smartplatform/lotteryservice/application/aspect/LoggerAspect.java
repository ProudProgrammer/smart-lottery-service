package org.gaborbalazs.smartplatform.lotteryservice.application.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
@ConditionalOnExpression("${loggeraspect.enabled:false}")
public class LoggerAspect {

    private static final String BEFORE = ">> ";
    private static final String AFTER = "<< ";
    private static final String BRACKET_PREFIX = "(";
    private static final String BRACKET_SUFFIX = ")";
    private static final String NULL_ARGUMENT = "null";
    private static final String DELIMITER_ARGUMENT = ", ";

    @Pointcut("within(org.gaborbalazs.smartplatform.lotteryservice.web..*)")
    private void inWebLayer() {
    }

    @Pointcut("within(org.gaborbalazs.smartplatform.lotteryservice.service..*)")
    private void inServiceLayer() {
    }

    @Pointcut("within(org.gaborbalazs.smartplatform.lotteryservice.betdao..*)")
    private void inBetDaoLayer() {
    }

    @Around("inWebLayer() || inServiceLayer()")
    Object aroundMethods(ProceedingJoinPoint joinPoint) throws Throwable {
        Logger logger = LoggerFactory.getLogger(joinPoint.getTarget().getClass());
        String arguments = getArgumentsAsString(joinPoint.getArgs());
        logger.debug(BEFORE + joinPoint.getSignature().getName() + BRACKET_PREFIX + arguments + BRACKET_SUFFIX);
        Object response = joinPoint.proceed();
        logger.debug(AFTER + joinPoint.getSignature().getName() + BRACKET_PREFIX + response + BRACKET_SUFFIX);
        return response;
    }

    private String getArgumentsAsString(Object[] arguments) {
        if (arguments == null) {
            return NULL_ARGUMENT;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < arguments.length; i++) {
            if (arguments[i] == null) {
                stringBuilder.append(NULL_ARGUMENT);
            } else {
                stringBuilder.append(arguments[i].toString());
            }
            if (i < arguments.length - 1) {
                stringBuilder.append(DELIMITER_ARGUMENT);
            }
        }
        return stringBuilder.toString();
    }
}
