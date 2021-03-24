package experiment2.wangwei.controller;

import experiment2.wangwei.pojo.Car;
import experiment2.wangwei.pojo.CarPark;
import javafx.animation.ParallelTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;

import static experiment2.wangwei.utils.TransitionUtil.*;

/**
 * @author yohoyes
 * @date 2021/3/24 13:56
 */
public class CarParkAnimation {
    private static CarPark park = CarPark.getParkingLot();
    private static int endX = 65;
    private static int carMargin = 7;

    private CarParkAnimation() {
    }

    public static SequentialTransition inPlay(Car car) {

        TranslateTransition translateTransition = translateTransitionX(car, car.getCarX(), endX, 2000);
        SequentialTransition sequentialTransition = new SequentialTransition();
        sequentialTransition.getChildren().addAll(
                translateTransition
        );
        car.setCarX(endX);
        endX += car.getCarWidth() + carMargin;
        park.arrival(car);
        return sequentialTransition;
    }

    public void outPlay(String carNo) {
    }
}
