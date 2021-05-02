package bookstoread;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author aroussi
 * @version %I% %G%
 */
public class BookShelf {

    private List<Book> books;

    public BookShelf() {
        this.books = new ArrayList<>();
    }

    public List<Book> books() {
        return Collections.unmodifiableList(books);
    }

    public BookShelf add(Book book) {
        this.books.add(book);
        return this;
    }

    public List<Book> arrange(Comparator<Book> comparator) {
        return books.stream().sorted(comparator).collect(Collectors.toList());
    }
}
