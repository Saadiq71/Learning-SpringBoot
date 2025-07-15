package com.app.postapp.Controller;

import com.app.postapp.Model.Posts;
import com.app.postapp.Model.Users;
import com.app.postapp.Repository.Postrepo;
import com.app.postapp.Repository.userrepo;
import com.app.postapp.Mediahelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class UserApi {

    @Autowired
    private userrepo userRepo;

    @Autowired
    private Postrepo postRepo;

    @Autowired
    private Mediahelper mediaHelper;

    // 1. Register a user (profile image optional)
    @PostMapping("/register")
    public String registerUser(@RequestParam String email,
                               @RequestParam String pass,
                               @RequestParam(required = false) MultipartFile profileImage) throws Exception {

        if (userRepo.existsById(email)) return "User already exists";

        Map<String, String> profileUrl = null;

        if (profileImage != null && !profileImage.isEmpty()) {
            profileUrl = mediaHelper.uploadFiles(new MultipartFile[]{profileImage});
        }

        Users user = new Users(email, pass, profileUrl);
        userRepo.save(user);
        return "User registered successfully";
    }

    // 2. Login
    @PostMapping("/login")
    public String login(@RequestParam String email,
                        @RequestParam String pass) {
        Optional<Users> userOptional = userRepo.findById(email);
        if (userOptional.isPresent()) {
            Users user = userOptional.get();
            return user.getPass().equals(pass) ? "Login successful" : "Incorrect password";
        } else {
            return "User not found";
        }
    }

    // 3. Change password
    @PutMapping("/changepass")
    public String changePassword(@RequestParam String email,
                                 @RequestParam String newpass) {
        Optional<Users> userOptional = userRepo.findById(email);
        if (userOptional.isPresent()) {
            Users user = userOptional.get();
            user.setPass(newpass);
            userRepo.save(user);
            return "Password updated";
        }
        return "User not found";
    }

    // 4. Delete account
    @DeleteMapping("/delete")
    public String deleteUser(@RequestParam String email) {
        if (userRepo.existsById(email)) {
            userRepo.deleteById(email);
            return "User deleted";
        }
        return "User not found";
    }

    @PutMapping("/setprofile")
    public Map<String, String> setProfileImage(@RequestParam String email,
                                               @RequestParam MultipartFile profileImage) throws Exception {
        Optional<Users> userOptional = userRepo.findById(email);
        Map<String, String> response = new HashMap<>();

        if (userOptional.isPresent()) {
            Map<String, String> profileUrlMap = mediaHelper.uploadFiles(new MultipartFile[]{profileImage});

            String publicId = profileUrlMap.keySet().iterator().next();
            String url = profileUrlMap.get(publicId);

            Users user = userOptional.get();
            user.setProfileurl(profileUrlMap);
            userRepo.save(user);

            response.put("message", "Profile image set");
            response.put("public_id", publicId);
            response.put("url", url);
        } else {
            response.put("error", "User not found");
        }

        return response;
    }


    // 6. Delete profile image
    @DeleteMapping("/deleteprofileimage")
    public String deleteProfileImage(@RequestParam String email,
                                     @RequestParam String fileid) {
        Optional<Users> userOptional = userRepo.findById(email);
        if (userOptional.isPresent()) {
            mediaHelper.deleteFile(fileid);
            Users user = userOptional.get();
            user.setProfileurl(null);
            userRepo.save(user);
            return "Profile image deleted";
        }
        return "User not found";
    }

    // ✅ 7. Get all posts (sorted by newest)
    @GetMapping("/all")
    public List<Posts> getAllPosts() {
        List<Posts> posts = postRepo.findAll();
        posts.sort((p1, p2) -> p2.getObjectId().getTimestamp() - p1.getObjectId().getTimestamp());
        return posts;
    }

    // ✅ 8. Get posts by a specific user
    @GetMapping("/myposts")
    public List<Posts> getUserPosts(@RequestParam String email) {
        List<Posts> allPosts = postRepo.findAll();
        List<Posts> userPosts = new ArrayList<>();

        for (Posts post : allPosts) {
            if (post.getEmail().equals(email)) {
                userPosts.add(post);
            }
        }

        return userPosts;
    }
}
