package experiment2.wangwei.pojo;

import experiment2.wangwei.utils.WQueue;
import javafx.scene.shape.Shape;

import java.util.concurrent.TimeUnit;

/**
 * 便车道
 * @author yohoyes
 */
public class Lane {
    private WQueue<Car> line = new WQueue<>();
    private static Lane lane = new Lane();

    private int laneStartX = 10;
    private int laneEndX = 330;
    private int laneY = 75;
    private int nextShapeX = laneEndX;
    private int shapeWidth = 0;
    private int shapeMargin = 7;

    private Lane() {
    }

    public void in(Car car) {
        line.add(car);
        car.moveTo(nextShapeX, laneY);
        shapeWidth = car.getWidth();
        nextShapeX -= shapeWidth + shapeMargin;
    }

    public Car out() {
        if(!line.isEmpty()) {
            Car car = line.poll();
            car.moveTo(laneEndX + 100, laneY);
            change();
            return car;
        }
        return null;
    }

    private void change() {
        WQueue.Itr iterator = line.iterator();
        while (iterator.hasNext()) {
            Car next = (Car) iterator.next();
            shapeWidth = next.getWidth();
            changeTransition(next);
        }
        nextShapeX += shapeWidth  + shapeMargin;
    }

    private void changeTransition(Car car) {
        double toX = car.getX() + shapeMargin + car.getHeight();
        car.moveTo(toX,laneY);
    }

    public boolean isEmpty() {
        return line.isEmpty();
    }

    public int size() {
        return line.size();
    }

    public static Lane getLane() {
        return lane;
    }
}
