package com.app.postapp.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Document("Posts")
public class Posts {
    @Id
    private ObjectId id;
    String email;
    String caption;
    Map<String,String> posturl;


    public Posts(String email, String caption, Map<String, String> posturl) {
        this.email = email;
        this.caption = caption;
        this.posturl = posturl;
    }
    @JsonIgnore
    public ObjectId getObjectId() {
        return id;
    }

    public  String getid(){
        return id!=null? id.toHexString():null;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public Map<String, String> getPosturl() {
        return posturl;
    }

    public void setPosturl(Map<String, String> posturl) {
        this.posturl = posturl;
    }
}
