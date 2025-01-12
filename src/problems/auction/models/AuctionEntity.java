package problems.auction.models;

import java.util.Random;

public class AuctionEntity {
    private String entityId;
    private String name;
    private String description;
    private int startingPrice;
    private int currentPrice;
    private int biddingDuration;
    private AuctionEntityState state;
    private String buyerId;
    private String sellerId;

    public AuctionEntity(String entityId, String name, String description, int startingPrice, int biddingDuration, String sellerId) {
        this.entityId = entityId;
        this.name = name;
        this.description = description;
        this.startingPrice = startingPrice;
        this.currentPrice = 0;
        this.biddingDuration = new Random().nextInt(10) + biddingDuration;
        this.state = AuctionEntityState.CREATED;
        this.buyerId = null;
        this.sellerId = sellerId;
    }

    public String getEntityId() {
        return entityId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStartingPrice() {
        return startingPrice;
    }

    public void setStartingPrice(int startingPrice) {
        this.startingPrice = startingPrice;
    }

    public int getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(int currentPrice) {
        this.currentPrice = currentPrice;
    }

    public int getBiddingDuration() {
        return biddingDuration;
    }

    public void setBiddingDuration(int biddingDuration) {
        this.biddingDuration = biddingDuration;
    }

    public AuctionEntityState getState() {
        return state;
    }

    public void setState(AuctionEntityState state) {
        this.state = state;
    }

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }
}
