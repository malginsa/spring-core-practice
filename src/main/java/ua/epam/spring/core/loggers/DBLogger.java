package ua.epam.spring.core.loggers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ua.epam.spring.core.beans.Event;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.List;

@Component("dBLogger")
public class DBLogger implements EventLogger {

    private static final Logger LOG = Logger.getLogger(DBLogger.class);

    private JdbcTemplate jdbcTemplate;

    @Autowired
    ApplicationContext ctx;

    @PostConstruct
    private void init() {
        jdbcTemplate = (JdbcTemplate) ctx.getBean("jdbcTemplate");
        try {
            jdbcTemplate.execute("create table t_event (id int, msg varchar(255), dt TIMESTAMP)");
        } catch (DataAccessException e) {
            LOG.warn("can't create table t_event");
        }
    }

    @PreDestroy
    private void destroy() {
        jdbcTemplate.execute("drop table t_event");
    }

    public void logEvent(Event event) {
        // TODO rename dat to dt
        jdbcTemplate.update("INSERT INTO t_event (id, msg, dt) VALUES (?,?,?)",
                event.getId(), event.getMsg(), new java.sql.Date(event.getDate().getTime()));
    }

    public int getCount() {
        return jdbcTemplate.queryForObject("select count(*) from t_event",
                Integer.class);
    }

    public String getMsg(int id) {
        return jdbcTemplate.queryForObject("select msg from t_event where id = ?",
                new Object[]{id}, String.class);
    }

    public Event getEventById(int id) {
        return jdbcTemplate.queryForObject("select * from t_event where id = ?",
                new Object[]{id},
                new RowMapper<Event>() {
                    public Event mapRow(ResultSet resultSet, int rowNum)
                            throws SQLException {
                        Event event = new Event(resultSet.getDate("dt"),
                                DateFormat.getDateTimeInstance());
                        event.setId(resultSet.getInt("id"));
                        event.setMsg(resultSet.getString("msg"));
                        return event;
                    }
                });
    }

    // TODO
    public List<Event> getAllEvents() {
        return jdbcTemplate.query("select * from t_event", new RowMapper<Event>() {
            public Event mapRow(ResultSet resultSet, int rowNum) throws SQLException {
                Event event = (Event) ctx.getBean("event");
                event.setId(resultSet.getInt("id"));
                event.setMsg(resultSet.getString("msg"));
                event.setDate(resultSet.getDate("dt"));
                return event;
            }
        });
    }
}
