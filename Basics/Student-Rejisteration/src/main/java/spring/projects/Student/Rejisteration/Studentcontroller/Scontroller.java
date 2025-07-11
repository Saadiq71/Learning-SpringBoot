package spring.projects.Student.Rejisteration.Studentcontroller;

import org.springframework.web.bind.annotation.*;
import spring.projects.Student.Rejisteration.model.Student;

import java.util.List;
import java.util.ArrayList;

@RestController
@RequestMapping("/Student")
@CrossOrigin(origins = "*")
public class Scontroller {

    List<Student> Slist = new ArrayList<>();


    //Create a student
    @PostMapping("/create")
    String createStudent(@RequestParam String srn, @RequestParam String name, @RequestParam String depart, @RequestParam int sem, @RequestParam double cgpa){
        for(int i = 0; i< Slist.size();i++){
            if(Slist.get(i).getSrn().equals(srn)){
                return "Student already exists";
            }
        }
        Slist.add(new Student(srn,name,depart,sem,cgpa));
        return "Student added Successfully";
    }


    //Read (read all)
    @GetMapping("/getall")
    List<Student> sendall(){ return Slist;}


    //Update
    @PutMapping("/update")
    String updatestudent(@RequestParam String srn, @RequestParam String name, @RequestParam String depart, @RequestParam int sem, @RequestParam double cgpa) {
        for(int i = 0; i< Slist.size();i++){
            if(Slist.get(i).getSrn().equals(srn)){
                Slist.get(i).setName(name);
                Slist.get(i).setDepartment(depart);
                Slist.get(i).setSem(sem);
                Slist.get(i).setCgpa(cgpa);
                return "Updated Successfully";
            }

    }
        return "Student not Found to be updated";
    }



    //delete
    @DeleteMapping("/delete")
    String deletestudent(@RequestParam String srn){
        for(int i =0; i<Slist.size();i++){
            if(Slist.get(i).getSrn().equals(srn)){
                Slist.remove(i);
                return "Student Deleted Successfully";
            }
        }
        return "Student Not found to be deleted";
    }
}
