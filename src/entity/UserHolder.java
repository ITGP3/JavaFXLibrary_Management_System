package entity;

public class UserHolder {

    private String email;


    private final  static UserHolder USER_HOLDER = new UserHolder();

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static UserHolder getUserHolder() {
        return USER_HOLDER;
    }
}
