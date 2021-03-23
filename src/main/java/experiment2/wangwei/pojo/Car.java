package experiment2.wangwei.pojo;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

/**
 * 汽车类
 * @author yohoyes
 */
public class Car {
    //车牌号
    private String no;
    //代表车的形状
    private Shape shape;
    //车的宽度
    private int carWidth = 30;
    //车的长度
    private int carHeight = 30;
    //车的X坐标
    private int carX;
    //车的Y坐标
    private int carY;

    public Car(String no) {
        this.no = no;
        Rectangle rectParallel = new Rectangle(10,1, carWidth, carHeight);
        rectParallel.setArcHeight(15);
        rectParallel.setArcWidth(15);
        rectParallel.setFill(Color.DARKBLUE);
        rectParallel.setTranslateX(50);
        rectParallel.setTranslateY(75);
        this.shape = rectParallel;
    }

    public Car(String no, Shape shape) {
        this.shape = shape;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    @Override
    public boolean equals(Object obj) {
        Car car = (Car)obj;
        return this.no.equals(car.no);
    }

    @Override
    public String toString() {
        return "Car{" +
                "no=" + no +
                '}';
    }

    public Shape getShape() {
        return shape;
    }

    public void setShape(Shape shape) {
        this.shape = shape;
    }

    public int getCarWidth() {
        return carWidth;
    }

    public void setCarWidth(int carWidth) {
        this.carWidth = carWidth;
    }

    public int getCarHeight() {
        return carHeight;
    }

    public void setCarHeight(int carHeight) {
        this.carHeight = carHeight;
    }

    public int getCarX() {
        return carX;
    }

    public void setPos(int carX, int carY) {
        setCarX(carX);
        setCarY(carY);
    }

    public void setCarX(int carX) {
        this.carX = carX;
    }

    public int getCarY() {
        return carY;
    }

    public void setCarY(int carY) {
        this.carY = carY;
    }
}
