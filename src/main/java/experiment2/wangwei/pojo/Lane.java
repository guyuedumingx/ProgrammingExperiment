package experiment2.wangwei.pojo;

import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.Queue;


/**
 * 便车道
 * @author yohoyes
 */
public class Lane {
    private Queue<Car> line = new ArrayDeque<>();
    private static Lane lane = new Lane();

    private Lane() {

    }

    public void in(Car car) {
        line.add(car);
    }

    public Car out() {
        return line.poll();
    }

    public boolean isEmpty() {
        return line.isEmpty();
    }

    /**
     * 显示在便车道上所有车辆的信息
     */
    public void showWaiting() {
        Iterator<Car> iterator = line.iterator();
        System.out.println("Waiting: ");
        while (iterator.hasNext()) {
            System.out.println(iterator.next().toString());
        }
    }

    public static Lane getLane() {
        return lane;
    }
}
