package problems.libms;

import problems.libms.models.Book;
import problems.libms.models.BorrowBook;
import problems.libms.models.Member;

public class RuleEngine {

    private static final int MAX_LIMIT = 3;
    private static final int DURATION_LIMIT = 3;
    public RuleEngine() {
    }
    public boolean canBorrowBook(Member member, Book book) {
        if (!book.isAvailability() || book.getQuantity() == 0) {
            System.out.printf("Cannot borrow book %s. Unavailable\n", book.getBookId());
            return false;
        }

        if (member.getCurrentBorrowedBooks().containsKey(book.getBookId())) {
            System.out.printf("Member %s has already borrowed book %s\n",
                    member.getMemberId(), book.getBookId());
            return false;
        }
        if (member.getCurrentBorrowedBooks().size() == MAX_LIMIT) {
            System.out.println("Cannot borrow. Member limit reached");
            return false;
        }

        return true;
    }

    public boolean canReturnBook(Member member, Book book) {
        return member.getCurrentBorrowedBooks().containsKey(book.getBookId());
    }

    public boolean isFineApplicable(BorrowBook borrowBook, int currentTime) {
        return Math.abs(borrowBook.getEndTime() - currentTime) > DURATION_LIMIT;
    }
}
