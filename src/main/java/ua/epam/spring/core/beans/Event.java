package ua.epam.spring.core.beans;

import java.text.DateFormat;
import java.util.Date;
import java.util.Random;

public class Event {

    private int id;
    private String msg;
    private Date date;
    private DateFormat df;
    private static Random random = new Random();

    public Event(Date date, DateFormat df) {
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
