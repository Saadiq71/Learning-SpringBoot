package com.app.postapp.Repository;


import com.app.postapp.Model.Users;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface userrepo extends MongoRepository<Users,String> {
}
