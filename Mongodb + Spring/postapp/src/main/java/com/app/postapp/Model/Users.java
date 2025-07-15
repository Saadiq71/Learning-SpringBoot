package com.app.postapp.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Document("Users")
public class Users {
    @Id
    String email;
    String pass;
    Map<String,String> profileurl;

    public Users(String email, String pass, Map<String, String> profileurl) {
        this.email = email;
        this.pass = pass;
        this.profileurl = profileurl;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public Map<String, String> getProfileurl() {
        return profileurl;
    }

    public void setProfileurl(Map<String, String> profileurl) {
        this.profileurl = profileurl;
    }
}
