package spring.projects.Student.Rejisteration.model;

public class Student {
    String srn;
    String name;
    String Department;
    int sem;
    double cgpa;

    public Student(String srn, String name, String department, int sem, double cgpa) {
        this.srn = srn;
        this.name = name;
        Department = department;
        this.sem = sem;
        this.cgpa = cgpa;
    }

    public String getSrn() {
        return srn;
    }
    public String getName() {
        return name;
    }
    public String getDepartment() {
        return Department;
    }
    public int getSem() {
        return sem;
    }
    public double getCgpa() {
        return cgpa;
    }


    public void setSrn(String srn) {
        this.srn = srn;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setDepartment(String department) {
        Department = department;
    }
    public void setSem(int sem) {
        this.sem = sem;
    }
    public void setCgpa(double cgpa) {
        this.cgpa = cgpa;
    }
}
