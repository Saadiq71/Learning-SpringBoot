package spring.application.student_managemengt.Studnet.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import spring.application.student_managemengt.Studnet.Model.Student;

import java.util.List;

public interface Studentrepo extends MongoRepository<Student,Integer> {
    List<Student> findByDepartmentIgnoreCase(String department);
}
