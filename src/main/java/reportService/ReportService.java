package reportService;

import reportService.query.Statistic;
import reportService.query.StatisticHandler;
import reportService.repository.ReportRepository;
import reportService.repository.StatisticRes;

import java.util.ArrayList;

public class ReportService {

    public void execute(Statistic s) {
        if (s.getType().equals("DateStatistic")) {
            ArrayList<Integer> res = getDateStatistic(s);
            for (int i = 0; i < 7; i++) {
                System.out.println(i + " " + res.get(i));
            }
        } else if (s.getType().equals("Frequency")) {
            System.out.println(getFrequency(s));
        } else if (s.getType().equals("Duration")) {
            System.out.println(getDuration(s));
        }
    }

    public ArrayList<Integer> getDateStatistic(Statistic s) {
        StatisticRes res = StatisticHandler.execute(s);
        return res.getDayStat();
    }

    public double getFrequency(Statistic s) {
        StatisticRes res = StatisticHandler.execute(s);
        long startDay = res.getStartDay();
        long curDay = System.currentTimeMillis();
        int numOfDays = (int)((curDay - startDay) / (24*60*60*1000));
        return (double)res.getAllDay()/(double)numOfDays;
    }

    public double getDuration(Statistic s) {
        StatisticRes res = StatisticHandler.execute(s);
        return res.getDuration();

    }

}
