package ua.epam.spring.core.loggers;

import ua.epam.spring.core.EventLogger;

public class ConsoleEventLogger implements EventLogger {

    public void logEvent(String msg) {
        System.out.println(msg);
    }
}
