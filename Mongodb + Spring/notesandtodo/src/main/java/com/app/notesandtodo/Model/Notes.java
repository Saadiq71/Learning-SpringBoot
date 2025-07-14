package com.app.notesandtodo.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Notes")
public class Notes {
    @Id
    private ObjectId id;//it will be auto generated
    String email;
    String title;
    String content;


    public Notes(String email, String title, String content) {

        this.email = email;
        this.title = title;
        this.content = content;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


}
