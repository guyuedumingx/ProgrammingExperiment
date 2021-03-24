package experiment2.wangwei.pojo;

import experiment2.wangwei.utils.WQueue;
import java.util.Iterator;


/**
 * 便车道
 * @author yohoyes
 */
public class Lane {
    private WQueue<Car> line = new WQueue<>();
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

    public int size() {
        return line.size();
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

    public WQueue<Car> getLine() {
        return line;
    }

    public static Lane getLane() {
        return lane;
    }
}
