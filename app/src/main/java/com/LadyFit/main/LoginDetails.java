package com.LadyFit.main;

public class LoginDetails {

    String Username;
    String password;

    public LoginDetails(String username, String password) {
        Username = username;
        this.password = password;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) { password=password;}

}
