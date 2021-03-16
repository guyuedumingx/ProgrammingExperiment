package experiment2.Florence.pojo;

import experiment2.Florence.Factory.CarFactory;
import experiment2.Florence.util.FlorenceQueue;
import experiment2.Florence.util.FlorenceStack;

/**
 * @author Florence
 */
public class CarPark {
    private static final Integer maxSizeOfPark = 3;
    private static final Integer price = 100;
    private final FlorenceQueue<Car> waitQueue = new FlorenceQueue<>();
    private final FlorenceStack<Car> inPark= new FlorenceStack<>();
    private final FlorenceStack<Car> cachePark = new FlorenceStack<>();

    public void arrival(String carNo) {
        Car car = CarFactory.getCar(carNo);
        if (inPark.size()<maxSizeOfPark) {
            inPark.push(car);
            return;
        }
        waitQueue.enQueue(car);
    }

    public void showPark() {
        System.out.println("在停车场中的：");
        System.out.println(inPark);
    }

    public void showWaiting() {
        System.out.println("在便道中的：");
        System.out.println(waitQueue);
    }

    public Car leave(String carNo) {
        while (!inPark.isEmpty()){
            Car topCar = inPark.pop();
            if (carNo.equals(topCar.getCarNo())){
                topCar.setLeave(System.currentTimeMillis());
                return topCar;
            }
            else {
                cachePark.push(topCar);
            }
        }
        while (!cachePark.isEmpty()){
            inPark.push(cachePark.pop());
        }
        return null;
    }

    public double charging(Car car) {
        return car.getPassTime()*price;
    }

}
