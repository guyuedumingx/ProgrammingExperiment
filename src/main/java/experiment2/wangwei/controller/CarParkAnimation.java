package experiment2.wangwei.controller;

import experiment2.wangwei.pojo.Car;
import experiment2.wangwei.pojo.CarPort;
import experiment2.wangwei.utils.WQueue;
import experiment2.wangwei.utils.WStack;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;

import static experiment2.wangwei.utils.TransitionUtil.*;

/**
 * @author yohoyes
 * @date 2021/3/24 13:56
 */
public class CarParkAnimation {
//    private static WStack<CarPort> parklot = new WStack<>();
//    private static int endX = 65;
//    private static int carMargin = 7;
//    public static final int PARKINGLOT_SIZE = 8;
//    public static final double CHARGE_PER_MILLIS = 3;
//
//    private CarParkAnimation() {
//    }
//
//    public static SequentialTransition inPlay(Car car) {
//
//        TranslateTransition translateTransition = translateTransitionX(car, car.getX(), endX, 2000);
//        SequentialTransition sequentialTransition = new SequentialTransition();
//        sequentialTransition.getChildren().addAll(
//                translateTransition
//        );
//        car.setX(endX);
//        endX += car.getWidth() + carMargin;
//        parklot.push(new CarPort(car));
//        return sequentialTransition;
//    }
//
//    public static SequentialTransition inPlay(CarPort carPort) {
//        Car car = carPort.getCar();
//        TranslateTransition translateTransition = translateTransitionX(car, car.getX(), endX, 2000);
//        SequentialTransition sequentialTransition = new SequentialTransition();
//        sequentialTransition.getChildren().addAll(
//                translateTransition
//        );
//        car.setX(endX);
//        endX += car.getWidth() + carMargin;
//        parklot.push(carPort);
//        return sequentialTransition;
//    }
//
//    public static void outPlay(String no) {
//        CarPort pop = null;
//        WStack<CarPort> tempLot = TempleAnimation.getTempLot();
//        WQueue<Car> lane = LineAnimation.getLane();
//        if(search(no) != -1) {
//            while (!(pop = parklot.pop()).getCar().getNo().equals(no)) {
//                TempleAnimation.inPlay(pop);
//                endX -= pop.getCar().getWidth() + carMargin;
//            }
//            out(pop);
//            while (!tempLot.isEmpty()) {
//                inPlay(TempleAnimation.outPlay());
//            }
//            if(!lane.isEmpty()){
//                while (!isFull()) {
//                    inPlay(LineAnimation.getAnimation().outPlay());
//                }
//            }
//        }else {
//            throw new RuntimeException("没有这个车辆异常");
//        }
//    }
//
//    private static void out(CarPort carPort) {
//        Car car = carPort.getCar();
//        int toX = 630;
//        TranslateTransition translateTransitionX = translateTransitionX(car, car.getX(), toX, 2000);
//        car.setX(toX);
//        translateTransitionX.play();
//    }
//
//    /**
//     * 查看停车场是否有这辆车
//     */
//    public static int search(String no) {
//        return parklot.search(no);
//    }
//
//    public static boolean isEmpty() {
//        return parklot.isEmpty();
//    }
//
//    public static boolean isFull() {
//        return parklot.size() == PARKINGLOT_SIZE;
//    }
}

class TempleAnimation {
//    private static WStack<CarPort> tempLot = new WStack<>();
//    private static int endX = 65;
//    private static int carMargin = 7;
//
//    public static void inPlay(CarPort carPort) {
//        Car car = carPort.getCar();
//        int toX = 430;
//        int toY = 550;
//        TranslateTransition translateTransitionX = translateTransitionX(car, car.getX(), toX, 2000);
//        car.setX(toX);
//        TranslateTransition translateTransitionY = translateTransitionX(car, car.getY(), toY, 2000);
//        car.setY(toY);
//        TranslateTransition back = translateTransitionX(car, car.getX(), endX, 2000);
//        car.setX(endX);
//        endX += car.getWidth() + carMargin;
//        SequentialTransition sequentialTransition = new SequentialTransition();
//        sequentialTransition.getChildren().addAll(
//                translateTransitionX,
//                translateTransitionY,
//                back
//        );
//        sequentialTransition.play();
//    }
//
//    public static CarPort outPlay() {
//        CarPort carPort = tempLot.pop();
//        Car car = carPort.getCar();
//        double toX = 430;
//        int toY = 245;
//        TranslateTransition translateTransitionX = translateTransitionX(car, car.getX(), toX, 2000);
//        car.setX(toX);
//        TranslateTransition translateTransitionY = translateTransitionX(car, car.getY(), toY, 2000);
//        car.setY(toY);
//        toX = car.getX() - 50;
//        TranslateTransition back = translateTransitionX(car, car.getX(), toX, 2000);
//        car.setX(toX);
//        SequentialTransition sequentialTransition = new SequentialTransition();
//        sequentialTransition.getChildren().addAll(
//                translateTransitionX,
//                translateTransitionY,
//                back,
//                CarParkAnimation.inPlay(carPort)
//        );
//        sequentialTransition.play();
//        return carPort;
//    }
//
//    public static WStack<CarPort> getTempLot() {
//        return tempLot;
//    }
}
