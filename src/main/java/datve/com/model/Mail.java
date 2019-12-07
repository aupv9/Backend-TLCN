package datve.com.model;

public class Mail {
    private String friendEmail;

    // Replace with your email here:
    public static final String MY_EMAIL = "aupv96@gmail.com";

    // Replace password!!
    public static final String MY_PASSWORD = "batman@2019";

    // And receiver!
    public String FRIEND_EMAIL;

    public String getFriendEmail() {
        return friendEmail;
    }

    public void setFriendEmail(String friendEmail) {
        this.friendEmail = friendEmail;
    }
}
