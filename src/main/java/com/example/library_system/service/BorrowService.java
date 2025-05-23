package com.example.library_system.service;

import com.example.library_system.entity.BorrowRecord;
import com.example.library_system.entity.User;
import com.example.library_system.entity.Book;
import com.example.library_system.repository.BorrowRecordRepository;
import com.example.library_system.repository.UserRepository;
import com.example.library_system.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BorrowService {

    private final BorrowRecordRepository borrowRecordRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    @Autowired
    public BorrowService(BorrowRecordRepository borrowRecordRepository,
                         UserRepository userRepository,
                         BookRepository bookRepository) {
        // Inject the repositories to access data
        this.borrowRecordRepository = borrowRecordRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    public List<BorrowRecord> getBorrowsByUsername(String username) {
        // Find the user by username
        User user = userRepository.findByUsername(username);
        if (user == null) {
            // Return empty list if user not found
            return List.of();
        }
        // Return all borrow records of the user by user id
        return borrowRecordRepository.findByUserId(user.getId());
    }

    public void borrowBook(String username, int bookId) {
        // Find user and book by username and bookId
        User user = userRepository.findByUsername(username);
        Book book = bookRepository.findById(bookId).orElse(null);

        // Check if user and book exist and there are available copies
        if (user != null && book != null && book.getAvailableCopies() > 0) {
            // Create new borrow record
            BorrowRecord borrowRecord = new BorrowRecord();
            borrowRecord.setUser(user);
            borrowRecord.setBook(book);
            borrowRecord.setBorrowDate(LocalDate.now());
            borrowRecord.setReturned(false);
            // Save the borrow record
            borrowRecordRepository.save(borrowRecord);

            // Decrement available copies and save the updated book
            book.setAvailableCopies(book.getAvailableCopies() - 1);
            bookRepository.save(book);
        }
    }

    public void returnBook(int borrowId) {
        // Find borrow record by id
        borrowRecordRepository.findById(borrowId).ifPresent(borrowRecord -> {
            if (!borrowRecord.isReturned()) {
                // Mark as returned and set return date
                borrowRecord.setReturned(true);
                borrowRecord.setReturnDate(LocalDate.now());
                borrowRecordRepository.save(borrowRecord);

                // Increment book's available copies and save
                Book book = borrowRecord.getBook();
                book.setAvailableCopies(book.getAvailableCopies() + 1);
                bookRepository.save(book);
            }
        });
    }
}
