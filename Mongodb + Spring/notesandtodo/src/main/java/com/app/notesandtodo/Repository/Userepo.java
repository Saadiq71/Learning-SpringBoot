package com.app.notesandtodo.Repository;

import com.app.notesandtodo.Model.Usercredential;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface Userepo extends MongoRepository<Usercredential,String> {
}
