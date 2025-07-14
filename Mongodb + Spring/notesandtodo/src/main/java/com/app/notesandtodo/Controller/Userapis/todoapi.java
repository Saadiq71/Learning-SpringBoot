package com.app.notesandtodo.Controller.Userapis;

import com.app.notesandtodo.Model.Todo;
import com.app.notesandtodo.Repository.Todorepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/todo")
@CrossOrigin(origins = "*")
public class todoapi {

    @Autowired
    private Todorepo todooperation;

    @PostMapping("/create")
    public String createTodo(@RequestParam String email, @RequestParam String title) {
        todooperation.save(new Todo(email, title));
        return "Todo created.";
    }

    @GetMapping("/getbyemail")
    public List<Todo> getTodosByEmail(@RequestParam String email) {
        return todooperation.findByEmail(email);
    }

    @PutMapping("/update_title")
    public String updateTitle(@RequestParam ObjectId Tid, @RequestParam String newtitle) {
        Optional<Todo> todo = todooperation.findById(Tid);
        if (todo.isPresent()) {
            todo.get().setTitle(newtitle);
            todooperation.save(todo.get());
            return "Todo title updated.";
        }
        return "Todo not found.";
    }

    @PutMapping("/update_status")
    public String updateStatus(@RequestParam ObjectId Tid, @RequestParam boolean status) {
        Optional<Todo> todo = todooperation.findById(Tid);
        if (todo.isPresent()) {
            todo.get().setStatus(status);
            todooperation.save(todo.get());
            return "Todo status updated.";
        }
        return "Todo not found.";
    }

    @DeleteMapping("/delete")
    public String deleteTodo(@RequestParam ObjectId Tid) {
        if (todooperation.existsById(Tid)) {
            todooperation.deleteById(Tid);
            return "Todo deleted.";
        }
        return "Todo not found.";
    }
}
