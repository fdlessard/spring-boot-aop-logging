package io.fdlessard.codebites.aoplogging.core;


import java.util.Arrays;
import java.util.stream.Collectors;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.event.Level;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MethodLoggerAspect {

    @Around("@annotation(methodLogger)")
    public Object aroundAdvice(ProceedingJoinPoint joinPoint, MethodLogger methodLogger) throws Throwable {

        Logger logger = LoggerFactory.getLogger(joinPoint.getTarget().getClass());
        String methodName = getMethodName(joinPoint);

        if (methodLogger.logParameters()) {
            String methodAndArguments = getMethodAndArgumentsAsString(joinPoint, methodName);
            log(logger, methodLogger.logLevel(), methodAndArguments);
        } else {
            log(logger, methodLogger.logLevel(), methodName + "()");
        }

        Object returnValue = joinPoint.proceed();

        if (methodLogger.logResult() && !isVoidMethod(joinPoint)) {
            log(logger, methodLogger.logLevel(), methodName + "() - " + returnValue);
        }

        return returnValue;
    }

    private boolean isVoidMethod(ProceedingJoinPoint joinPoint) {
        return ((MethodSignature) joinPoint.getSignature()).getReturnType() == void.class;
    }

    private String getMethodAndArgumentsAsString(ProceedingJoinPoint joinPoint, String methodName) {
        return Arrays.stream(joinPoint.getArgs()).map(Object::toString)
                .collect(Collectors.joining(", ", methodName + "(", ")"));
    }

    private String getMethodName(ProceedingJoinPoint joinPoint) {
        return MethodSignature.class.cast(joinPoint.getSignature()).getMethod().getName();
    }

    private void log(Logger logger, Level level, String logMessage) {

        if (level == Level.INFO) {
            logger.info(logMessage);
        } else if (level == Level.DEBUG) {
            logger.debug(logMessage);
        } else if (level == Level.WARN) {
            logger.warn(logMessage);
        } else if (level == Level.ERROR) {
            logger.error(logMessage);
        } else if (level == Level.TRACE) {
            logger.trace(logMessage);
        }
    }
}