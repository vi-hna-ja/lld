package problems.lrucache;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Cache {
    private final int maxCapacity;
    private final ConcurrentHashMap<Integer, Node> itemToNodeMap;
    private final Node head;
    private final Node tail;
    private final Lock lock;

    public Cache(int maxCapacity) {
        this.maxCapacity = maxCapacity;
        this.itemToNodeMap = new ConcurrentHashMap<>();
        this.head = new Node(-1, -1);
        this.tail = new Node(-1, -1);
        this.lock = new ReentrantLock();

        this.head.setRight(tail);
        this.tail.setLeft(head);
    }

    public void put(int key, int value) {
        lock.lock();
        try {
            if (itemToNodeMap.containsKey(key)) {
                Node nodeToDelete = itemToNodeMap.get(key);
                deleteNode(nodeToDelete.getLeft(), nodeToDelete.getRight());
                itemToNodeMap.remove(key);
            }

            if (hasMaxCapacityReached()) {
                Node nodeToDelete = head.getRight();
                deleteNode(nodeToDelete.getLeft(), nodeToDelete.getRight());
                itemToNodeMap.remove(nodeToDelete.getKey());
            }

            Node newNode = new Node(key, value);
            insertNode(tail.getLeft(), tail, newNode);
            itemToNodeMap.put(key, newNode);
        } finally {
            lock.unlock();
        }
    }

    public Optional<Integer> get(int key) {
        lock.lock();
        try {
            if (!itemToNodeMap.containsKey(key))
                return Optional.empty();

            Node cachedNode = itemToNodeMap.get(key);
            deleteNode(cachedNode.getLeft(), cachedNode.getRight());
            insertNode(tail.getLeft(), tail, cachedNode);

            return Optional.of(cachedNode.getVal());
        } finally {
            lock.unlock();
        }
    }

    private void deleteNode(Node prevNode, Node nextNode) {
        prevNode.setRight(nextNode);
        nextNode.setLeft(prevNode);
    }

    private void insertNode(Node prevNode, Node nextNode, Node newNode) {
        newNode.setLeft(prevNode);
        newNode.setRight(nextNode);
        prevNode.setRight(newNode);
        nextNode.setLeft(newNode);
    }

    private boolean hasMaxCapacityReached() {
        return itemToNodeMap.size() == maxCapacity;
    }

}
