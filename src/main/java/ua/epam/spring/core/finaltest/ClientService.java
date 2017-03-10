package ua.epam.spring.core.finaltest;

import org.springframework.stereotype.Component;

public class ClientService implements Service {
    public void say() {
        System.out.println("I'm clientService");
    }
}
