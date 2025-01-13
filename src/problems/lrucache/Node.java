package problems.lrucache;

public class Node {
    private int key;
    private int val;
    private Node left;
    private Node right;

    Node(int key, int val) {
        this.key = key;
        this.val = val;
        this.left = null;
        this.right = null;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

    public int getKey() {
        return key;
    }

    public int getVal() {
        return val;
    }
}