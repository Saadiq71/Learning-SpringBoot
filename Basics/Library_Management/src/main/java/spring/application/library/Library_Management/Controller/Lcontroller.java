package spring.application.library.Library_Management.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import spring.application.library.Library_Management.Model.User;
import spring.application.library.Library_Management.Service.allservice;

import java.util.List;

@RestController
@RequestMapping("/library")
@CrossOrigin(origins = "*")
public class Lcontroller {

    @Autowired
    allservice operation;

    // 1. Create User
    @PutMapping("/create_user")
    public String adduser(@RequestParam int uid, @RequestParam String name) {
        return operation.createuser(uid, name);
    }

    // 2. Borrow a Book
    @PostMapping("/borrow_book")
    public String borrowBook(@RequestParam int uid, @RequestParam int bid) {
        return operation.borrow(uid, bid);
    }

    // 3. Return a Book
    @PostMapping("/return_book")
    public String returnBook(@RequestParam int uid, @RequestParam int bid) {
        return operation.returnbook(uid, bid);
    }

    // 4. Add Book (admin only)
    @PostMapping("/add_book")
    public String addBook(@RequestParam String email,
                          @RequestParam String pass,
                          @RequestParam int id,
                          @RequestParam String title) {
        return operation.addbook(email, pass, id, title);
    }

    // 5. Remove Book (admin only)
    @DeleteMapping("/remove_book")
    public String removeBook(@RequestParam String email,
                             @RequestParam String pass,
                             @RequestParam int id) {
        return operation.removebook(email, pass, id);
    }

    // 6. See all users and their borrowed books (admin)
    @GetMapping("/all_users")
    public List<User> getAllUsers() {
        return operation.getall();
    }
}
