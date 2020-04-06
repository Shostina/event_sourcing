import managerAdmin.ManagerAdmin;
import managerAdmin.command.CreationSubscription;
import managerAdmin.command.UpdatingSubscription;
import org.junit.BeforeClass;
import org.junit.Test;
import reportService.ReportService;
import reportService.query.Statistic;
import turnstile.Turnstile;
import turnstile.command.Enter;
import turnstile.command.Exit;
import turnstile.query.EnterChecker;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static junit.framework.TestCase.assertEquals;

public class Tests {

    private static final long DAY_10_IN_MS = 10*24*60*60*1000;
    private static final long DAY_5_IN_MS = 5*24*60*60*1000;
    private static final long DAY_3_IN_MS = 3*24*60*60*1000;
    private static final long DAY_1_IN_MS = 1*24*60*60*1000;
    private static final long HOUR_1_IN_MS = 1*60*60*1000;
    private static final long HOUR_2_IN_MS = 2*60*60*1000;
    private static final long CUR_TIME = System.currentTimeMillis();
    
    private static ManagerAdmin managerAdmin = new ManagerAdmin();
    private static Turnstile turnstile = new Turnstile();
    private static ReportService reportService = new ReportService();
    
    @BeforeClass
    public static void fillDB() {
        CreationSubscription subscription1 = new CreationSubscription(1, CUR_TIME - DAY_10_IN_MS, CUR_TIME - DAY_5_IN_MS);
        CreationSubscription subscription2 = new CreationSubscription(2, CUR_TIME - DAY_10_IN_MS, CUR_TIME - DAY_5_IN_MS);
        CreationSubscription subscription3 = new CreationSubscription(3, CUR_TIME - DAY_10_IN_MS, CUR_TIME + DAY_5_IN_MS);
        managerAdmin.execute(subscription1);
        managerAdmin.execute(subscription2);
        managerAdmin.execute(subscription3);
        UpdatingSubscription subscription2u = new UpdatingSubscription(2, CUR_TIME - DAY_3_IN_MS);
        managerAdmin.execute(subscription2u);
        UpdatingSubscription subscription2uu = new UpdatingSubscription(2, CUR_TIME + DAY_1_IN_MS);
        managerAdmin.execute(subscription2uu);
        
        Enter enter31 = new Enter(3, CUR_TIME - HOUR_2_IN_MS);
        Exit exit31 = new Exit(3, CUR_TIME - HOUR_1_IN_MS);
        Enter enter32 = new Enter(3, CUR_TIME - HOUR_1_IN_MS);
        Exit exit32 = new Exit(3, CUR_TIME);
        Enter enter33 = new Enter(3, CUR_TIME);
        Exit exit33 = new Exit(3, CUR_TIME + HOUR_1_IN_MS);
        Enter enter34 = new Enter(3, CUR_TIME + HOUR_1_IN_MS);
        Exit exit34 = new Exit(3, CUR_TIME + HOUR_2_IN_MS);
        turnstile.execute(enter31);
        turnstile.execute(exit31);
        turnstile.execute(enter32);
        turnstile.execute(exit32);
        turnstile.execute(enter33);
        turnstile.execute(exit33);
        turnstile.execute(enter34);
        turnstile.execute(exit34);
        Enter enter21 = new Enter(2, CUR_TIME - HOUR_2_IN_MS);
        Exit exit21 = new Exit(2, CUR_TIME + HOUR_2_IN_MS);
        turnstile.execute(enter21);
        turnstile.execute(exit21);
    }

    @Test
    public void testEnterCheckerHandler() {
        EnterChecker enterChecker1 = new EnterChecker(1, CUR_TIME);
        EnterChecker enterChecker2 = new EnterChecker(2, CUR_TIME);
        EnterChecker enterChecker3 = new EnterChecker(3, CUR_TIME);
        assert(!turnstile.execute(enterChecker1));
        assert (turnstile.execute(enterChecker2));
        assert (turnstile.execute(enterChecker3));
    }

    @Test
    public void testReportService_getDuration() {
        Statistic statisticDuration2 = new Statistic(2, "Duration");
        Statistic statisticDuration3 = new Statistic(3, "Duration");
        double actual2 = reportService.getDuration(statisticDuration2);
        double expected2 = 4.0;
        double actual3 = reportService.getDuration(statisticDuration3);
        double expected3 = 1.0;
        assertEquals(expected2, actual2);
        assertEquals(expected3, actual3);
    }

    @Test
    public void testReportService_getFrequency() {
        Statistic statisticFrequency2 = new Statistic(2, "Frequency");
        Statistic statisticFrequency3 = new Statistic(3, "Frequency");
        double actual2 = reportService.getFrequency(statisticFrequency2);
        double expected2 = 0.1;
        double actual3 = reportService.getFrequency(statisticFrequency3);
        double expected3 = 0.4;
        assertEquals(expected2, actual2);
        assertEquals(expected3, actual3);
    }

    @Test
    public void testReportService_getDateStatistic() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date(CUR_TIME));
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        ArrayList<Integer> expected2 = new ArrayList<>();
        ArrayList<Integer> expected3 = new ArrayList<>();
        for(int i = 0; i < 7; i++) {
            expected2.add(0);
            expected3.add(0);
        }
        expected2.set(dayOfWeek, 1);
        expected3.set(dayOfWeek, 4);

        Statistic statisticDate2 = new Statistic(2, "DateStatistic");
        Statistic statisticDate3 = new Statistic(3, "DateStatistic");
        ArrayList<Integer> actual2 = reportService.getDateStatistic(statisticDate2);
        ArrayList<Integer> actual3 = reportService.getDateStatistic(statisticDate3);
        assertEquals(expected2, actual2);
        assertEquals(expected3, actual3);
    }

}
