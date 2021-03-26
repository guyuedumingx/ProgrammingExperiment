package experiment2.wangwei.pojo;

import experiment2.wangwei.utils.TransitionUtil;
import experiment2.wangwei.utils.WQueue;
import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.shape.Path;
import javafx.scene.shape.Shape;
import javafx.util.Duration;

/**
 * 是所有物体的公有实现类，实现了WShape接口，完成了部分基本实现
 * @author yohoyes
 * @date 2021/3/24 21:18
 */
public class BaseShape implements WShape{
    /**
     * 物体形状
     */
    Shape shape;
    /**
     * 物体宽度
     */
    int width;
    /**
     * 物体高度
     */
    int height;
    /**
     * 物体x坐标
     */
    double x;
    /**
     * 物体y坐标
     */
    double y;
    /**
     * 物体动画的延迟
     */
    int delay;
    /**
     * 判断物体是否在移动
     */
    boolean isPlaying = false;
    /**
     * 物体要执行的动画队列
     */
    WQueue<Package> packageWQueue = new WQueue<>();
    /**
     * 动画执行完成的回调事件
     */
    EventHandler<ActionEvent> value;

    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public void setWidth(int width) {
        this.width = width;
        shape.prefWidth(width);
    }

    @Override
    public void setHeight(int height) {
        this.height = height;
        shape.prefHeight(height);
    }
    @Override
    public void moveTo(double x, double y, double mills, double delay) {
        if(isPlaying){
            packageWQueue.add(new Package(x, y));
            return;
        }
        isPlaying = true;
        Transition move = null;
        if (this.x != x && this.y == y) {
            move = TransitionUtil.translateTransitionX(this, this.x, x, mills);
        } else if (this.x == x && y != y) {
            move = TransitionUtil.translateTransitionY(this, this.y, y, mills);
        } else {
            TranslateTransition moveX = TransitionUtil.translateTransitionX(this, this.x, x, mills/2);
            TranslateTransition moveY = TransitionUtil.translateTransitionY(this, this.y, y, mills/2);
            SequentialTransition transition = new SequentialTransition();
            transition.getChildren().addAll(moveY, moveX);
            move = transition;
        }
        if(delay > 0) {
            PauseTransition pause = new PauseTransition(Duration.millis(delay));
            SequentialTransition sequential = new SequentialTransition();
            sequential.getChildren().addAll(pause,move);
            move = sequential;
        }
        move.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                isPlaying = false;
                if(!packageWQueue.isEmpty()) {
                    next();
                }else {
                    if(value != null){
                        value.handle(new ActionEvent());
                    }
                }
            }
        });
        move.play();
        this.x = x;
        this.y = y;
    }

    @Override
    public void removeFinishedAction() {
        setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            }
        });
    }

    @Override
    public void setOnFinished(EventHandler<ActionEvent> value) {
        this.value = value;
    }

    private void inPlayQueue(Animation animation) {
        animation.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                isPlaying = false;
                if(!packageWQueue.isEmpty()) {
                    next();
                }else {
                    if(value != null){
                        value.handle(new ActionEvent());
                    }
                }
            }
        });
        packageWQueue.add(new Package(animation));
        if(!isPlaying) {
            isPlaying = true;
            animation.play();
        }
    }

    /**
     * 播放队列中的下一个动画
     */
    private void next() {
        Package poll = packageWQueue.poll();
        if(poll.isMove()) {
            moveTo(poll.getX(),poll.getY());
        }else {
            isPlaying = true;
            Animation animation = poll.getAnimation();
            animation.play();
        }
    }

    @Override
    public void moveTo(double x, double y, double mills) {
        moveTo(x, y, mills, 0);
    }

    @Override
    public void moveTo(double x, double y) {
        moveTo(x, y, DEFAULT_TIME);
    }

    @Override
    public void pause(double mills) {
        PauseTransition transition = new PauseTransition(Duration.millis(mills));
        inPlayQueue(transition);
    }

    @Override
    public void scaling(int mul) {

    }

    @Override
    public void rotate(int angle) {

    }

    @Override
    public void moveByPath(Path path) {

    }

    @Override
    public void setShape(Shape shape) {
        this.shape = shape;
    }

    @Override
    public Shape getShape() {
        return shape;
    }
}

class Package {
    private double x;
    private double y;
    private Animation animation;
    private boolean isMove = false;

    public Package(double x, double y) {
        this.x = x;
        this.y = y;
        isMove = true;
    }

    public Package(Animation animation) {
        this.animation = animation;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public boolean isMove() {
        return isMove;
    }

    public Animation getAnimation() {
        return animation;
    }
}