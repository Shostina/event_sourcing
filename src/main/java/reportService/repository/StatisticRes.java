package reportService.repository;

import java.util.ArrayList;

public class StatisticRes {

    private static final long HOUR_1_IN_MS = 1*60*60*1000;

    int id;
    ArrayList<Integer> dayStat = new ArrayList<Integer>();
    int allDay;
    int sumDuration;
    long startDay;
    long lastEnter;

    public StatisticRes() {
        for(int i = 0; i < 7; i++) {
            dayStat.add(0);
        }
        allDay = 0;
        sumDuration = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Integer> getDayStat() {
        return dayStat;
    }

    public void addDayStat(int day) {
        this.dayStat.set(day, this.dayStat.get(day) + 1);
        this.allDay += 1;
    }

    public int getAllDay() {
        return allDay;
    }

    public double getDuration() {
        return (double)sumDuration/(HOUR_1_IN_MS * (double) allDay);
    }

    public void addSumDuration(int sumDuration) {
        this.sumDuration += sumDuration;
    }

    public long getStartDay() {
        return startDay;
    }

    public void setStartDay(long startDay) {
        this.startDay = startDay;
    }

    public long getLastEnter() {
        return lastEnter;
    }

    public void setLastEnter(long lastEnter) {
        this.lastEnter = lastEnter;
    }
}
