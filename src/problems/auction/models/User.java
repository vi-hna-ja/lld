package problems.auction.models;

public class User {
    private String userId;
    private String email;
    private String password;
    private boolean isLoginBlocked;
    private Long blockedUntil;

    public User(String userId, String email, String password) {
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.isLoginBlocked = false;
        this.blockedUntil = null;
    }

    public String getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public boolean isLoginBlocked() {
        return isLoginBlocked;
    }

    public Long getBlockedUntil() {
        return blockedUntil;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLoginBlocked(boolean loginBlocked) {
        isLoginBlocked = loginBlocked;
    }

    public void setBlockedUntil(Long blockedUntil) {
        this.blockedUntil = blockedUntil;
    }

    public void notify(String entityId, int price) {
        System.out.printf("User %s has been notified of entity %s for price %s thrice!!!", userId, entityId, price);
    }
}
