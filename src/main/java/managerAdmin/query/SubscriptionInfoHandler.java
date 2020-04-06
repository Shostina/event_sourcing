package managerAdmin.query;

import util.Aggregator;
import util.Subscription;


public class SubscriptionInfoHandler {
    public static Subscription execute(SubscriptionInfo query) {
        return Aggregator.aggregateSubscription(query.id);
    }
}
