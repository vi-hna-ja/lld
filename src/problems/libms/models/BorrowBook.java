package problems.libms.models;

import java.util.Random;

public class BorrowBook {

    private String memberId;
    private String bookId;
    private int startTime;
    private int endTime;

    public BorrowBook(String memberId, String bookId, int startTime) {
        this.memberId = memberId;
        this.bookId = bookId;
        this.startTime = startTime;
        this.endTime = new Random().nextInt(0, 7);
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }
}
