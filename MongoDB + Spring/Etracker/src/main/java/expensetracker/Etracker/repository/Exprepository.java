package expensetracker.Etracker.repository;

import expensetracker.Etracker.Model.Expense;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.util.List;

public interface Exprepository extends MongoRepository<Expense,Integer> {

    List<Expense> findByCategory(String category);
    List<Expense> findByDateBetween(LocalDate start, LocalDate end);
}
