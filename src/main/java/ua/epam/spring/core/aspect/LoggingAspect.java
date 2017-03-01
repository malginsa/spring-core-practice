package ua.epam.spring.core.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

//@Aspect
@Component("loggingAspect")
public class LoggingAspect {

    private static final Logger LOG = Logger.getLogger(LoggingAspect.class);

    @Pointcut("execution(* *.logEvent(..))")
    public void allLogEventMethods() {
    }

    @Pointcut("allLogEventMethods() && within(*..*File*Logger)")
    public void logEventInsideFileLoggers() {
    }

    @Before("allLogEventMethods()")
    public void logBefore(JoinPoint joinPoint) {
        LOG.info("BEFORE: " + joinPoint.getTarget().getClass().getSimpleName()
                + " " + joinPoint.getSignature().getName());
    }

    @AfterReturning(
            pointcut = "allLogEventMethods()",
            returning = "retVal")
    public void logAfter(Object retVal) {
        LOG.info("Returned value: " + retVal);
    }

    @AfterThrowing(
            pointcut = "allLogEventMethods()",
            throwing = "ex")
    public void logAfterThrow(Throwable ex) {
        LOG.warn("Thrown: " + ex);
    }
}
