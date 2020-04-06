package db;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(
        name = "events"
)
public class Events implements Serializable {
    private static final long serialVersionUID = -8706689714326132798L;

    @Id
    @Column(name = "event_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long eventId;

    @Column(name = "subscription_id")
    private int subscriptionId;

    @Column(name = "event_type")
    private String eventType;

    @Column(name = "time")
    private long time;

    public long getEventId() {
        return eventId;
    }

    public void setEventId(long eventId) {
        this.eventId = eventId;
    }

    public int getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(int subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
