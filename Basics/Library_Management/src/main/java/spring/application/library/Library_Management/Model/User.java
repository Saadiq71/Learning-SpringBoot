package spring.application.library.Library_Management.Model;



import java.util.HashMap;

import java.util.Map;

public class User {
   public  Map<Integer,String> Borrowed =new HashMap<>();
    private int uid;
    private String name;

    public User(int uid, String name) {
        this.uid = uid;
        this.name = name;

    }

    // setter
    public void setUid(int uid) {
        this.uid = uid;
    }

    public void setName(String name) {
        this.name = name;
    }

    // getter
    public int getUid() {
        return uid;
    }

    public String getName() {
        return name;
    }

public void borrow(int bid, String title){
        Borrowed.put(bid,title);
}
    public String Rbook(int bid){
        if(Borrowed.containsKey(bid)){
            Borrowed.remove(bid);
            return "Book returned Successfully";
        }

        return "No Book with that id was borrowed";
    }

}