public class Trip {
    private int trip_id;
    private String departure;
    private String return_;
    private float cost;
    private int seats;
    private String branch;
    private String guide;
    private String driver;

    public Trip(int trip_id, String departure, String return_, float cost, int seats, String branch, String guide,
            String driver) {
        this.trip_id = trip_id;
        this.departure = departure;
        this.return_ = return_;
        this.cost = cost;
        this.seats = seats;
        this.branch = branch;
        this.guide = guide;
        this.driver = driver;
    }

    public int getTrip_id() {
        return trip_id;
    }

    public void setTrip_id(int trip_id) {
        this.trip_id = trip_id;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getReturn_() {
        return return_;
    }

    public void setReturn_(String return_) {
        this.return_ = return_;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getGuide() {
        return guide;
    }

    public void setGuide(String guide) {
        this.guide = guide;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }
}
