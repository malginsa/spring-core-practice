package ua.epam.spring.core.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.util.Date;
import java.util.Random;

@Component("event")
@Scope("prototype")
public class Event {

    private int id;
    private String msg;
    private Date date;
    private DateFormat df;
    private static Random random = new Random();

    @Autowired
     public Event(@Qualifier("currentDate") Date date,
                  @Qualifier("dateFormat") DateFormat df) {
        this.date = date;
        this.df = df;
        id = random.nextInt();
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", msg='" + msg + '\'' +
                ", date=" + df.format(date) +
                '}';
    }
}
