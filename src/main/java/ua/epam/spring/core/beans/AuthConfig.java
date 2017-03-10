package ua.epam.spring.core.beans;

import org.springframework.context.annotation.Bean;

public class AuthConfig {

    @Bean
    public String authBean() {
        return "I'm an authBean";
    }

}
