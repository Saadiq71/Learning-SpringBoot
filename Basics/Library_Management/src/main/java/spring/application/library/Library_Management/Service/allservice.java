package spring.application.library.Library_Management.Service;

import org.springframework.stereotype.Service;
import spring.application.library.Library_Management.Model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

@Service
public class allservice {
    List<User> ulist = new ArrayList<>(); // to maintain users list
    Map<Integer, String> Blist = new HashMap<>(); // to maintain list of all books

    // 1. Create User
    public String createuser(int uid, String name) {
        for (User user : ulist) {
            if (user.getUid() == uid) {
                return "User Already Exist";
            }
        }
        ulist.add(new User(uid, name));
        return "User Created Successfully";
    }

    // 2. Borrow book by user
    public String borrow(int uid, int bid) {
        if (Blist.containsKey(bid)) {
            for (User user : ulist) {
                if (user.getUid() == uid) {
                    user.borrow(bid, Blist.get(bid));
                    return "Book Assigned Successfully";
                }
            }
        }
        return "Invalid Operation";
    }

    // 3. Return book by user
    public String returnbook(int uid, int bid) {
        if (Blist.containsKey(bid)) {
            for (User user : ulist) {
                if (user.getUid() == uid) {
                    return user.Rbook(bid);
                }
            }
        }
        return "Invalid Operation";
    }

    // 4. Add book by admin only
    public String addbook(String email, String pass, int id, String title) {
        if (email.equals("mrsadiq471@gmail.com") && pass.equals("654321")) {
            if (Blist.containsKey(id)) {
                return "Book already present at this id";
            } else {
                Blist.put(id, title);
                return "Book Added Successfully";
            }
        }
        return "You are not authorized for this";
    }

    // 5. Remove book by admin only
    public String removebook(String email, String pass, int id) {
        if (email.equals("mrsadiq471@gmail.com") && pass.equals("654321")) {
            if (Blist.containsKey(id)) {
                Blist.remove(id);
                return "Book Deleted Successfully";
            } else {
                return "No Book present at this id";
            }
        }
        return "You are not authorized for this";
    }

    // 6. See all the users and the books they borrowed (for admin)
    public List<User> getall() {
        return ulist;
    }
}
