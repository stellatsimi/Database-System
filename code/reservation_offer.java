public class reservation_offer {
    private String res_off_lname;
    private String res_off_tr_id;
    private String res_off_name;
    private String res_off_id;
    private float res_off_deposit;

    public reservation_offer(String res_off_lname, String res_off_tr_id, String res_off_name, String res_off_id, float res_off_deposit) {
        this.res_off_lname = res_off_lname;
        this.res_off_tr_id = res_off_tr_id;
        this.res_off_name = res_off_name;
        this.res_off_id = res_off_id;
        this.res_off_deposit = res_off_deposit;
    }

    public String getRes_off_lname() {
        return res_off_lname;
    }

    public void setRes_off_lname(String res_off_lname) {
        this.res_off_lname = res_off_lname;
    }

    public String getRes_off_tr_id() {
        return res_off_tr_id;
    }

    public void setRes_off_tr_id(String res_off_tr_id) {
        this.res_off_tr_id = res_off_tr_id;
    }

    public String getRes_off_name() {
        return res_off_name;
    }

    public void setRes_off_name(String res_off_name) {
        this.res_off_name = res_off_name;
    }

    public String getRes_off_id() {
        return res_off_id;
    }

    public void setRes_off_id(String res_off_id) {
        this.res_off_id = res_off_id;
    }

    public float getRes_off_deposit() {
        return res_off_deposit;
    }

    public void setRes_off_deposit(float res_off_deposit) {
        this.res_off_deposit = res_off_deposit;
    }
}
