package com.example.myapplication.models;

public class users {
    private String profilepic;
    private String userId;
    private String username;  // Use 'username' to match the Firebase field
    private String lastMessage;
    private String password;
    private String email;  // Use 'email' to match the Firebase field

    // Constructor with all fields
    public users(String profilepic, String userId, String username, String lastMessage, String password, String email) {
        this.profilepic = profilepic;
        this.userId = userId;
        this.username = username;
        this.lastMessage = lastMessage;
        this.password = password;
        this.email = email;
    }

    // Default constructor
    public users() {
    }

    // Constructor with selected fields
    public users(String username, String email, String password) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    // Getter and Setter methods
    public String getProfilepic() {
        return profilepic;
    }

    public void setProfilepic(String profilepic) {
        this.profilepic = profilepic;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
