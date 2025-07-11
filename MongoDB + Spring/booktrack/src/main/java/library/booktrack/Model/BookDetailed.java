//this is dto DTO stands for Data Transfer Object.
//
//👉 Simple Meaning:
//A DTO is just a plain Java class used to carry data from one part of your program to another — like from your backend to Postman (or frontend). It contains only fields and getters/setters, no logic.
package library.booktrack.Model;

import java.util.List;

public class BookDetailed {
    public int bid;
    public String title;
    public int stock;
    public List<String> borrowedBy;

    public BookDetailed(int bid, String title, int stock, List<String> borrowedBy) {
        this.bid = bid;
        this.title = title;
        this.stock = stock;
        this.borrowedBy = borrowedBy;
    }
}
