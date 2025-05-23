package com.example.library_system.controller;

import com.example.library_system.entity.BorrowRecord;
import com.example.library_system.service.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/borrow")
public class BorrowController {

    @Autowired
    private BorrowService borrowService;

    // Show the list of books borrowed by the currently logged-in user
    @GetMapping("/my-borrows")
    public String viewMyBorrows(Model model, Authentication auth) {
        String username = auth.getName();
        List<BorrowRecord> borrows = borrowService.getBorrowsByUsername(username);
        model.addAttribute("borrows", borrows);
        return "borrow/my-borrows";
    }

    // Handle borrowing a book by its ID
    @PostMapping("/borrow-book/{bookId}")
    public String borrowBook(@PathVariable int bookId, Authentication auth) {
        String username = auth.getName();
        borrowService.borrowBook(username, bookId);
        return "redirect:/books";
    }

    // Handle returning a borrowed book by its borrow record ID
    @PostMapping("/return-book/{borrowId}")
    public String returnBook(@PathVariable int borrowId) {
        borrowService.returnBook(borrowId);
        return "redirect:/borrow/my-borrows";
    }
}
