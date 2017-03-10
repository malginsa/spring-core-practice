package ua.epam.spring.core.finaltest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ua.epam.spring.core.beans.AuthConfig;

@Import(AuthConfig.class)
@Configuration
public class AppConfig {

    @Autowired
    private String authBean;

    @Bean
    Service clientService(String authBean) {
        ClientService s = new ClientService();
        s.setAuthBean(authBean);
        return s;
    }


    @Bean
    Service adminService() {
        Service s = new AdminService();
        return s;
    }
}
