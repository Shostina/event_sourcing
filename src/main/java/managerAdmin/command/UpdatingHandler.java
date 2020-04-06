package managerAdmin.command;

import db.DAO;
import db.Events;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.MyConstants;

import static db.DBService.SESSIONFACTORY;

public class UpdatingHandler {
    public static void execute(UpdatingSubscription subscription) {

        Session session = SESSIONFACTORY.openSession();
        Transaction transaction = session.beginTransaction();
        DAO dao = new DAO(session);
        Events updatingEvent = new Events();
        updatingEvent.setEventType(MyConstants.UPDATING_EVENT);
        updatingEvent.setSubscriptionId(subscription.id);
        updatingEvent.setTime(subscription.endDay);
        dao.saveEvents(updatingEvent);
        transaction.commit();
        session.close();
    }
}
