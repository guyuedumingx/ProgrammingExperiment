package experiment2.wangwei.pojo;

import javafx.scene.shape.Path;
import javafx.scene.shape.Shape;

/**
 * @author yohoyes
 * @date 2021/3/24 21:01
 */
public interface WShape {
    public static final int DEFAULT_TIME = 1000;

    double getX();

    double getY();

    int getWidth();

    int getHeight();

    void setWidth(int width);

    void setHeight(int height);

    void moveTo(double x, double y);

    void moveTo(double x, double y, double mills);

    void moveTo(double x, double y, double mills, double delay);

    void scaling(int mul);

    void rotate(int angle);

    void pause(double mills);

    void moveByPath(Path path);

    void setShape(Shape shape);

    Shape getShape();
}
