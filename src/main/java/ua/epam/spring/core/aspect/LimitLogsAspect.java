package ua.epam.spring.core.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ua.epam.spring.core.beans.Event;
import ua.epam.spring.core.loggers.EventLogger;

/** Limit number of invokes of method ConsoleLogEvent.logEvent().
 *  In case of critical logs, redirect them to FileEventLogger */
//@Aspect
@Component("limitLogsAspect") // !!! it's necessary
public class LimitLogsAspect {

    private static final Logger LOG = Logger.getLogger(LoggingAspect.class);

    private int count = 0;

    @Autowired
    @Qualifier("cacheFileEventLogger")
    EventLogger otherLogger;

    @Pointcut("execution(* *.logEvent(..)) &&" +
            "within(*..ConsoleEventLogger)")
    public void consoleLogEventMethod() {
    }

    // instead of invoking of bean's method
    @Around("consoleLogEventMethod() && " +
            "args(evt)") // bean's method's argument: event
    public void aroundConsoleLogEvent(
            ProceedingJoinPoint jp, // we can invoke bean's method
            Object evt) // bean's method's argument: event
            throws Throwable {
        count += 1;
        if (count < 2) {
            jp.proceed(new Object[]{evt});
        } else {
            otherLogger.logEvent((Event) evt);
        }
    }
}
