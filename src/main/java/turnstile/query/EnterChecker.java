package turnstile.query;

import java.util.Date;

public class EnterChecker {
    int id;
    long curDate;

    public EnterChecker(int id, long curDate) {
        this.id = id;
        this.curDate = curDate;
    }
}
