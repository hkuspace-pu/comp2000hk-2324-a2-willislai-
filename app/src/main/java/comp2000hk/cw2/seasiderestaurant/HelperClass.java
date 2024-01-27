package comp2000hk.cw2.seasiderestaurant;

public class HelperClass {
    String username, phone_no, email, password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone_no() {
        return phone_no;
    }

    public void setPhone_no(String phone_no) {
        this.phone_no = phone_no;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public HelperClass(String username, String phone_no, String email, String password) {
        this.username = username;
        this.phone_no = phone_no;
        this.email = email;
        this.password = password;
    }

    public HelperClass() {
    }
}
