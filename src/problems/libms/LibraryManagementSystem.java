package problems.libms;

import problems.libms.manager.BookCatalogueManager;
import problems.libms.manager.BorrowingManager;
import problems.libms.manager.MemberCatalogueManager;
import problems.libms.models.Book;
import problems.libms.models.Member;

/*
Requirements:
    The library management system should allow librarians to manage books, members, and borrowing activities.
    The system should support adding, updating, and removing books from the library catalog.
    Each book should have details such as title, author, ISBN, publication year, and availability status.
    The system should allow members to borrow and return books.
    Each member should have details such as name, member ID, contact information, and borrowing history.
    The system should enforce borrowing rules, such as a maximum number of books that can be borrowed at a time and loan duration.
    The system should handle concurrent access to the library catalog and member records.
    The system should be extensible to accommodate future enhancements and new features
 */

public class LibraryManagementSystem {
    public void run() {
        Library library = Library.getLibraryInstance();

        MemberCatalogueManager memberCatalogueManager = library.getMemberCatalogueManager();
        BookCatalogueManager bookCatalogueManager = library.getBookCatalogueManager();
        BorrowingManager borrowingManager = library.getBorrowingManager();

        // add members
        memberCatalogueManager.addMember(new Member("M1", "ABC", "myemail@email.com"));
        memberCatalogueManager.addMember(new Member("M2", "BCD", "myemail@gmail.com"));
        memberCatalogueManager.addMember(new Member("M3", "CDE", "myemail@fmail.com"));
        memberCatalogueManager.addMember(new Member("M4", "DEF", "myemail@hmail.com"));
        memberCatalogueManager.addMember(new Member("M5", "EFG", "myemail@imail.com"));

        // add books
        bookCatalogueManager.addBook(new Book("B1", "Effective Java", "Joshua Bloch", "123456799", 2018, 5));
        bookCatalogueManager.addBook(new Book("B2", "Effective C++", "Joshua Bluuch", "123456889", 2015, 5));
        bookCatalogueManager.addBook(new Book("B3", "Effective Python", "Joshua Bleech", "123455789", 2019, 5));

        borrowingManager.borrowBook("M1", "B1");
        borrowingManager.borrowBook("M1", "B1");

        borrowingManager.returnBook("M1", "B1");
        borrowingManager.returnBook("M2", "B2");
    }
}
