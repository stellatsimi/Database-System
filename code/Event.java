public class Event {
    private int tripId;
    private String start;
    private String end;
    private String description;

    public Event(int tripId, String start, String end, String description) {
        this.tripId = tripId;
        this.start = start;
        this.end = end;
        this.description = description;
    }

    public int getTripId() {
        return tripId;
    }

    public void setTripId(int tripId) {
        this.tripId = tripId;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
