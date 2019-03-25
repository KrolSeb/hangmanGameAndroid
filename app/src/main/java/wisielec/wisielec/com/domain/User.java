package wisielec.wisielec.com.domain;

import com.google.firebase.database.IgnoreExtraProperties;
import wisielec.wisielec.com.enums.Rank;

/**
 * Created by sebastian on 12.01.18(Saturday).
 */

@IgnoreExtraProperties
public class User {
    private final String DEFAULT_AVATAR = "https://firebasestorage.googleapis.com/v0/b/gra-w-wisielca.appspot.com/o/avatar.png?alt=media&token=829e201b-98ec-4a66-9064-2d6405bc8dc5";

    private String avatarURL;
    private String email;
    private String id;
    private boolean isAccountActivated;
    private boolean isActuallyLogged;
    private String password;
    private int points;
    private String rank;
    private String userName;

    public User(String email, String password) {
        this();
        this.email = email;
        this.password = password;
        this.userName  = this.email.split("@")[0];
    }

    public User() {
        this.avatarURL = DEFAULT_AVATAR;
        this.email = "";
        this.id = "";
        this.isAccountActivated = true;
        this.isActuallyLogged = true;
        this.password = "";
        this.points = 0;
        this.rank = Rank.PRIVATE.toString();
        this.userName = "";
    }

    public String getAvatarURL() {
        return avatarURL;
    }

    public void setAvatarURL(String avatarURL) {
        this.avatarURL = avatarURL;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean getIsAccountActivated() {
        return isAccountActivated;
    }

    public void setIsAccountActivated(boolean isAccountActivated) {
        this.isAccountActivated = isAccountActivated;
    }

    public boolean getIsActuallyLogged() {
        return isActuallyLogged;
    }

    public void setIsActuallyLogged(boolean isActuallyLogged) {
        this.isActuallyLogged = isActuallyLogged;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "User{" +
                "avatarURL='" + avatarURL + '\'' +
                ", email='" + email + '\'' +
                ", id='" + id + '\'' +
                ", isAccountActivated=" + isAccountActivated +
                ", isActuallyLogged=" + isActuallyLogged +
                ", password='" + password + '\'' +
                ", points=" + points +
                ", rank='" + rank + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }
}