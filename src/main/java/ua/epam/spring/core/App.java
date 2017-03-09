package ua.epam.spring.core;

import org.apache.log4j.Logger;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.epam.spring.core.aspect.StatisticsAspect;
import ua.epam.spring.core.beans.Client;
import ua.epam.spring.core.beans.Event;
import ua.epam.spring.core.loggers.DBLogger;
import ua.epam.spring.core.loggers.EventLogger;

import java.util.Map;

public class App {

    private Client client;
    private EventLogger defaultLogger;
    private Map<EventType, EventLogger> loggers;
    private static ConfigurableApplicationContext ctx;

    private static final Logger LOG = Logger.getLogger(App.class);

    public App() {
    }

    public App(Client client,
               EventLogger defaultLogger,
               Map<EventType, EventLogger> loggers) {
        this.client = client;
        this.defaultLogger = defaultLogger;
        this.loggers = loggers;
    }

    public void logEvent(EventType type, String msg) {
        EventLogger logger = loggers.get(type);
        if (logger==null) {
            logger = defaultLogger;
        }
        String message = msg.replaceAll(client.getId(), client.getFullName());
        Event event = (Event) ctx.getBean("event");
        event.setMsg(message);
        logger.logEvent(event);
    }

    public static void main(String[] args) {
        ctx = new ClassPathXmlApplicationContext("spring.xml");
        App app = (App) ctx.getBean("app");

        app.logEvent(EventType.INFO, "First for user 1");
        app.logEvent(EventType.ERROR, "Second event for user 2");
        app.logEvent(EventType.ERROR, "Third event for user 3");

        StatisticsAspect statistics = (StatisticsAspect)
                ctx.getBean("statisticsAspect");
//        System.out.println("Statistics:\n" + statistics);

        DBLogger dBLogger = (DBLogger) ctx.getBean("dBLogger");
        // TODO cast to target when aspect isactive
//        Advice advice = (Advice) ctx.getBean("dBLogger");

        System.out.println("RECORDS IN DB : " + dBLogger.getCount());
        System.out.println("MSG FROM DB WITH id=2: " + dBLogger.getMsg(2));

        System.out.println("DB: " + dBLogger.getEventById(3));

        System.out.println("ALL EVENTS FROM DB:");
        for (Event event : dBLogger.getAllEvents()) {
            System.out.println(event);
        }

        ctx.close(); // Spring closes context, invokes destroy method of every bean.
    }
}
