package managerAdmin.command;

import java.util.Date;

public class CreationSubscription {

    int id;
    long startDay;
    long endDay;

    public CreationSubscription(int id, long startDay, long endDay) {
        this.id = id;
        this.startDay = startDay;
        this.endDay = endDay;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getStartDay() {
        return startDay;
    }

    public void setStartDay(long startDay) {
        this.startDay = startDay;
    }

    public long getEndDay() {
        return endDay;
    }

    public void setEndDay(long endDay) {
        this.endDay = endDay;
    }
}
