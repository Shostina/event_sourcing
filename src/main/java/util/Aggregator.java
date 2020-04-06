package util;

import db.DAO;
import db.Events;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.MyConstants;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import reportService.repository.StatisticRes;

import static db.DBService.SESSIONFACTORY;


public class Aggregator {
    public static Subscription aggregateSubscription(int id) {
        Subscription subscription = new Subscription();
        Session session = SESSIONFACTORY.openSession();
        Transaction transaction = session.beginTransaction();
        List<Events> events = new DAO(session).getEvents(id);
        for (Events event : events) {
            if(event.getEventType().equals(MyConstants.CREATING_EVENT)) {
                subscription.setId(event.getSubscriptionId());
                subscription.setStartDay(event.getTime());
            } else if(event.getEventType().equals(MyConstants.UPDATING_EVENT)) {
                subscription.setEndDay(event.getTime());
            }
        }
        transaction.commit();
        session.close();
        return subscription;
    }
    public static StatisticRes aggreagateStatistic(int id) {
        System.out.println("aggregate Statistic");
        StatisticRes statistic = new StatisticRes();
        Session session = SESSIONFACTORY.openSession();
        Transaction transaction = session.beginTransaction();
        List<Events> events = new DAO(session).getEvents(id);
        for (Events event : events) {
            if(event.getEventType().equals(MyConstants.CREATING_EVENT)) {
                statistic.setId(event.getSubscriptionId());
                statistic.setStartDay(event.getTime());
            } else if(event.getEventType().equals(MyConstants.ENTER_EVENT)) {
                long startTime = event.getTime();
                Calendar c = Calendar.getInstance();
                c.setTime(new Date(startTime));
                int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
                statistic.addDayStat(dayOfWeek);
                statistic.setLastEnter(startTime);
            } else if(event.getEventType().equals(MyConstants.EXIT_EVENT)) {
                if(statistic.getLastEnter() > 0) {
                    int duration = (int)(event.getTime() - statistic.getLastEnter());
                    statistic.addSumDuration(duration);
                    statistic.setLastEnter(-1);
                }
            }
        }
        transaction.commit();
        session.close();
        return statistic;

    }
}
