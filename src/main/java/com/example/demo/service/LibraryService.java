package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Book;
import com.example.demo.repository.BookRepository;

@Service
public class LibraryService {

    @Autowired
    private BookRepository bookRepository;

    public void addBook(Book book) {
        try {
            if (bookRepository.findByISBN(book.getISBN()) == null) {
                bookRepository.save(book);
            } else {
                System.out.println("Book with ISBN " + book.getISBN() + " already exists.");
            }
        } catch (Exception e) {
            System.out.println("An error occurred while adding the book: " + e.getMessage());
        }
    }

    public void removeBook(String ISBN) {
        try {
            Book book = bookRepository.findByISBN(ISBN);
            if (book != null) {
                bookRepository.delete(book);
            } else {
                throw new BookNotFoundException("Book with ISBN " + ISBN + " not found.");
            }
        } catch (BookNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("An error occurred while removing the book: " + e.getMessage());
        }
    }

    public List<Book> findBookByTitle(String title) {
        try {
            List<Book> books = bookRepository.findByTitleContainingIgnoreCase(title);
            if (books.isEmpty()) {
                throw new BookNotFoundException("No books found with title: " + title);
            }
            return books;
        } catch (BookNotFoundException e) {
            System.out.println(e.getMessage());
            return null;
        } catch (Exception e) {
            System.out.println("An error occurred while searching for books by title: " + e.getMessage());
            return null;
        }
    }

    public List<Book> findBookByAuthor(String author) {
        try {
            List<Book> books = bookRepository.findByAuthorContainingIgnoreCase(author);
            if (books.isEmpty()) {
                throw new BookNotFoundException("No books found by author: " + author);
            }
            return books;
        } catch (BookNotFoundException e) {
            System.out.println(e.getMessage());
            return null;
        } catch (Exception e) {
            System.out.println("An error occurred while searching for books by author: " + e.getMessage());
            return null;
        }
    }

    public List<Book> listAllBooks() {
        try {
            return bookRepository.findAll();
        } catch (Exception e) {
            System.out.println("An error occurred while listing all books: " + e.getMessage());
            return null;
        }
    }

    public List<Book> listAvailableBooks() {
        return bookRepository.findAll().stream()
                .filter(Book::isAvailability)
                .toList();
    }

	
}

