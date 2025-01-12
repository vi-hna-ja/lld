package problems.elevator;

/*
Requirements
The elevator system should consist of multiple elevators serving multiple floors.
Each elevator should have a capacity limit and should not exceed it.
Users should be able to request an elevator from any floor and select a destination floor.
The elevator system should efficiently handle user requests and optimize the movement of elevators to minimize waiting time.
The system should prioritize requests based on the direction of travel and the proximity of the elevators to the requested floor.
The elevators should be able to handle multiple requests concurrently and process them in an optimal order.
The system should ensure thread safety and prevent race conditions when multiple threads interact with the elevators.

 */
public class ElevatorSystemDemo {

    public void run() {
        ElevatorController controller = new ElevatorController(3, 10);
        controller.start();
        controller.addRequest(0, 5);
        controller.addRequest(2, 7);
        controller.addRequest(3, 5);
        controller.addRequest(6, 2);
        controller.addRequest(3, 0);
        controller.addRequest(5, 9);
    }
}
