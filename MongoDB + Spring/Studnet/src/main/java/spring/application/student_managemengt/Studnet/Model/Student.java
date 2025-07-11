package spring.application.student_managemengt.Studnet.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Smartbvb")
public class Student {
    @Id
    int srn;
    String name;
    String department;
    double cgpa;

    public Student(int srn, String name, String department, double cgpa) {
        this.srn = srn;
        this.name = name;
        this.department = department;
        this.cgpa = cgpa;
    }

    public int getSrn() {
        return srn;
    }

    public void setSrn(int srn) {
        this.srn = srn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        department = department;
    }

    public double getCgps() {
        return cgpa;
    }

    public void setCgps(double cgps) {
        this.cgpa = cgps;
    }
}
