package com.example.demo.servicetest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.demo.entity.Book;
import com.example.demo.repository.BookRepository;
import com.example.demo.service.LibraryService;


class LibraryServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private LibraryService libraryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddBook() {
        Book book = new Book("Title", "Author", "123456789", "Genre", 2021, "Department", true);
        when(bookRepository.findByISBN("123456789")).thenReturn(null);

        libraryService.addBook(book);
        verify(bookRepository, times(1)).save(book);
    }

    @Test
    void testRemoveBook() {
        Book book = new Book("Title", "Author", "123456789", "Genre", 2021, "Department", true);
        when(bookRepository.findByISBN("123456789")).thenReturn(book);

        libraryService.removeBook("123456789");
        verify(bookRepository, times(1)).delete(book);
    }

    @Test
    void testFindBookByTitle() {
        Book book = new Book("Title", "Author", "123456789", "Genre", 2021, "Department", true);
        when(bookRepository.findByTitleContainingIgnoreCase("title")).thenReturn(Arrays.asList(book));

        List<Book> books = libraryService.findBookByTitle("title");
        assertEquals(1, books.size());
        assertEquals("Title", books.get(0).getTitle());
    }

    @Test
    void testFindBookByAuthor() {
        Book book = new Book("Title", "Author", "123456789", "Genre", 2021, "Department", true);
        when(bookRepository.findByAuthorContainingIgnoreCase("author")).thenReturn(Arrays.asList(book));

        List<Book> books = libraryService.findBookByAuthor("author");
        assertEquals(1, books.size());
        assertEquals("Author", books.get(0).getAuthor());
    }

    @Test
    void testListAllBooks() {
        Book book = new Book("Title", "Author", "123456789", "Genre", 2021, "Department", true);
        when(bookRepository.findAll()).thenReturn(Arrays.asList(book));

        List<Book> books = libraryService.listAllBooks();
        assertEquals(1, books.size());
        assertEquals("Title", books.get(0).getTitle());
    }

    @Test
    void testListAvailableBooks() {
        Book book1 = new Book("Title1", "Author1", "123456789", "Genre", 2021, "Department", true);
        Book book2 = new Book("Title2", "Author2", "987654321", "Genre", 2021, "Department", false);
        when(bookRepository.findAll()).thenReturn(Arrays.asList(book1, book2));

        List<Book> availableBooks = libraryService.listAvailableBooks();
        assertEquals(1, availableBooks.size());
        assertTrue(availableBooks.get(0).isAvailability());
    }
}
