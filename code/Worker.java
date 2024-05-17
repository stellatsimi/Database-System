public class Worker {
    private String at;
    private String name;
    private String lname;
    private String branch;
    private float salry;
    private String type;

    public Worker(String at, String name, String lname, String branch, float salry, String type) {
        this.at = at;
        this.name = name;
        this.lname = lname;
        this.branch = branch;
        this.salry = salry;
        this.type = type;
    }

    public String getAt() {
        return at;
    }

    public void setAt(String at) {
        this.at = at;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public float getSalry() {
        return salry;
    }

    public void setSalry(float salry) {
        this.salry = salry;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
