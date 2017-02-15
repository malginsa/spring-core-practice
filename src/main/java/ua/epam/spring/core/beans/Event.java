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

    public static boolean isDay() {
        Date curr = new Date();
        int hours = curr.getHours();
        return  (hours > 8 && hours < 17);
    }

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
