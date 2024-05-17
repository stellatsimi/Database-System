public class settings {
    private String user;
    private String change;
    private String action;
    private String date;

    public settings(String user, String change, String action, String date) {
        this.user = user;
        this.change = change;
        this.action = action;
        this.date = date;
    }

    public String getUser() {
        return this.user;
    }

    public String getChange() {
        return this.change;
    }

    public String getAction() {
        return this.action;
    }

    public String getDate() {
        return this.date;
    }

}
