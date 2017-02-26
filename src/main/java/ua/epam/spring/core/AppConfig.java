package ua.epam.spring.core;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.*;
import org.springframework.core.io.ClassPathResource;
import ua.epam.spring.core.beans.Client;

import java.text.DateFormat;
import java.util.Date;

@Configuration
@Import(LoggingAspect.class)
public class AppConfig {

    private static final Logger LOG = Logger.getLogger(AppConfig.class);

    @Bean
    public static PropertyPlaceholderConfigurer propertyPlaceholderConfigurer() {
        PropertyPlaceholderConfigurer ppc = new PropertyPlaceholderConfigurer();
        ppc.setLocation( new ClassPathResource("client.properties"));
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

//    @Qualifier("consoleEventLogger")
//    ConsoleEventLogger consBean;
//
//    @Qualifier("combinedEventLogger")
//    CombinedEventLogger cmbBean;
//
//    @Bean
//    public Map<EventType, EventLogger> loggerMap() {
//        Map<EventType, EventLogger> res = new HashMap<EventType, EventLogger>();
//        res.put(EventType.INFO, consBean);
//        res.put(EventType.ERROR, cmbBean);
//        return res;
//    }
}
