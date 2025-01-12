package problems.elevator;

import problems.elevator.models.Direction;
import problems.elevator.models.Request;

import java.util.Comparator;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Elevator {
    private String elevatorId;
    private int currentFloor;
    private Direction currentDirection;
    private int capacity;
    private int currentPassengerCount;
    private PriorityBlockingQueue<Request> requests;
    private Lock lock;
    private Condition requestsEmptyCondition;

    public Elevator(int capacity) {
        this.elevatorId = UUID.randomUUID().toString();
        this.capacity = capacity;
        this.currentFloor = 0;
        this.currentPassengerCount = 0;
        this.currentDirection = Direction.IDLE;
        lock = new ReentrantLock();
        this.requests = new PriorityBlockingQueue<>(capacity,
                Comparator.comparingInt(Request::getDestinationFloor));
        this.requestsEmptyCondition = lock.newCondition();
    }

    public void addRequest(Request request) {
        lock.lock();
        try {
            requests.offer(request);
            requestsEmptyCondition.signal();
        } finally {
            lock.unlock();
        }
    }

    public void move() {
        while (true) {
            lock.lock();
            try {
                while (requests.isEmpty()) {
                    System.out.printf("Elevator %s is IDLE on floor %s\n", elevatorId, currentFloor);
                    currentDirection = Direction.IDLE;
                    requestsEmptyCondition.await();
                }
                Request request = requests.poll();
                    int nextDestination = request.getDestinationFloor();
                    currentDirection = currentFloor < nextDestination ? Direction.UP : Direction.DOWN;

                    while (currentFloor != nextDestination) {
                        System.out.printf("Elevator %s moving from floor %s\n", elevatorId, currentFloor);
                        currentFloor += (currentDirection == Direction.UP) ? 1 : -1;
                        Thread.sleep(1200);

                        openDoor();
                    }
            }
            catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            finally {
                lock.unlock();
            }
        }
    }

    public void openDoor() {
        System.out.printf("Door opening on floor %s\n", currentFloor);
        Random random = new Random();
        int passengersEntered = random.nextInt(Math.abs(currentPassengerCount - 1));
        int passengersExited = random.nextInt(capacity);
        currentPassengerCount += (passengersEntered - passengersExited);

        while (currentPassengerCount > capacity) {
            System.out.print("ALERT!!!!! Max capacity reached\n");
            currentPassengerCount -= random.nextInt(capacity / 3);
        }

        System.out.printf("Door closing on floor %s\n", currentFloor);
    }

    public String getElevatorId() {
        return elevatorId;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public Direction getCurrentDirection() {
        return currentDirection;
    }
}
