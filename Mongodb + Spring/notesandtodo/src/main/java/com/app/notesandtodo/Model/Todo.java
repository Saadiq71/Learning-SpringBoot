package com.app.notesandtodo.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Todos")
public class Todo {
    @Id
    private ObjectId id;
    String email;
    String title;
    boolean status;


    public Todo( String email, String title) {

        this.email = email;
        this.title = title;
        this.status = false;

    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }


    @JsonIgnore // Optional: hides ObjectId field from JSON if you want
    public ObjectId getObjectId() {
        return id;
    }

    public String getId() { // this will show in response
        return id != null ? id.toHexString() : null;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }
}
