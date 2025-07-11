package spring.application.todo.Todo.Application.Service;

import org.springframework.stereotype.Service;
import spring.application.todo.Todo.Application.Model.Todo;

import java.util.ArrayList;
import java.util.List;

@Service
public class TodoService {

    private final List<Todo> todoList = new ArrayList<>();

    public String addTodo(int id, String title) {
        for (int i = 0; i < todoList.size(); i++) {
            if (todoList.get(i).getId() == id) {
                return "Task already exists with this ID";
            }
        }
        todoList.add(new Todo(id, title, false));
        return "Todo Added Successfully";
    }

    public List<Todo> getAllTodos() {
        return todoList;
    }

    public String updateTitle(int id, String title) {
        for (int i = 0; i < todoList.size(); i++) {
            if (todoList.get(i).getId() == id) {
                todoList.get(i).setTitle(title);
                return "Updated title successfully";
            }
        }
        return "No todo found with this ID to update";
    }

    public String deleteTodo(int id) {
        for (int i = 0; i < todoList.size(); i++) {
            if (todoList.get(i).getId() == id) {
                todoList.remove(i);
                return "Task Deleted Successfully";
            }
        }
        return "No todo found with this ID to delete";
    }

    public String changeStatus(int id, boolean status) {
        for (int i = 0; i < todoList.size(); i++) {
            if (todoList.get(i).getId() == id) {
                todoList.get(i).setCompleted(status);
                return "Status Updated";
            }
        }
        return "No task found with this ID";
    }
}
