package com.app.notesandtodo.Repository;

import com.app.notesandtodo.Model.Todo;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.aggregation.ArrayOperators;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Objects;

public interface Todorepo extends MongoRepository<Todo, ObjectId> {
    void deleteByEmail(String email);

    List<Todo> findByEmail(String email);
}
