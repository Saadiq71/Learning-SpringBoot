package library.booktrack.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document("Books")
public class Book {
    @Id
    int bid;
    String title;
    int stock;

    public Book(int bid, String title, int stock) {
        this.bid = bid;
        this.title = title;
        this.stock = stock;
    }

    @JsonIgnore
    public List<Student> Borrowed = new ArrayList<>(); // ✅ fixed: removed stock as initial capacity

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void updatelist(Student s) {
        Borrowed.add(s);
    }
}
