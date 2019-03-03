package in.helpingdevelop.shortfilm.model;

public class UserProfile {
    private String id;
    private String ticket_bookd;
    private String email;
    private String name;
    private String mobile;
    private String dob;
    private String password = "password";

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTicket_bookd() {
        return ticket_bookd;
    }

    public void setTicket_bookd(String ticket_bookd) {
        this.ticket_bookd = ticket_bookd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }
}
