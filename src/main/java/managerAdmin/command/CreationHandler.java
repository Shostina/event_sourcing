package managerAdmin.command;

import db.DAO;
import db.Events;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.MyConstants;

import static db.DBService.SESSIONFACTORY;

public class CreationHandler {
    public static void execute(CreationSubscription subscription) {
        Session session = SESSIONFACTORY.openSession();
        Transaction transaction = session.beginTransaction();
        Events event = new Events();
        event.setEventType(MyConstants.CREATING_EVENT);
        event.setSubscriptionId(subscription.id);
        event.setTime(subscription.startDay);
        DAO dao = new DAO(session);
        dao.saveEvents(event);
        Events updatingEvent = new Events();
        updatingEvent.setEventType(MyConstants.UPDATING_EVENT);
        updatingEvent.setSubscriptionId(subscription.id);
        updatingEvent.setTime(subscription.endDay);
        dao.saveEvents(updatingEvent);
        transaction.commit();
        session.close();
    }
}
