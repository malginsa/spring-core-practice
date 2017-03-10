package ua.epam.spring.core.finaltest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    Service clientService() {
        Service s = new ClientService();
        return s;
    }


    @Bean
    Service adminService() {
        Service s = new AdminService();
        return s;
    }
}
