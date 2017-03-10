package ua.epam.spring.core.finaltest;

import org.springframework.stereotype.Component;

public class ClientService implements Service {

    public String authBean;

    public String getAuthBean() {
        return authBean;
    }

    public void setAuthBean(String authBean) {
        this.authBean = authBean;
    }

    public void say() {
        System.out.println("I'm clientService");
    }
}
