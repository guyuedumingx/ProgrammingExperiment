package experiment2.wangwei.controller;

import experiment2.wangwei.pojo.Car;
import experiment2.wangwei.utils.ColorUtil;
import experiment2.wangwei.utils.WQueue;
import javafx.animation.*;
import static experiment2.wangwei.utils.TransitionUtil.*;

/**
 * 控制候车道
 * @author yohoyes
 * @date 2021/3/23 21:33
 */
public class LineAnimation {
//
//    private static LineAnimation animation = new LineAnimation();
//    private static WQueue<Car> lane = new WQueue<>();
//
//    private int startX = 10;
//
//    private int endX = 330;
//
//    private int lineY = 0;
//
//    private int transToX = endX;
//
//    private int carWidth = 0;
//
//    private int carHeight = 0;
//
//    private int carMargin = 7;
//
//    private LineAnimation() {}
//
//    public void in(Car car) {
//        this.carWidth = car.getWidth();
//        this.carHeight = car.getHeight();
//        lane.add(car);
//        inPlay(car);
//    }
//
//    /**
//     * 进场动画
//     */
//    private void inPlay(Car car) {
//        car.getShape().setFill(ColorUtil.ACTIVECOLOR);
//        //并行执行动画
//        ParallelTransition parallelTransition=new ParallelTransition(
//                translateTransitionX(car,startX,transToX,2000), fadeTransition(car, 3000), rotateTransition(car, 360, 3, 1000));
//        car.setX(transToX);
//        transToX -= carWidth + carMargin;
//        car.getShape().setFill(ColorUtil.NORMALCOLOR);
//        if(!CarParkAnimation.isFull()) {
//            outPlay(parallelTransition);
//        }else {
//            parallelTransition.play();
//        }
//    }
//
//    public static LineAnimation getAnimation() {
//        return animation;
//    }
//
//    public void outPlay(Transition transition) {
//        Car car = lane.poll();
//        car.getShape().setFill(ColorUtil.ACTIVECOLOR);
//        double toX = 430;
//        TranslateTransition translateTransitionX = translateTransitionX(car, car.getX(), toX, 1000);
//        car.setX(toX);
//
//        int toY = 245;
//        TranslateTransition translateTransitionY = translateTransitionY(car, car.getY(), toY, 2000);
//        car.setY(toY);
//
//        toX = car.getX() - 50;
//        TranslateTransition backTransition = translateTransitionX(car, car.getX(), toX, 1000);
//        ParallelTransition back=new ParallelTransition(
//                backTransition, fadeTransition(car, 1000), rotateTransition(car, 360, 2, 1000));
//        car.setX(toX);
//
//        SequentialTransition sequentialTransition = new SequentialTransition();
//        sequentialTransition.getChildren().addAll(
//                transition,
//                translateTransitionX,
//                translateTransitionY,
//                back,
//                CarParkAnimation.inPlay(car)
//        );
//        sequentialTransition.play();
//        car.getShape().setFill(ColorUtil.NORMALCOLOR);
//        change();
//    }
//
//    public Car outPlay() {
//        Car car = lane.poll();
//        car.getShape().setFill(ColorUtil.ACTIVECOLOR);
//        double toX = 430;
//        TranslateTransition translateTransitionX = translateTransitionX(car, car.getX(), toX, 1000);
//        car.setX(toX);
//
//        int toY = 245;
//        TranslateTransition translateTransitionY = translateTransitionY(car, car.getY(), toY, 2000);
//        car.setY(toY);
//
//        toX = car.getX() - 50;
//        TranslateTransition backTransition = translateTransitionX(car, car.getX(), toX, 1000);
//        ParallelTransition back=new ParallelTransition(
//                backTransition, fadeTransition(car, 1000), rotateTransition(car, 360, 2, 1000));
//        car.setX(toX);
//
//        SequentialTransition sequentialTransition = new SequentialTransition();
//        sequentialTransition.getChildren().addAll(
//            translateTransitionX,
//            translateTransitionY,
//            back,
//            CarParkAnimation.inPlay(car)
//        );
//        sequentialTransition.play();
//        car.getShape().setFill(ColorUtil.NORMALCOLOR);
//        change();
//        return car;
//    }
//
//    private void change() {
//        WQueue.Itr iterator = lane.iterator();
//        while (iterator.hasNext()) {
//            changeTransition((Car)iterator.next());
//        }
//        transToX += carWidth + carMargin;
//    }
//
//    private void changeTransition(Car car) {
//        double toX = car.getX() + carMargin + carHeight;
//        TranslateTransition translateTransitionX = translateTransitionX(car, car.getX(), toX, 1000);
//        car.setX(toX);
//        translateTransitionX.play();
//    }
//
//    public static WQueue<Car> getLane() {
//        return lane;
//    }
}

