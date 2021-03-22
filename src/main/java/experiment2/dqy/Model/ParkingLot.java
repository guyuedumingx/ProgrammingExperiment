package experiment2.dqy.Model;

import experiment2.dqy.Util.Queue;
import experiment2.dqy.Util.Stack;

public class ParkingLot {
    private Queue waitingLine = new Queue();
    private Stack parkingRoom = new Stack();
    private int numberOfCars;
    private int capacityOfParkingLot;
    private Car[] carList= new Car[capacityOfParkingLot];
    private Car[] logs = new Car[20000000];

    public int getNumberOfCars() {
        return numberOfCars;
    }

    public void setNumberOfCars(int numberOfCars) {
        this.numberOfCars = numberOfCars;
    }

    public int getCapacityOfParkingLot() {
        return capacityOfParkingLot;
    }

    public void setCapacityOfParkingLot(int capacityOfParkingLot) {
        this.capacityOfParkingLot = capacityOfParkingLot;
    }

    boolean isInParkingLot(Car car) {
        for (int i = 0; i < numberOfCars; i++) {
            if (car.equals(carList[i])) return true;
        }
        return false;
    }

    void enterCar(Car car) {
        if (numberOfCars == capacityOfParkingLot) {
            waitingLine.push(car);
        } else {
            carList[++numberOfCars] = car;
            parkingRoom.push(car);
        }
    }

    boolean leaveCar(Car car) {
        if(car.equals(parkingRoom.getTop())) {
            parkingRoom.pop();
            numberOfCars--;
            return true;
        } else {
            return false;
        }
    }


}
