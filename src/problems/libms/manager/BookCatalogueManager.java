package problems.libms.manager;

import problems.libms.manager.interfaces.BookCatalogueInterface;
import problems.libms.models.Book;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class BookCatalogueManager implements BookCatalogueInterface {

    private final ConcurrentHashMap<String, Book> bookCatalogue;
    public BookCatalogueManager() {
        this.bookCatalogue = new ConcurrentHashMap<>();
    }

    @Override
    public void addBook(Book book) {
        bookCatalogue.put(book.getBookId(), book);
    }

    @Override
    public void removeBook(String bookId) {
        if (!bookCatalogue.containsKey(bookId)) {
            System.out.printf("Book %s not present. Unable to remove.\n", bookId);
            return;
        }
        bookCatalogue.remove(bookId);
    }

    @Override
    public void updateBook(Book book) {
        if (!bookCatalogue.containsKey(book.getBookId())) {
            System.out.printf("Book %s not present. Unable to update.\n", book.getBookId());
            return;
        }
        bookCatalogue.put(book.getBookId(), book);
    }

    @Override
    public Optional<Book> getBookById(String bookId) {
        if (!bookCatalogue.containsKey(bookId)) {
            System.out.printf("Book %s not present.\n", bookId);
            return Optional.empty();
        }

        return Optional.of(bookCatalogue.get(bookId));
    }
}
