package spring.application.student_managemengt.Studnet.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import spring.application.student_managemengt.Studnet.Model.Student;
import spring.application.student_managemengt.Studnet.Repository.Studentrepo;

import java.util.List;

@RestController
@RequestMapping("/Student")
@CrossOrigin(origins = "*")
public class StudentController {

    @Autowired
    private Studentrepo operation;

    // 1. Add a student
    @PostMapping("/add")
    public String addStudent(@RequestParam int id,
                             @RequestParam String name,
                             @RequestParam String Department,
                             @RequestParam double cgpa) {
        if (operation.existsById(id)) {
            return "Student already exists";
        }
        operation.save(new Student(id, name, Department, cgpa));
        return "Student added successfully";
    }

    // 2. Get all students
    @GetMapping("/all")
    public List<Student> getAll() {
        return operation.findAll();
    }

    // 3. Get student by ID
    @GetMapping("/get/{id}")
    public Student getStudent(@PathVariable int id) {
        return operation.findById(id).orElse(null);
    }

    // 4. Update student
    @PutMapping("/update/{id}")
    public String updateStudent(@PathVariable int id,
                                @RequestParam String name,
                                @RequestParam String Department,
                                @RequestParam double cgpa) {
        Student existing = operation.findById(id).orElse(null);
        if (existing != null) {
            existing.setName(name);
            existing.setDepartment(Department);
            existing.setCgps(cgpa);
            operation.save(existing);
            return "Student updated successfully";
        }
        return "Student not found";
    }

    // 5. Delete student by ID
    @DeleteMapping("/delete/{id}")
    public String deleteStudent(@PathVariable int id) {
        if (operation.existsById(id)) {
            operation.deleteById(id);
            return "Student deleted successfully";
        }
        return "Student not found";
    }

    // 6. Filter students by department
    @GetMapping("/department/{dept}")
    public List<Student> getByDepartment(@PathVariable String dept) {
        return operation.findByDepartmentIgnoreCase(dept);
    }
}
