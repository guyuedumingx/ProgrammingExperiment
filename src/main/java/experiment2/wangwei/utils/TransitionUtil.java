package experiment2.wangwei.utils;

import experiment2.wangwei.pojo.WShape;
import javafx.animation.*;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.MoveTo;
import javafx.util.Duration;

/**
 * @author yohoyes
 * @date 2021/3/23 23:55
 */
public class TransitionUtil {

    /**
     * 弧形转移
     * @param shape 操作的车
     * @param fromX 起始位置
     * @param toX 终止位置
     * @param milTime 持续毫秒数
     */
    public static PathTransition pathTransition(WShape shape, double fromX, double fromY, double toX, double toY, double milTime) {
        //创建路径
        javafx.scene.shape.Path path=new javafx.scene.shape.Path();
        path.getElements().add(new MoveTo(fromX, fromY));
        double midX = (fromX+toX) / 2 + 120;
        double midY = (fromY+toY)/2;
        path.getElements().add(new CubicCurveTo(fromX, fromY, midX, midY, toX, toY));
        //创建路径转变
        PathTransition pt=new PathTransition();
        pt.setDuration(Duration.millis(milTime));
        //设置路径
        pt.setPath(path);
        //设置物体
        pt.setNode(shape.getShape());
        pt.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        return pt;
    }

    /**
     * 定义矩形的左右平移效果
     * @param shape
     * @param fromX 起始位置
     * @param toX 终止位置
     * @param milTime 持续时间
     */
    public static TranslateTransition translateTransitionX(WShape shape, double fromX, double toX, double milTime) {
        TranslateTransition translateTransition=new TranslateTransition(Duration.millis(milTime), shape.getShape());
        translateTransition.setFromX(fromX);
        translateTransition.setToX(toX);
        return translateTransition;
    }

    /**
     * 定义矩形的上下平移效果
     * @param shape
     * @param fromY 起始位置
     * @param toY 终止位置
     * @param milTime 持续时间
     */
    public static TranslateTransition translateTransitionY(WShape shape, double fromY, double toY, double milTime) {
        TranslateTransition translateTransition=new TranslateTransition(Duration.millis(milTime), shape.getShape());
        translateTransition.setFromY(fromY);
        translateTransition.setToY(toY);
        return translateTransition;
    }

    /**
     * 定义矩形的淡入淡出效果
     * @param shape 操作的车
     * @param milTime 持续的毫秒数
     */
    public static FadeTransition fadeTransition(WShape shape, double milTime) {
        FadeTransition fadeTransition=new FadeTransition(Duration.millis(milTime), shape.getShape());
        fadeTransition.setFromValue(1.0f);
        fadeTransition.setToValue(0.3f);
        fadeTransition.setCycleCount(2);
        fadeTransition.setAutoReverse(true);

        return fadeTransition;
    }

    /**
     * 旋转的过渡动画
     * @param shape 操作的车
     * @param angle 设置旋转的角度
     * @param count 设置旋转次数
     * @param milTime 持续时间
     */
    public static RotateTransition rotateTransition(WShape shape, int angle, int count, double milTime) {
        RotateTransition rt = new RotateTransition(Duration.millis(milTime), shape.getShape());
        rt.setByAngle(angle);
        rt.setCycleCount(count);
        rt.setInterpolator(Interpolator.LINEAR);
        return rt;
    }
}
