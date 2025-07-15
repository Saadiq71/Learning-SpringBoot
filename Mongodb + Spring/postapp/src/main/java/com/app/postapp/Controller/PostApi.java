package com.app.postapp.Controller;

import com.app.postapp.Mediahelper;
import com.app.postapp.Model.Posts;
import com.app.postapp.Repository.Postrepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/post")
@CrossOrigin(origins = "*")
public class PostApi {

    @Autowired
    private Postrepo postRepo;

    @Autowired
    private Mediahelper mediaHelper;

    // 1. Create a new post
    @PostMapping("/create")
    public String createPost(@RequestParam String email,
                             @RequestParam String caption,
                             @RequestParam MultipartFile[] files) throws Exception {

        if (files.length == 0) return "At least one file is required";

        Map<String, String> urls = mediaHelper.uploadFiles(files); // ✅ Cloudinary method
        Posts post = new Posts(email, caption, urls);
        postRepo.save(post);

        return "Post created successfully";
    }

    // 2. Delete post and all Cloudinary media by public_id
    @DeleteMapping("/delete")
    public String deletePost(@RequestParam String postid) {
        Optional<Posts> postOptional = postRepo.findById(new ObjectId(postid));
        if (postOptional.isPresent()) {
            Posts post = postOptional.get();
            Map<String, String> urls = post.getPosturl();

            // ✅ Delete all media from Cloudinary
            for (String publicId : urls.keySet()) {
                mediaHelper.deleteFile(publicId);
            }

            postRepo.delete(post);
            return "Post deleted";
        }
        return "Post not found";
    }

    // 3. Update post caption only
    @PutMapping("/updatecaption")
    public String updateCaption(@RequestParam String postid,
                                @RequestParam String newcaption) {
        Optional<Posts> postOptional = postRepo.findById(new ObjectId(postid));
        if (postOptional.isPresent()) {
            Posts post = postOptional.get();
            post.setCaption(newcaption);
            postRepo.save(post);
            return "Caption updated";
        }
        return "Post not found";
    }
}
