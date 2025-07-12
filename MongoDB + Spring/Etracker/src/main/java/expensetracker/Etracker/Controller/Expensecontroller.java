package expensetracker.Etracker.Controller;
import expensetracker.Etracker.Model.Expense;
import expensetracker.Etracker.repository.Exprepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/Expense")
@CrossOrigin("*")
public class Expensecontroller {

    @Autowired
    private Exprepository repo;

    @PostMapping("/add_update")
    public String addExpense(@RequestParam int id,
                             @RequestParam String title,
                             @RequestParam double amt,
                             @RequestParam String date,
                             @RequestParam String category) {
        LocalDate parsedDate = LocalDate.parse(date);
        repo.save(new Expense(id, title, amt, parsedDate, category));
        return "Added/Updated Successfully";
    }

    @GetMapping("/read")
    public List<Expense> getAll() {
        return repo.findAll();
    }

    @DeleteMapping("/delete")
    public String deleteById(@RequestParam int id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return "Deleted Successfully";
        } else {
            return "No Expense Found with this ID";
        }
    }

    @GetMapping("/category")
    public double totalByCategory(@RequestParam String category) {
        return repo.findByCategory(category)
                .stream().mapToDouble(Expense::getAmt).sum();
    }

    @GetMapping("/date")
    public double totalByDate(@RequestParam String start_date,
                              @RequestParam String end_date) {
        LocalDate start = LocalDate.parse(start_date);
        LocalDate end = LocalDate.parse(end_date);
        return repo.findByDateBetween(start, end)
                .stream().mapToDouble(Expense::getAmt).sum();
    }

    @GetMapping("/thisweek")
    public double totalThisWeek() {
        LocalDate now = LocalDate.now();
        LocalDate monday = now.with(DayOfWeek.MONDAY);
        LocalDate sunday = now.with(DayOfWeek.SUNDAY);
        return repo.findByDateBetween(monday, sunday)
                .stream().mapToDouble(Expense::getAmt).sum();
    }

    @GetMapping("/thismonth")
    public double totalThisMonth() {
        LocalDate now = LocalDate.now();
        LocalDate start = now.withDayOfMonth(1);
        LocalDate end = now.withDayOfMonth(now.lengthOfMonth());
        return repo.findByDateBetween(start, end)
                .stream().mapToDouble(Expense::getAmt).sum();
    }
}
