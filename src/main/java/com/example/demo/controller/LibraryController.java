package com.example.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.Book;
import com.example.demo.service.LibraryService;

import java.util.List;

@RestController
@RequestMapping("/library")
public class LibraryController {

    @Autowired
    private LibraryService libraryService;

    @PostMapping("/addBook")
    public void addBook(@RequestBody Book book) {
        libraryService.addBook(book);
    }

    @DeleteMapping("/removeBook/{ISBN}")
    public void removeBook(@PathVariable String ISBN) {
        libraryService.removeBook(ISBN);
    }

    @GetMapping("/findBookByTitle")
    public List<Book> findBookByTitle(@RequestParam String title) {
        return libraryService.findBookByTitle(title);
    }

    @GetMapping("/findBookByAuthor")
    public List<Book> findBookByAuthor(@RequestParam String author) {
        return libraryService.findBookByAuthor(author);
    }

    @GetMapping("/listAllBooks")
    public List<Book> listAllBooks() {
        return libraryService.listAllBooks();
    }

    @GetMapping("/listAvailableBooks")
    public List<Book> listAvailableBooks() {
        return libraryService.listAvailableBooks();
    }
}