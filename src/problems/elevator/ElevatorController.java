package problems.elevator;

import problems.elevator.models.Direction;
import problems.elevator.models.Request;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ElevatorController {
    private List<Elevator> elevators;
    private Queue<Request> unprocessedRequests;
    private Lock lock;

    public ElevatorController(int elevatorCount, int capacity) {
        elevators = new ArrayList<>();
        for (int i = 0; i < elevatorCount; i++) {
            elevators.add(new Elevator(capacity));
        }
        lock = new ReentrantLock();
        unprocessedRequests = new ConcurrentLinkedQueue<>();
    }

    public void addRequest(int currentFloor, int destinationFloor) {
        Direction direction = currentFloor > destinationFloor ? Direction.DOWN : Direction.UP;
        Request request = new Request(currentFloor, destinationFloor);
        lock.lock();

        try {
            Elevator nextElevator = getNextElevator(request, direction);
            if (nextElevator == null) {
                // add it to unprocessed queue
                unprocessedRequests.add(request);
            }
            nextElevator.addRequest(request);

        } finally {
            lock.unlock();
        }
    }

    private Elevator getNextElevator(Request request, Direction direction) {
        Elevator nextElevator = null;
        int closestDistance = Integer.MAX_VALUE;
        for (Elevator e : elevators) {
            if (e.getCurrentDirection() == Direction.IDLE || e.getCurrentDirection() == direction) {
                int distance = Math.abs(e.getCurrentFloor() - request.getCurrentFloor());
                if (closestDistance > distance) {
                    closestDistance = distance;
                    nextElevator = e;
                }
            }
        }
        return nextElevator;
    }

    public void start() {
        // give each elevator a worker thread separately. each thread will call move()
        for (Elevator e : elevators) {
            new Thread(e::move).start();
        }

        // have a separate thread to check for unprocessed requests
        new Thread(() -> {
            lock.lock();
            try {
                // check for unprocessed requests
                if (!unprocessedRequests.isEmpty()) {
                    Request request = unprocessedRequests.poll();
                    addRequest(request.getCurrentFloor(), request.getDestinationFloor());
                }
            } finally {
                lock.unlock();
            }
        }).start();
    }
}
