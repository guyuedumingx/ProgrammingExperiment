package experiment2.wangwei.controller;

import experiment2.wangwei.pojo.Car;
import experiment2.wangwei.pojo.Lane;
import experiment2.wangwei.utils.ColorUtil;
import experiment2.wangwei.utils.WQueue;
import javafx.animation.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Path;

import static experiment2.wangwei.utils.TransitionUtil.*;

/**
 * 控制候车道
 * @author yohoyes
 * @date 2021/3/23 21:33
 */
public class LineAnimation {

    private static LineAnimation animation = new LineAnimation();
    private Lane lane = Lane.getLane();

    private int startX = 10;

    private int endX = 330;

    private int lineY = 0;

    private int transToX = endX;

    private int carWidth = 0;

    private int carHeight = 0;

    private int carMargin = 7;

    private LineAnimation() {}

    public void in(Car car) {
        this.carWidth = car.getCarWidth();
        this.carHeight = car.getCarHeight();
        lane.in(car);
        inPlay(car);
    }

    /**
     * 进场动画
     */
    private void inPlay(Car car) {
        car.getShape().setFill(ColorUtil.ACTIVECOLOR);
        //并行执行动画
        ParallelTransition parallelTransition=new ParallelTransition(
                translateTransitionX(car,startX,transToX,2000), fadeTransition(car, 3000), rotateTransition(car, 360, 3, 1));
        car.setCarX(transToX);
        transToX -= carWidth + carMargin;
        parallelTransition.play();
        car.getShape().setFill(ColorUtil.NORMALCOLOR);
    }

    public static LineAnimation getAnimation() {
        return animation;
    }

    public Car outPlay() {
        Car car = lane.out();
        car.getShape().setFill(ColorUtil.ACTIVECOLOR);
        change(lane);
        int toX = 430;
        TranslateTransition translateTransitionX = translateTransitionX(car, car.getCarX(), toX, 1000);
        car.setCarX(toX);

        int toY = 245;
        TranslateTransition translateTransitionY = translateTransitionY(car, car.getCarY(), toY, 2000);
        car.setCarY(toY);

        toX = car.getCarX() - 50;
        TranslateTransition backTransition = translateTransitionX(car, car.getCarX(), toX, 1000);
        ParallelTransition back=new ParallelTransition(
                backTransition, fadeTransition(car, 1000), rotateTransition(car, 360, 2, 1000));
        car.setCarX(toX);

        SequentialTransition sequentialTransition = new SequentialTransition();
        sequentialTransition.getChildren().addAll(
            translateTransitionX,
            translateTransitionY,
            back,
            CarParkAnimation.inPlay(car)
        );
        sequentialTransition.play();
        car.getShape().setFill(ColorUtil.NORMALCOLOR);
        return car;
    }

    private void change(Lane lane) {
        WQueue<Car> line = lane.getLine();
        WQueue.Itr iterator = line.iterator();
        while (iterator.hasNext()) {
            changeTransition((Car)iterator.next());
        }
        transToX += carWidth + carMargin;
    }

    private void changeTransition(Car car) {
        int toX = car.getCarX() + carMargin + carHeight;
        TranslateTransition translateTransitionX = translateTransitionX(car, car.getCarX(), toX, 1000);
        car.setCarX(toX);
        translateTransitionX.play();
    }
}
