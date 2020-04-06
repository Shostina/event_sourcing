package turnstile.command;

import db.DAO;
import db.Events;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.MyConstants;

import static db.DBService.SESSIONFACTORY;

public class ExitHandler {
    public static void execute (Exit exit) {
        Session session = SESSIONFACTORY.openSession();
        Transaction transaction = session.beginTransaction();
        DAO dao = new DAO(session);
        Events updatingEvent = new Events();
        updatingEvent.setEventType(MyConstants.EXIT_EVENT);
        updatingEvent.setSubscriptionId(exit.id);
        updatingEvent.setTime(exit.time);
        dao.saveEvents(updatingEvent);
        transaction.commit();
        session.close();
    }
}
