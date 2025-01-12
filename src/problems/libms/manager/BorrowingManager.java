package problems.libms.manager;

import problems.libms.RuleEngine;
import problems.libms.manager.interfaces.BookCatalogueInterface;
import problems.libms.manager.interfaces.MemberCatalogueInterface;
import problems.libms.models.Book;
import problems.libms.models.BorrowBook;
import problems.libms.models.Member;

import java.util.Optional;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

public class BorrowingManager {
    private final BookCatalogueInterface bookCatalogueManager;
    private final MemberCatalogueInterface memberCatalogueManager;
    private final RuleEngine ruleEngine;
    private final FineManager fineManager;
    private final ReentrantLock lock = new ReentrantLock();
    private int startTimeCounter;
    private int endTimeCounter;

    public BorrowingManager(BookCatalogueInterface bookCatalogueManager,
                            MemberCatalogueInterface memberCatalogueManager) {
        this.bookCatalogueManager = bookCatalogueManager;
        this.memberCatalogueManager = memberCatalogueManager;
        this.ruleEngine = new RuleEngine();
        this.fineManager = new FineManager();
        this.startTimeCounter = 0;
        this.endTimeCounter = 3;
    }

    public void borrowBook(String memberId, String bookId) {
        lock.lock();
        try {
            Optional<Member> optionalMember = memberCatalogueManager.getMemberById(memberId);
            Optional<Book> optionalBook = bookCatalogueManager.getBookById(bookId);
            if (optionalMember.isEmpty() || optionalBook.isEmpty()) {
                return;
            }

            Member member = optionalMember.get();
            Book book = optionalBook.get();

            if (!ruleEngine.canBorrowBook(member, book)) {
                System.out.printf("Member %s cannot borrow book %s\n", memberId, bookId);
                return;
            }

            book.decreaseQuantity();

            member.getCurrentBorrowedBooks().put(bookId, new BorrowBook(memberId, bookId, startTimeCounter++));
            member.getBorrowingHistory().add(bookId);
        } finally {
            lock.unlock();
        }
    }

    public void returnBook(String memberId, String bookId) {
        lock.lock();
        try {
            Optional<Member> optionalMember = memberCatalogueManager.getMemberById(memberId);
            Optional<Book> optionalBook = bookCatalogueManager.getBookById(bookId);
            if (optionalMember.isEmpty() || optionalBook.isEmpty()) {
                return;
            }

            Member member = optionalMember.get();
            Book book = optionalBook.get();

            if (!ruleEngine.canReturnBook(member, book)) {
                System.out.printf("Member %s did not borrow book %s\n",
                        member.getMemberId(), book.getBookId());
                return;
            }

            synchronized (member) {
                int currentTime = endTimeCounter++ + new Random().nextInt(0, 10);
                BorrowBook borrowBook = member.getCurrentBorrowedBooks().get(book.getBookId());

                if (ruleEngine.isFineApplicable(borrowBook, currentTime)) {
                    int fineAmount = fineManager.calculateFine(borrowBook, currentTime);
                    System.out.printf("Fine of amount %s is applicable for member %s on book %s\n",
                            fineAmount, memberId, bookId);

                    fineManager.payFine(member, fineAmount);
                }
            }

            book.increaseQuantity();

            member.getCurrentBorrowedBooks().remove(bookId);
        }
        finally {
            lock.unlock();
        }
    }
}
