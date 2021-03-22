package experiment2.Florence.Factory;

import experiment2.Florence.pojo.Car;

/**
 * @author Florence
 */
public class CarFactory {
    public static Car getCar(String carNo){
        Car car = new Car(System.currentTimeMillis(),carNo);
        return car;
    }
}
