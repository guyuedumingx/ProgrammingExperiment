package experiment2.wangwei.pojo;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 便车道
 * @author yohoyes
 */
public class Lane {
    private Queue<Car> line = new ArrayDeque<>();

    public Lane() {

    }

    public void in(Car car) {
        line.add(car);
    }

    public Car out() {
        return line.peek();
    }

    public boolean isEmpty() {
        return line.isEmpty();
    }
}
