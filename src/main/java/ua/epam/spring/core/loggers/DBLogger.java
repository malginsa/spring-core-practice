package ua.epam.spring.core.loggers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ua.epam.spring.core.beans.Event;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component("dBLogger")
public class DBLogger implements EventLogger {

    private static final Logger LOG = Logger.getLogger(DBLogger.class);

    private JdbcTemplate jdbcTemplate;

    @Autowired
    ApplicationContext ctx;

    @PostConstruct
    private void init() {
        jdbcTemplate = (JdbcTemplate) ctx.getBean("jdbcTemplate");
        jdbcTemplate.execute("create table t_event (id int, msg varchar(255))");
    }

    @PreDestroy
    private void destroy() {
        jdbcTemplate.execute("drop table t_event");
    }

    public void logEvent(Event event) {
        jdbcTemplate.update("INSERT INTO t_event (id, msg) VALUES (?,?)",
                event.getId(), event.toString());
    }

    public int getCount() {
        return jdbcTemplate.queryForObject("select count(*) from t_event",
                Integer.class);
    }

    public String getEvent(int id) {
        return jdbcTemplate.queryForObject("select msg from t_event where id = ?",
                new Object[]{id}, String.class);
    }
}
