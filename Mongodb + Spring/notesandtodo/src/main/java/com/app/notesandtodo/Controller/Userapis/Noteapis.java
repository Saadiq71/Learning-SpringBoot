package com.app.notesandtodo.Controller.Userapis;

import com.app.notesandtodo.Model.Notes;
import com.app.notesandtodo.Repository.Noterepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/notes")
@CrossOrigin(origins = "*")
public class Noteapis {

    @Autowired
    private Noterepo noteoperation;

    @PostMapping("/create")
    public String createNote(@RequestParam String email, @RequestParam String title, @RequestParam String content) {
        noteoperation.save(new Notes(email, title, content));
        return "Note created successfully.";
    }

    @GetMapping("/getbyemail")
    public List<Notes> getNotesByEmail(@RequestParam String email) {
        return noteoperation.findByEmail(email);
    }

    @GetMapping("/getcontent")
    public String getNoteContent(@RequestParam String email, @RequestParam String title) {
        return noteoperation.findContentByEmailandTitle(email, title);
    }

    @PutMapping("/update_title")
    public String updateTitle(@RequestParam ObjectId noteid, @RequestParam String newtitle) {
        Optional<Notes> note = noteoperation.findById(noteid);
        if (note.isPresent()) {
            note.get().setTitle(newtitle);
            noteoperation.save(note.get());
            return "Note title updated.";
        }
        return "Note not found.";
    }

    @PutMapping("/update_content")
    public String updateContent(@RequestParam ObjectId noteid, @RequestParam String newcontent) {
        Optional<Notes> note = noteoperation.findById(noteid);
        if (note.isPresent()) {
            note.get().setContent(newcontent);
            noteoperation.save(note.get());
            return "Note content updated.";
        }
        return "Note not found.";
    }

    @DeleteMapping("/delete")
    public String deleteNote(@RequestParam ObjectId noteid) {
        if (noteoperation.existsById(noteid)) {
            noteoperation.deleteById(noteid);
            return "Note deleted.";
        }
        return "Note not found.";
    }
}
