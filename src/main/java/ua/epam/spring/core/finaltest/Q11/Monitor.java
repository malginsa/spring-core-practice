package ua.epam.spring.core.finaltest.Q11;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

public class Monitor implements ApplicationListener {

    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        System.out.println("!!!: " + applicationEvent + "\n");
    }
}
