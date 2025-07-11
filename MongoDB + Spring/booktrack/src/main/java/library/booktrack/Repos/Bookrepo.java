package library.booktrack.Repos;

import library.booktrack.Model.Book;
import library.booktrack.Model.Student;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface Bookrepo extends MongoRepository<Book,Integer> {
}