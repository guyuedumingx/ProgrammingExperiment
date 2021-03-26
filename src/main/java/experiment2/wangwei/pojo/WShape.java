package experiment2.wangwei.pojo;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.shape.Path;
import javafx.scene.shape.Shape;

/**
 * 这玩意定义了一个物体的属性和动作，是所有可运动物体的爸爸接口
 * @author yohoyes
 * @date 2021/3/24 21:01
 */
public interface WShape {
    public static final int DEFAULT_TIME = 2000;

    double getX();

    double getY();

    int getWidth();

    int getHeight();

    void setWidth(int width);

    void setHeight(int height);

    /**
     * 把物体移动道x ,y 位置
     */
    void moveTo(double x, double y);

    /**
     * 把物体移动道x ,y 位置, 整个动画时长为 mills毫秒
     */
    void moveTo(double x, double y, double mills);

    /**
     * 把物体移动道x ,y 位置, 整个动画时长为 mills毫秒, delay 毫秒后开始
     */
    void moveTo(double x, double y, double mills, double delay);

    /**
     * 该物体执行完所有动画后的回调函数
     */
    void setOnFinished(EventHandler<ActionEvent> value);

    /**
     * 移除物体执行完动画之后的回调函数
     */
    void removeFinishedAction();

    /**
     * 把物体放大或缩小
     * @param mul 倍数
     */
    void scaling(int mul);

    /**
     * 物体旋转
     * @param angle 角度
     */
    void rotate(int angle);

    /**
     * 物体停止mills 毫秒
     */
    void pause(double mills);

    /**
     * 根据传入的path来移动物体
     */
    void moveByPath(Path path);

    /**
     * 设置物体形状
     */
    void setShape(Shape shape);

    /**
     * 获取物体形状
     */
    Shape getShape();
}
