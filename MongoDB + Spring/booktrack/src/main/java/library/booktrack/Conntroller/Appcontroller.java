package library.booktrack.Conntroller;

import library.booktrack.Model.Book;
import library.booktrack.Model.BookDetailed;
import library.booktrack.Model.Student;
import library.booktrack.Repos.Bookrepo;
import library.booktrack.Repos.Studentrepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/library")
@CrossOrigin(origins = "*")
public class Appcontroller {

    @Autowired
    private Studentrepo soperation;

    @Autowired
    private Bookrepo boperation;

    @PostMapping("/add_book")
    public String addbook(@RequestParam int bid, @RequestParam String title, @RequestParam int stocks) {
        if (boperation.existsById(bid)) {
            return "Book already Exists at this ID";
        }
        boperation.save(new Book(bid, title, stocks));
        return "Book Added Successfully";
    }

    @PostMapping("/add_student")
    public String addstudent(@RequestParam int id, @RequestParam String name) {
        if (soperation.existsById(id)) {
            return "Student Already Exists";
        }
        soperation.save(new Student(id, name));
        return "Student Added Successfully";
    }

    @PutMapping("/borrow")
    public String Borrowbooks(@RequestParam int rollno, @RequestBody Book b) {
        Optional<Student> optionalStudent = soperation.findById(rollno);
        Optional<Book> optionalBook = boperation.findById(b.getBid());

        if (optionalStudent.isPresent() && optionalBook.isPresent()) {
            Student tempstudent = optionalStudent.get();
            Book tempbook = optionalBook.get();

            if (tempbook.getStock() <= 0) {
                return "Book is out of stock";
            }

            tempstudent.BorrowBook(tempbook);  // ✅ student borrows book
            soperation.save(tempstudent);

            tempbook.setStock(tempbook.getStock() - 1);
            tempbook.updatelist(tempstudent);  // ✅ student added to borrowed list
            boperation.save(tempbook);

            return "Book Borrowed Successfully";
        }

        return "Student or Book not found";
    }

    @PutMapping("/return")
    public String returnbooks(@RequestParam int rollno, @RequestBody Book b) {
        Optional<Student> optionalStudent = soperation.findById(rollno);
        Optional<Book> optionalBook = boperation.findById(b.getBid());

        if (optionalStudent.isPresent() && optionalBook.isPresent()) {
            Student tempstudent = optionalStudent.get();
            Book tempbook = optionalBook.get();

            if (tempstudent.getBook() != null && tempstudent.getBook().getBid() == b.getBid()) {
                tempstudent.returnBook();                 // ✅ fixed method call
                soperation.save(tempstudent);

                tempbook.setStock(tempbook.getStock() + 1);
                tempbook.Borrowed.removeIf(s -> s.getRollno() == tempstudent.getRollno()); // ✅ remove student
                boperation.save(tempbook);

                return "Book Returned Successfully";
            } else {
                return "This Book was not borrowed by the student";
            }
        }

        return "Student or Book not found";
    }

    //see all simple book list
@GetMapping("/seeall")
List<Book> getall(){
        return boperation.findAll();
}


    @GetMapping("/seeall_detailed")
    public List<BookDetailed> getAllBooksDetailed() {
        List<Book> allbooks = boperation.findAll();
        List<BookDetailed> finaloutput = new ArrayList<>();

        for (int i = 0; i < allbooks.size(); i++) {
            Book tempbook = allbooks.get(i);
            List<String> borrowers = new ArrayList<>();

            for (int j = 0; j < tempbook.Borrowed.size(); j++) {
                borrowers.add(tempbook.Borrowed.get(j).getName());
            }

            finaloutput.add(new BookDetailed(tempbook.getBid(), tempbook.getTitle(), tempbook.getStock(), borrowers));
        }

        return finaloutput;
    }


}



