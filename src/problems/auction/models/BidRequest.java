package problems.auction.models;

public class BidRequest {
    private String emailId;
    private String entityId;
    private int price;

    public BidRequest(String emailId, String entityId, int price) {
        this.emailId = emailId;
        this.entityId = entityId;
        this.price = price;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getEntityId() {
        return entityId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
