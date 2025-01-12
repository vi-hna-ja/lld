package problems.vendingmachine.models;

public enum Note {
    TWENTY(20),
    FIFTY(50);

    private final int value;

    Note(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
