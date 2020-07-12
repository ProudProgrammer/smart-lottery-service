package org.gaborbalazs.smartplatform.lotteryservice.application.aspect;

import java.util.List;
import java.util.stream.Collectors;

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
        List<Object> args = List.of(joinPoint.getArgs());
        logger.debug(BEFORE + joinPoint.getSignature().getName() + BRACKET_PREFIX + args.stream().map(Object::toString).collect(Collectors.joining(", ")) + BRACKET_SUFFIX);
        Object response = joinPoint.proceed();
        logger.debug(AFTER + joinPoint.getSignature().getName() + BRACKET_PREFIX + response + BRACKET_SUFFIX);
        return response;
    }
}
