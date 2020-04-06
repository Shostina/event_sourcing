package db;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Collections;
import java.util.List;

public class DAO {
    private final Session session;

    public DAO(Session session) {
        this.session = session;
    }

    public Events get(long id) throws HibernateException {
        return (Events) session.get(Events.class, id);
    }

    public List<Integer>getIds() throws HibernateException {

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Integer> query = builder.createQuery(Integer.class);
        Root<Events> events = query.from(Events.class);
        query.select(events.get("subscriptionId"));
        query.distinct(true);
        List<Integer> resultList = session.createQuery(query).getResultList();

        return resultList;
    }

    public List<Events> getEvents(int subscriptionId) throws HibernateException {
        Criteria criteria = session.createCriteria(Events.class);
        List<Events> events = criteria.add(Restrictions.eq("subscriptionId", subscriptionId)).list();
        Collections.sort(events, (a, b) -> a.getTime() < b.getTime() ? -1 : a.getTime() == b.getTime() ? 0 : 1);
        return events;
    }

    public void saveEvents(Events event) {
        session.save(event);
    }
}
