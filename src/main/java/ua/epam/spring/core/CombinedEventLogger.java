package ua.epam.spring.core;

import ua.epam.spring.core.beans.Event;
import ua.epam.spring.core.loggers.EventLogger;

import java.util.Collection;
import java.util.List;

public class CombinedEventLogger implements EventLogger{

    private Collection<EventLogger> loggers;

    public CombinedEventLogger(Collection<EventLogger> loggers) {
        this.loggers = loggers;
    }

    public void logEvent(Event event) {
        for (EventLogger logger : loggers) {
            logger.logEvent(event);
        }
    }
}
