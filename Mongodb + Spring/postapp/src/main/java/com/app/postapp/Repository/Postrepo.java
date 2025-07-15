package com.app.postapp.Repository;


import com.app.postapp.Model.Posts;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface Postrepo extends MongoRepository<Posts, ObjectId> {
}
