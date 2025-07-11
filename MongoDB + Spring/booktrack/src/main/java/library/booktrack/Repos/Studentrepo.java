package library.booktrack.Repos;

import library.booktrack.Model.Student;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface Studentrepo extends MongoRepository<Student,Integer> {
}

