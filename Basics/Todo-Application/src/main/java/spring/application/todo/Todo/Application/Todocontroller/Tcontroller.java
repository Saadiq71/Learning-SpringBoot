package spring.application.todo.Todo.Application.Todocontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import spring.application.todo.Todo.Application.Model.Todo;
import spring.application.todo.Todo.Application.Service.TodoService;

import java.util.List;

@RestController
@RequestMapping("/todo")
@CrossOrigin(origins = "*")
public class Tcontroller {

    @Autowired
    private TodoService todoService;

    @PostMapping("/create")
    public String addTodo(@RequestParam int id, @RequestParam String title) {
        return todoService.addTodo(id, title);
    }

    @GetMapping("/all_todo")
    public List<Todo> getAllTodos() {
        return todoService.getAllTodos();
    }

    @PutMapping("/update")
    public String updateTitle(@RequestParam int id, @RequestParam String title) {
        return todoService.updateTitle(id, title);
    }

    @DeleteMapping("/delete")
    public String deleteTodo(@RequestParam int id) {
        return todoService.deleteTodo(id);
    }

    @GetMapping("/change")
    public String changeStatus(@RequestParam int id, @RequestParam boolean status) {
        return todoService.changeStatus(id, status);
    }
}
