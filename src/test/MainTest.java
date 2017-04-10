package test;

import library.Library;
import library.models.Book;
import library.models.BookInstance;
import library.models.Booking;
import library.models.Reader;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Di on 10.04.2017.
 */
class MainTest
{

    private static Library library;

    @BeforeAll
    public static void init()
    {
        library = new Library();
    }

    @Test
    public void buyBookTestCatalog()
    {
        library.buyBook("Intro to Java", "Schildt", "1241241ada", 5, 2017);
        assertEquals(1, library.getCatalog().size());

        Book book = new Book("Schildt", "Intro to Java", 2017, "1241241ada");
        assertTrue(library.getCatalog().contains(book));

        Book bookFromCatalog = library.getCatalog().iterator().next();
        assertTrue(book.getTitle().equals(bookFromCatalog.getTitle()));
        assertTrue(book.getAuthor().equals(bookFromCatalog.getAuthor()));
        assertTrue(book.getIsbn().equals(bookFromCatalog.getIsbn()));
        assertTrue(book.getYear() == bookFromCatalog.getYear());
    }


    @Test
    public void buyBookTestStore()
    {
        library.buyBook("Intro to Java", "Schildt", "1241241ada", 5, 2017);

        assertEquals(5, library.getStore().size());
        Book book = new Book("Schildt", "Intro to Java", 2017, "1241241ada");

        for (BookInstance instance:library.getStore()
             )
        {
            Book bookFromStore = instance.getBook();
            assertTrue(book.getTitle().equals(bookFromStore.getTitle()));
            assertTrue(book.getAuthor().equals(bookFromStore.getAuthor()));
            assertTrue(book.getIsbn().equals(bookFromStore.getIsbn()));
            assertTrue(book.getYear() == bookFromStore.getYear());
        }
    }


    @Test
    public void takeBookTest()
    {
        library.buyBook("Intro to Java", "Schildt", "1241241ada", 5, 2017);

        library.takeBook("John", "Connor", "Androidovich", 12345678,
                "Intro to Java");

        Book trueBook = new Book("Schildt", "Intro to Java", 2017, "1241241ada");

                Set<Booking> bookings = library.getBookings();
        Booking booking = bookings.iterator().next();
        Book book = booking.getBookInstance().getBook();

        assertTrue(book.getAuthor().equals(trueBook.getAuthor()));
        assertTrue(book.getTitle().equals(trueBook.getTitle()));
        assertTrue(book.getYear() == (trueBook.getYear()));
        assertTrue(book.getIsbn() == (trueBook.getIsbn()));

        Set<Reader> readers = library.getReaders();
        Reader reader = readers.iterator().next();

        Reader trueReader = new Reader("John", "Connor", "Androidovich", 12345678);

        assertTrue(reader.getFirstName().equals(trueReader.getFirstName()));
        assertTrue(reader.getSecondName().equals(trueReader.getSecondName()));
        assertTrue(reader.getLastName().equals(trueReader.getLastName()));
        assertTrue(reader.getPassportNumber() == (trueReader.getPassportNumber()));
    }


    @Test
    public void testReturnBook()
    {
        library.buyBook("Intro to Java", "Schildt", "1241241ada", 5, 2017);
        library.buyBook("How to hack Pentagon", "Snowden", "54524dfh", 5, 2015);

        library.takeBook("John", "Connor", "Androidovich", 12345678,
                "Intro to Java");

        library.takeBook("John", "Connor", "Androidovich", 12345678,
                "How to hack Pentagon");

        library.returnBook("John", "Connor", "Androidovich", 12345678,
                "Intro to Java");

        Set<Booking> bookings = library.getBookings();
        Booking booking = bookings.iterator().next();

        Reader reader = booking.getReader();
        Book book = booking.getBookInstance().getBook();

        Reader trueReader = new Reader("John", "Connor", "Androidovich", 12345678);
        Book trueBook = new Book("Snowden", "How to hack Pentagon", 2015, "54524dfh");

        assertTrue(reader.getFirstName().equals(trueReader.getFirstName()));
        assertTrue(reader.getSecondName().equals(trueReader.getSecondName()));
        assertTrue(reader.getLastName().equals(trueReader.getLastName()));
        assertTrue(reader.getPassportNumber() == (trueReader.getPassportNumber()));

        assertTrue(book.getAuthor().equals(trueBook.getAuthor()));
        assertTrue(book.getTitle().equals(trueBook.getTitle()));
        assertTrue(book.getYear() == (trueBook.getYear()));
        assertTrue(book.getIsbn() == (trueBook.getIsbn()));
    }




    @AfterEach
    public void clearAll()
    {
        library = new Library();
    }


}