package ua.epam.spring.core;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.epam.spring.core.beans.Client;
import ua.epam.spring.core.beans.Event;
import ua.epam.spring.core.loggers.EventLogger;

public class App {

    private Client client;
    private EventLogger eventLogger;
    private static ConfigurableApplicationContext ctx;

    public App(Client client, EventLogger eventLogger) {
        this.client = client;
        this.eventLogger = eventLogger;
    }

    public static void main(String[] args) {
        ctx = new ClassPathXmlApplicationContext("spring.xml");
        App app = (App) ctx.getBean("app");

        app.logEvent("Some event for user 1");
        app.logEvent("Some event for user 2");
        app.logEvent("Some event for user 3");
        app.logEvent("Some event for user 4");

        ctx.close(); // Spring closes context, invokes destroy method of every bean.
    }

    public void logEvent(String msg) {
        String message = msg.replaceAll(client.getId(), client.getFullName());
        Event event = (Event) ctx.getBean("event");
        event.setMsg(message);
        eventLogger.logEvent(event);
    }
}
