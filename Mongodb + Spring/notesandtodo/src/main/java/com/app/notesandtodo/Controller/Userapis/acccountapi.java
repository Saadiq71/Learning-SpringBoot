package com.app.notesandtodo.Controller.Userapis;

import com.app.notesandtodo.Model.Usercredential;
import com.app.notesandtodo.Repository.Noterepo;
import com.app.notesandtodo.Repository.Todorepo;
import com.app.notesandtodo.Repository.Userepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class acccountapi {

    @Autowired
    private Userepo useroperation;

    @Autowired
    private Noterepo notesoperation;

    @Autowired
    private Todorepo todooperation;

    @PostMapping("/create")
    public String createUser(@RequestParam String email, @RequestParam String password) {
        if (useroperation.existsById(email)) {
            return "User already exists. Please login.";
        }
        useroperation.save(new Usercredential(email, password));
        return "User registered successfully.";
    }

    @PutMapping("/login")
    public String loginUser(@RequestParam String email, @RequestParam String password) {
        Optional<Usercredential> tempUser = useroperation.findById(email);
        if (tempUser.isPresent()) {
            Usercredential user = tempUser.get();
            return user.getPassword().equals(password)
                    ? "Login successful"
                    : "Incorrect password";
        }
        return "User not found. Please register.";
    }

    @PutMapping("/changepassword")
    public String changePassword(@RequestParam String email, @RequestParam String password, @RequestParam String newpass) {
        Optional<Usercredential> tempUser = useroperation.findById(email);
        if (tempUser.isPresent()) {
            Usercredential user = tempUser.get();
            if (user.getPassword().equals(password)) {
                user.setPassword(newpass);
                useroperation.save(user);
                return "Password updated successfully.";
            }
            return "Incorrect current password.";
        }
        return "User not found.";
    }

    @PutMapping("/delete")
    public String deleteAccount(@RequestParam String email, @RequestParam String password) {
        Optional<Usercredential> tempUser = useroperation.findById(email);
        if (tempUser.isPresent() && tempUser.get().getPassword().equals(password)) {
            useroperation.deleteById(email);
            notesoperation.deleteByEmail(email);
            todooperation.deleteByEmail(email);
            return "Account and associated data deleted.";
        }
        return "Invalid credentials or user not found.";
    }
}
