package ua.epam.spring.core.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

//@Aspect
@Component("statisticsAspect")
public class StatisticsAspect {

    private Map<Class<?>, Integer> counter;

    public StatisticsAspect() {
        counter = new HashMap<Class<?>, Integer>();
    }

    @Pointcut("execution(* *.logEvent(..))")
    public void allLogEventMethods() {
    }

    @AfterReturning("allLogEventMethods()")
    public void count(JoinPoint jp) {
        Class<?> clazz = jp.getTarget().getClass();
        if (!counter.containsKey(clazz)) {
            counter.put(clazz, 0);
        }
        counter.put(clazz, counter.get(clazz) + 1);
    }

    @Override
    public String toString() {
        return "StatisticsAspect{" +
                "counter=" + counter +
                '}';
    }
}
