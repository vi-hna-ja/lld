package problems.libms.manager;

import problems.libms.models.BorrowBook;
import problems.libms.models.Member;

import java.util.HashMap;
import java.util.Map;

public class FineManager {

    private Map<String, Integer> memberFines;
    public FineManager() {
        this.memberFines = new HashMap<>();
    }

    public int calculateFine(BorrowBook borrowBook, int currentTime) {
        int lapsedTime = currentTime - borrowBook.getEndTime() + 1;
        int fineAmount = lapsedTime * 30;
        memberFines.put(borrowBook.getMemberId(), fineAmount);
        return fineAmount;
    }

    public void payFine(Member member, int amount) {
        if (!memberFines.containsKey(member.getMemberId())) {
            System.out.printf("Invalid payment by member %s\n", member.getMemberId());
            return;
        }
        if (memberFines.get(member.getMemberId()) < amount) {
            System.out.printf("Insufficient funds by member %s\n", member.getMemberId());
            return;
        }

        System.out.printf("Member %s paid fine %s\n", member.getMemberId(), amount);
        memberFines.remove(member.getMemberId());
    }
}
