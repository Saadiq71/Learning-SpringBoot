package spring.application.library.Library_Management.Model;

public class Book {
    int id;
    String title;

    public Book(int id, String title) {
        this.id = id;
        this.title = title;
    }

    //getter
    public int getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }

    //setter
    public void setId(int id) {
        this.id = id;
    }
    public void setTitle(String title) {
        this.title = title;
    }
}
