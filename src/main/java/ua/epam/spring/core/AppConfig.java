package ua.epam.spring.core;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.epam.spring.core.beans.Client;
import ua.epam.spring.core.loggers.ConsoleEventLogger;
import ua.epam.spring.core.loggers.EventLogger;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class AppConfig {

    private static ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");;

    @Bean
    public Client client() {
        return new Client("1", "Bob Marley");
    }

//    @Bean
    public Map<EventType, EventLogger> loggerMap() {
        Map<EventType, EventLogger> res = new HashMap<EventType, EventLogger>();
        res.put(EventType.INFO, (ConsoleEventLogger) ctx.getBean("consoleEventLogger"));
        res.put(EventType.ERROR, (ConsoleEventLogger) ctx.getBean("combinedEventLogger"));
        return res;
    }

}
