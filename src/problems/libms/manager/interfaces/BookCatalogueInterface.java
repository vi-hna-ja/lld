package problems.libms.manager.interfaces;

import problems.libms.models.Book;

import java.util.Optional;

public interface BookCatalogueInterface {
    void addBook(Book book);
    void removeBook(String bookId);
    void updateBook(Book book);
    Optional<Book> getBookById(String bookId);
}
