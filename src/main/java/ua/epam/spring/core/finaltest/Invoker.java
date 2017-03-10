package ua.epam.spring.core.finaltest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("invoker")
public class Invoker {

    @Autowired
    @Qualifier("ClientService")
    private Service service;

    public void invoke() {
        service.say();
    }

}
