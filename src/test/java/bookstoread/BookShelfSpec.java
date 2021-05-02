package bookstoread;

import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author aroussi
 * @version %I% %G%
 */
@DisplayName("Given a book shelf")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BookShelfSpec {

    BookShelf bookShelf;
    Book jUnit5, legacyCode, ddd;

    @BeforeAll
    void init() {
        jUnit5 = new Book("Java Unit testing with junit 5", "Rahul Sharma", LocalDate.now());
        legacyCode = new Book("Working effectively with legacy code", "Michael feathers", LocalDate.of(2004, 9, 1));
        ddd = new Book("Domain Driven Design", "Eric Evans", LocalDate.of(2003, 12, 1));
    }
    @BeforeEach
    void beforeEach() {
        bookShelf = new BookShelf();
    }

    @Test
    @DisplayName("I get empty when no book was added")
    void shelfEmptyWhenNoBookAdded(TestInfo testInfo) {
        System.out.println("Display name : " + testInfo.getDisplayName());
        List<Book> books = bookShelf.books();
        assertTrue(books.isEmpty(), "books should be empty");
    }

    @Test
    @DisplayName("I'm able to add book to book shelf")
    void ImAbleToAddBookToBookShelf() {
        bookShelf.add(jUnit5).add(legacyCode);
        assertEquals(2, bookShelf.books().size());
    }

    @Test
    @DisplayName("I cant update books outside of bookshelf instance")
    void ICantUpdateBooksOutsideOfItsScope() {
        bookShelf.add(jUnit5).add(legacyCode);
        List<Book> books = bookShelf.books();
        assertThrows(UnsupportedOperationException.class, () -> books.add(new Book(null, null, null)));
    }

    @Test
    @DisplayName("I can arrange books based on a natural order")
    void ICanArrangeBooksBasedOnANaturalOrder() {
        bookShelf.add(jUnit5).add(ddd).add(legacyCode);
        assertEquals(jUnit5, bookShelf.books().get(0));
        List<Book> books = bookShelf.arrange(Comparator.naturalOrder());
        assertEquals(List.of(ddd, jUnit5, legacyCode), books,
                "shelf books are not the naturalOrder");
    }

    @Test
    @DisplayName("I can arrange books based on a natural order without affecting books insertion order")
    void ICanArrangeBooksBasedOnANaturalOrderWithoutAffectingBooksInsertionOrder() {
        bookShelf.add(jUnit5).add(ddd).add(legacyCode);
        assertEquals(jUnit5, bookShelf.books().get(0));
        bookShelf.arrange(Comparator.naturalOrder());
        assertEquals(List.of(jUnit5, ddd, legacyCode), bookShelf.books());
    }

    @Test
    @DisplayName("I can arrange books based on author name")
    void ICanArrangeBooksBasedOnAuthorName() {
        bookShelf.add(jUnit5).add(ddd).add(legacyCode);
        List<Book> bookList = bookShelf.arrange(Comparator.comparing(book -> book.getAuthor()));
        assertEquals(List.of(ddd, legacyCode, jUnit5), bookList);
    }
}