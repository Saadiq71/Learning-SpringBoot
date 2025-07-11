Here’s a well-structured and professional `README.md` file for your **BookTrack** project using **Spring Boot + MongoDB**, based on your latest code and logic:

---

# 📚 BookTrack - Spring Boot + MongoDB Project

**BookTrack** is a simple library management system built using **Spring Boot** and **MongoDB**. It allows students to borrow and return books, while keeping track of stock and borrowers for each book.

---

## 🛠️ Technologies Used

| Technology          | Purpose                                     |
| ------------------- | ------------------------------------------- |
| Spring Boot         | Backend REST API framework                  |
| MongoDB             | NoSQL database for storing books & students |
| Spring Data MongoDB | To perform database operations              |
| Postman             | API testing and development tool            |
| Java 17             | Programming language                        |
| Maven               | Dependency management                       |

---

## 📂 Project Structure

```
booktrack/
│
├── Model/
│   ├── Book.java
│   └── Student.java
│
├── Repos/
│   ├── Bookrepo.java
│   └── Studentrepo.java
│
├── Controller/
│   └── Appcontroller.java
│
├── DTO/
│   └── BookDetailsDTO.java         (for seeall_detailed)
│
├── BooktrackApplication.java       (Main entry point)
└── application.properties          (MongoDB connection)
```

---

## 🔄 Data Flow (High-Level Logic)

### 1. **Adding Data**

* `POST /add_book`: Adds a book to MongoDB with ID, title, and stock.
* `POST /add_student`: Adds a student with roll number and name.

### 2. **Borrowing Logic**

* Student borrows a book:

  * Controller receives student roll number and book details.
  * Book stock is decreased.
  * Student document is updated to reference the borrowed book.
  * Book document's `Borrowed` list is updated to include the student.

### 3. **Returning Logic**

* Student returns a book:

  * Book stock is increased.
  * Student's book reference is set to `null`.
  * (Currently, the `Borrowed` list in `Book` is not cleaned, but you can improve this.)

---

## 🔗 API Endpoints

| Method | Endpoint                   | Description                   |
| ------ | -------------------------- | ----------------------------- |
| POST   | `/library/add_book`        | Add a new book                |
| POST   | `/library/add_student`     | Add a new student             |
| PUT    | `/library/borrow`          | Borrow a book                 |
| PUT    | `/library/return`          | Return a book                 |
| GET    | `/library/seeall`          | View all books                |
| GET    | `/library/seeall_detailed` | View all books with borrowers |

---

## 🧠 Key Business Logic

### 📘 Book Model

* Fields: `bid`, `title`, `stock`, `Borrowed (List<Student>)`
* Annotated with `@Document("Books")`

### 👤 Student Model

* Fields: `rollno`, `name`, `book (reference)`
* Annotated with `@Document("Student")` and uses `@DBRef`

### 💾 MongoDB Integration

* Book and Student are stored in separate collections.
* `@DBRef` is used in `Student` to link to `Book`.
* Repository interfaces (`MongoRepository`) are used to perform CRUD operations.

---

## 📊 Example Data

**Book:**

```json
{
  "bid": 101,
  "title": "Java Basics",
  "stock": 5
}
```

**Student:**

```json
{
  "rollno": 1,
  "name": "Saadiq",
  "book": {
    "bid": 101,
    "title": "Java Basics"
  }
}
```

---

## 🧪 Testing the API

You can use the provided Postman JSON file to test all the endpoints.

1. Start your server using:

```bash
mvn spring-boot:run
```

2. Import the collection into Postman.
3. Test APIs like **add\_book**, **borrow**, **return**, etc.

---

## 🚀 Future Improvements

* Track which student returned which book and when
* Add history logs
* Clean up borrowed list when book is returned
* Pagination support for viewing books/students

---

If you’d like this as a real `README.md` file with formatting and emojis, let me know—I can generate the file for you too.
