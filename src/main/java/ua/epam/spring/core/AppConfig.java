package ua.epam.spring.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.ClassPathResource;
import ua.epam.spring.core.beans.Client;
import ua.epam.spring.core.loggers.CombinedEventLogger;
import ua.epam.spring.core.loggers.ConsoleEventLogger;
import ua.epam.spring.core.loggers.EventLogger;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableAspectJAutoProxy
//@Import({ LoggingAspect.class, StatisticsAspect.class,
// LimitLogsAspect.class }) // it's necessary
public class AppConfig {

    @Bean
    public static PropertyPlaceholderConfigurer propertyPlaceholderConfigurer() {
        PropertyPlaceholderConfigurer ppc = new PropertyPlaceholderConfigurer();
        ppc.setLocations( new ClassPathResource("client.properties"),
                new ClassPathResource("jdbc.properties"));
        ppc.setIgnoreUnresolvablePlaceholders(true);
        ppc.setSystemPropertiesMode(
                PropertyPlaceholderConfigurer.SYSTEM_PROPERTIES_MODE_OVERRIDE);
        return ppc;
    }

    @Bean
    public Client client() {
        return new Client("1", "Bob Marley");
    }

    @Bean
    @Scope("prototype")
    public Date currentDate() {
        return new Date();
    }

    @Bean
    public DateFormat dateFormat() {
        return java.text.DateFormat.getDateTimeInstance();
    }

    @Autowired
    @Qualifier("consoleEventLogger")
    EventLogger consoleEventLogger;

    @Autowired
    @Qualifier("combinedEventLogger")
    EventLogger combinedEventLogger;

    @Bean
    public Map<EventType, EventLogger> loggerMap() {
        Map<EventType, EventLogger> res = new HashMap<EventType, EventLogger>();
        res.put(EventType.INFO, consoleEventLogger);
        res.put(EventType.ERROR, combinedEventLogger);
        return res;
    }
}
