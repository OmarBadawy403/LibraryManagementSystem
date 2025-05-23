package com.example.library_system.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "borrow_records")
public class BorrowRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generated ID for each borrow record
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false) // The user who borrowed the book
    private User user;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false) // The borrowed book
    private Book book;

    private LocalDate borrowDate;   // Date when the book was borrowed
    private LocalDate returnDate;   // Date when the book was returned (if any)
    private boolean returned;       // Whether the book is returned or still borrowed

    public BorrowRecord() {}

    // Getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public boolean isReturned() {
        return returned;
    }

    public void setReturned(boolean returned) {
        this.returned = returned;
    }
}
