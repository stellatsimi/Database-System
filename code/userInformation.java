import java.time.LocalDate;

public class userInformation {
    private static String AT;
    private static String name;
    private static String lastname;
    private static double salary;
    private static int branch;
    private static String password;
    private static LocalDate start_date;

    public static String getAT() {
        return AT;
    }

    public static void setAT(String AT) {
        userInformation.AT = AT;
    }

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        userInformation.name = name;
    }

    public static String getLastname() {
        return lastname;
    }

    public static void setLastname(String lastname) {
        userInformation.lastname = lastname;
    }

    public static double getSalary() {
        return salary;
    }

    public static void setSalary(double salary) {
        userInformation.salary = salary;
    }

    public static int getBranch() {
        return branch;
    }

    public static void setBranch(int branch) {
        userInformation.branch = branch;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        userInformation.password = password;
    }

    public static LocalDate getStart_date() {
        return start_date;
    }

    public static void setStart_date(LocalDate start_date) {
        userInformation.start_date = start_date;
    }

}