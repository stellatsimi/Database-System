public class Destination {
    int trip_id;
    String name;
    String departure;
    String arrival;
    String type;
    String location;
    String language;
    String description;

    public Destination(int trip_id, String name, String departure, String arrival, String type, String location, String language, String description) {
        this.trip_id = trip_id;
        this.name = name;
        this.departure = departure;
        this.arrival = arrival;
        this.type = type;
        this.location = location;
        this.language = language;
        this.description = description;
    }

    public int getTrip_id() {
        return trip_id;
    }

    public void setTrip_id(int trip_id) {
        this.trip_id = trip_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
