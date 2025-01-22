package problems.amazonlocker.models;

public enum PackageSize {
    S(4),M(3),L(2),XL(1);

    private int priority;
    PackageSize(int priority) {
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }
}
