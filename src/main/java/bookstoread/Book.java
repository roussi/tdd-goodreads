package bookstoread;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.Objects;

/**
 * @author aroussi
 * @version %I% %G%
 */
public class Book implements Comparable<Book> {
    private final String title;
    private final String author;
    private final LocalDate publishedOn;

    public Book(String title, String author, LocalDate publishedOn) {
        this.title = title;
        this.author = author;
        this.publishedOn = publishedOn;
    }
    public String getTitle() {
        return title;
    }
    public String getAuthor() {
        return author;
    }
    public LocalDate getPublishedOn() {
        return publishedOn;
    }

    @Override
    public int compareTo(Book o) {
        return this.title.compareTo(o.getTitle());
    }
}
