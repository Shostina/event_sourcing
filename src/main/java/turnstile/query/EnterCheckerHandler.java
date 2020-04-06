package turnstile.query;

import util.Aggregator;

public class EnterCheckerHandler {
    public static boolean execute(EnterChecker enter) {
        long time = Aggregator.aggregateSubscription(enter.id).getEndDay();
        return time > System.currentTimeMillis();
    }
}
