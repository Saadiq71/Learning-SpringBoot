package com.app.notesandtodo.Repository;

import com.app.notesandtodo.Model.Notes;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface Noterepo extends MongoRepository<Notes, ObjectId> {
    public void deleteByEmail(String email);

    List<Notes> findByEmail(String email);

    @Query(value = "{ 'email': ?0, 'title': ?1 }", fields = "{ 'content': 1, '_id': 0 }")
    String findContentByEmailandTitle(String email, String title);

}
