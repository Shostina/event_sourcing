package reportService.query;

public class Statistic {
    int id;
    String type;

    public Statistic(int id, String type) {
        this.id = id;
        this.type = type;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }
}
