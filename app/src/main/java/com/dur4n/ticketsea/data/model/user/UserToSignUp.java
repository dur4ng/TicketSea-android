package com.dur4n.ticketsea.data.model.user;

public class UserToSignUp {
    String username;
    String password;
    String passwordConfiramtion;

    public UserToSignUp(
            String username,
            String password,
            String passwordConfiramtion
    ) {
        this.username = username;
        this.password = password;
        this.passwordConfiramtion = passwordConfiramtion;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfiramtion() {
        return passwordConfiramtion;
    }

    public void setPasswordConfiramtion(String passwordConfiramtion) {
        this.passwordConfiramtion = passwordConfiramtion;
    }
}
