package problems.libms;

import problems.libms.manager.BookCatalogueManager;
import problems.libms.manager.BorrowingManager;
import problems.libms.manager.MemberCatalogueManager;

public class Library {
    private static Library libraryInstance;
    private final BookCatalogueManager bookCatalogueManager;
    private final MemberCatalogueManager memberCatalogueManager;
    private final BorrowingManager borrowingManager;

    private Library() {
        this.bookCatalogueManager = new BookCatalogueManager();
        this.memberCatalogueManager = new MemberCatalogueManager();
        this.borrowingManager = new BorrowingManager(bookCatalogueManager, memberCatalogueManager);
    }

    public static Library getLibraryInstance() {
        if (libraryInstance == null) {
            synchronized (Library.class) {
                if (libraryInstance == null) {
                    libraryInstance = new Library();
                }
            }
        }
        return libraryInstance;
    }

    public BookCatalogueManager getBookCatalogueManager() {
        return bookCatalogueManager;
    }

    public MemberCatalogueManager getMemberCatalogueManager() {
        return memberCatalogueManager;
    }

    public BorrowingManager getBorrowingManager() {
        return borrowingManager;
    }
}
