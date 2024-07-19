package com.example.demo.entitytest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

import com.example.demo.entity.Book;

class BookTest {

    @Test
    void testBookGettersAndSetters() {
        Book book = new Book();
        book.setTitle("Title");
        book.setAuthor("Author");
        book.setISBN("123456789");
        book.setGenre("Genre");
        book.setPublicationYear(2021);
        book.setDepartment("Department");
        book.setAvailability(true);

        assertEquals("Title", book.getTitle());
        assertEquals("Author", book.getAuthor());
        assertEquals("123456789", book.getISBN());
        assertEquals("Genre", book.getGenre());
        assertEquals(2021, book.getPublicationYear());
        assertEquals("Department", book.getDepartment());
        assertTrue(book.isAvailability());
    }

    @Test
    void testBookConstructor() {
        Book book = new Book("Title", "Author", "123456789", "Genre", 2021, "Department", true);

        assertEquals("Title", book.getTitle());
        assertEquals("Author", book.getAuthor());
        assertEquals("123456789", book.getISBN());
        assertEquals("Genre", book.getGenre());
        assertEquals(2021, book.getPublicationYear());
        assertEquals("Department", book.getDepartment());
        assertTrue(book.isAvailability());
    }
}