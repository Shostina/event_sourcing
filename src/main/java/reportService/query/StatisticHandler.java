package reportService.query;


import reportService.repository.StatisticRes;
import reportService.repository.ReportRepository;

public class StatisticHandler {

    public static StatisticRes execute (Statistic statistic) {
        return ReportRepository.getInstance().getById(statistic.id);
    }
}
