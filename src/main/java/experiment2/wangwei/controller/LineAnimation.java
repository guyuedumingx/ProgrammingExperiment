package experiment2.wangwei.controller;

import experiment2.wangwei.pojo.Car;
import javafx.animation.*;
import javafx.scene.shape.Shape;

import static experiment2.wangwei.utils.TransitionUtil.*;

/**
 * 控制候车道
 * @author yohoyes
 * @date 2021/3/23 21:33
 */
public class LineAnimation {

    private static LineAnimation animation = new LineAnimation();

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
        inPlay(car);
    }

    /**
     * 进场动画
     */
    private void inPlay(Car car) {
        //并行执行动画
        ParallelTransition parallelTransition=new ParallelTransition(
                translateTransition(car,startX,transToX,2000), fadeTransition(car, 3000), rotateTransition(car, 360, 3, 1));
        transToX -= carWidth + carMargin;
        parallelTransition.play();
    }

    public static LineAnimation getAnimation() {
        return animation;
    }

    public void outPlay(Car car) {
        TranslateTransition translateTransition = translateTransition(car, car.getCarX(), 400, 1000);
        translateTransition.play();

        //并行执行动画
        ParallelTransition parallelTransition=new ParallelTransition(
                fadeTransition(car,1000), rotateTransition(car,200,3,2),pathTransition(car,400,100,410,245,4000));
        parallelTransition.play();
    }
}
