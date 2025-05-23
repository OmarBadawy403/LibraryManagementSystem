package com.example.library_system.service;

import com.example.library_system.entity.Book;
import com.example.library_system.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        // Inject BookRepository to interact with the database
        this.bookRepository = bookRepository;
    }

    public Book findById(int id) {
        // Retrieve a book by its ID, return null if not found
        return bookRepository.findById(id).orElse(null);
    }

    public List<Book> findAll() {
        // Retrieve all books from the database
        return bookRepository.findAll();
    }

    public void save(Book book) {
        // Save a new book to the database
        bookRepository.save(book);
    }

    public void update(Book book) {
        // Update an existing book (save works as update if ID exists)
        bookRepository.save(book);
    }

    public void delete(int id) {
        // Delete a book by its ID
        bookRepository.deleteById(id);
    }

}
