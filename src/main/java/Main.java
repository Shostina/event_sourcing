import managerAdmin.ManagerAdmin;
import managerAdmin.command.CreationSubscription;
import managerAdmin.command.UpdatingSubscription;
import reportService.ReportService;
import reportService.query.Statistic;
import turnstile.Turnstile;
import turnstile.command.Enter;
import turnstile.command.Exit;
import turnstile.query.EnterChecker;

public class Main {

    private static final long DAY_10_IN_MS = 10*24*60*60*1000;
    private static final long DAY_5_IN_MS = 5*24*60*60*1000;
    private static final long DAY_3_IN_MS = 3*24*60*60*1000;
    private static final long DAY_1_IN_MS = 1*24*60*60*1000;
    private static final long HOUR_1_IN_MS = 1*60*60*1000;
    private static final long HOUR_2_IN_MS = 2*60*60*1000;

    private static ManagerAdmin managerAdmin = new ManagerAdmin();
    private static Turnstile turnstile = new Turnstile();
    private static ReportService reportService = new ReportService();

    void run() {
        long curTime = System.currentTimeMillis();
        EnterChecker enterChecker1 = new EnterChecker(1, curTime);
        EnterChecker enterChecker2 = new EnterChecker(2, curTime);
        EnterChecker enterChecker3 = new EnterChecker(3, curTime);
        System.out.println(turnstile.execute(enterChecker1));
        System.out.println(turnstile.execute(enterChecker2));
        System.out.println(turnstile.execute(enterChecker3));
        Statistic statisticDate2 = new Statistic(2, "DateStatistic");
        Statistic statisticFrequency2 = new Statistic(2, "Frequency");
        Statistic statisticDuration2 = new Statistic(2, "Duration");

        Statistic statisticDate3 = new Statistic(3, "DateStatistic");
        Statistic statisticFrequency3 = new Statistic(3, "Frequency");
        Statistic statisticDuration3 = new Statistic(3, "Duration");
        reportService.execute(statisticDate2);
        System.out.println("Frequency 2");
        reportService.execute(statisticFrequency2);
        System.out.println("Duration 3");
        reportService.execute(statisticDuration2);
        reportService.execute(statisticDate3);
        System.out.println("Frequency 3");
        reportService.execute(statisticFrequency3);
        System.out.println("Duration 3");
        reportService.execute(statisticDuration3);
    }

    public static void main(String[] args) {
        test();
        test2();
        (new Main()).run();
    }

    static void test() {
        long curTime = System.currentTimeMillis();
        CreationSubscription subscription1 = new CreationSubscription(1, curTime - DAY_10_IN_MS, curTime - DAY_5_IN_MS);
        CreationSubscription subscription2 = new CreationSubscription(2, curTime - DAY_10_IN_MS, curTime - DAY_5_IN_MS);
        CreationSubscription subscription3 = new CreationSubscription(3, curTime - DAY_10_IN_MS, curTime + DAY_5_IN_MS);
        managerAdmin.execute(subscription1);
        managerAdmin.execute(subscription2);
        managerAdmin.execute(subscription3);
        UpdatingSubscription subscription2u = new UpdatingSubscription(2, curTime - DAY_3_IN_MS);
        managerAdmin.execute(subscription2u);
        UpdatingSubscription subscription2uu = new UpdatingSubscription(2, curTime + DAY_1_IN_MS);
        managerAdmin.execute(subscription2uu);
    }

    static void test2() {
        long curTime = System.currentTimeMillis();
        Enter enter31 = new Enter(3, curTime - HOUR_2_IN_MS);
        Exit exit31 = new Exit(3, curTime - HOUR_1_IN_MS);
        Enter enter32 = new Enter(3, curTime - HOUR_1_IN_MS);
        Exit exit32 = new Exit(3, curTime);
        Enter enter33 = new Enter(3, curTime);
        Exit exit33 = new Exit(3, curTime + HOUR_1_IN_MS);
        Enter enter34 = new Enter(3, curTime + HOUR_1_IN_MS);
        Exit exit34 = new Exit(3, curTime + HOUR_2_IN_MS);
        turnstile.execute(enter31);
        turnstile.execute(exit31);
        turnstile.execute(enter32);
        turnstile.execute(exit32);
        turnstile.execute(enter33);
        turnstile.execute(exit33);
        turnstile.execute(enter34);
        turnstile.execute(exit34);
        Enter enter21 = new Enter(2, curTime - HOUR_2_IN_MS);
        Exit exit21 = new Exit(2, curTime + HOUR_2_IN_MS);
        turnstile.execute(enter21);
        turnstile.execute(exit21);
    }
}
