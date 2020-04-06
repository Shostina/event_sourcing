package reportService.repository;

import db.DAO;
import managerAdmin.command.CreationSubscription;
import org.hibernate.Session;
import org.hibernate.Transaction;
import turnstile.command.Enter;
import turnstile.command.Exit;
import util.Aggregator;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import static db.DBService.SESSIONFACTORY;

public class ReportRepository {
    HashMap<Integer, StatisticRes> repository;

    static ReportRepository singleton;

    private ReportRepository() {

        repository = new HashMap<Integer, StatisticRes>();

        Session session = SESSIONFACTORY.openSession();
        Transaction transaction = session.beginTransaction();
        DAO dao = new DAO(session);
        List<Integer> ids = dao.getIds();
        transaction.commit();
        session.close();
        for(Integer id : ids) {
            StatisticRes statistic = Aggregator.aggreagateStatistic(id);
            repository.put(id, statistic);
        }

    }

    public static ReportRepository getInstance() {
        if(singleton == null) {
            singleton = new ReportRepository();
        }
        return singleton;
    }

    public StatisticRes getById (int id) {
        if(repository.containsKey(id)) {
            return repository.get(id);
        }
        return null;
    }

    public void createSubscription(CreationSubscription subscription) {
        StatisticRes newStatistic = new StatisticRes();
        newStatistic.setId(subscription.getId());
        newStatistic.setStartDay(subscription.getStartDay());
        repository.put(subscription.getId(), newStatistic);
    }

    public void saveEnter(Enter enter) {
        StatisticRes statistic = repository.get(enter.getId());

        long startTime = enter.getTime();
        statistic.setLastEnter(startTime);

        Calendar c = Calendar.getInstance();
        c.setTime(new Date(startTime));
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        statistic.addDayStat(dayOfWeek);

    }

    public void saveExit(Exit exit) {
        StatisticRes statistic = repository.get(exit.getId());
        if(statistic.getLastEnter() != -1) {
            int duration = (int)(exit.getTime() - statistic.getLastEnter());
            statistic.addSumDuration(duration);
            statistic.setLastEnter(-1);
        }
    }

}
