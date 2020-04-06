package turnstile.command;

import db.DAO;
import db.Events;
import org.hibernate.Session;
import org.hibernate.Transaction;
import turnstile.query.EnterChecker;
import turnstile.query.EnterCheckerHandler;
import util.MyConstants;

import static db.DBService.SESSIONFACTORY;

public class EnterHandler {
    public static void execute(Enter enter) {
        if (EnterCheckerHandler.execute(new EnterChecker(enter.id, enter.time))) {
            Session session = SESSIONFACTORY.openSession();
            Transaction transaction = session.beginTransaction();
            DAO dao = new DAO(session);
            Events updatingEvent = new Events();
            updatingEvent.setEventType(MyConstants.ENTER_EVENT);
            updatingEvent.setSubscriptionId(enter.id);
            updatingEvent.setTime(enter.time);
            dao.saveEvents(updatingEvent);
            transaction.commit();
            session.close();
        }
    }
}
