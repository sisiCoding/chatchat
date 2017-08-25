package com.jingyushi.chatchat;

import java.util.List;

/**
 * Created by jingyushi on 8/22/17.
 */

// Not used for now, will be used to store user info in the db
public class User {
    private String userName;
    private String email;
    private String password;
    private List<User> friends;

    //TODO add profile picture support, and QR code, maybe chat id is needed

    public User(){}
    public User(String userName, String email, List<User> friends){
        this.userName = userName;
        this.email = email;
        this.friends = friends;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public List<User> getFriends() {
        return friends;
    }

    public void setFriends(List<User> friends) {
        this.friends = friends;
    }
}
