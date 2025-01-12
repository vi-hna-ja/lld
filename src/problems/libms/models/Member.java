package problems.libms.models;

import java.util.*;

public class Member {
    private String memberId;
    private String name;
    private String email;
    private final Map<String, BorrowBook> currentBorrowedBooks;
    private final List<String> borrowingHistory;

    public Member(String memberId, String name, String email) {
        this.memberId = memberId;
        this.name = name;
        this.email = email;
        this.currentBorrowedBooks = new HashMap<>();
        this.borrowingHistory = new ArrayList<>();
    }

    public String getMemberId() {
        return memberId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Map<String, BorrowBook> getCurrentBorrowedBooks() {
        return currentBorrowedBooks;
    }

    public List<String> getBorrowingHistory() {
        return borrowingHistory;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
