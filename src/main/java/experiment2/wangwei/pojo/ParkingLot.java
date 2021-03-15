package experiment2.wangwei.pojo;

import java.util.Stack;

/**
 * 停车场类
 * @author yohoyes
 */
public class ParkingLot {
    private Stack<CarPort> lot = new Stack<>();
    private Stack<CarPort> tempLot = new Stack<>();
    public static final int PARKINGLOT_SIZE = 10;
    public static final double CHARGE_PER_MILLIS = 3;

    public ParkingLot() {
    }

    public void in(Car car) {
        lot.push(new CarPort(car, lot.size()));
    }

    public Car out() {
        CarPort pop = lot.pop();
        charge(pop);
        return pop.getCar();
    }

    /**
     * 根据时间收费
     */
    public double charge(CarPort port) {
        double time = System.currentTimeMillis() - port.getParkTime();
//        time = time * Math.pow(10,-6) / 3600 ;
        double charge = time * CHARGE_PER_MILLIS;
        System.out.println("停车时长为： " + time + " 毫秒");
        System.out.println("收费为： " + charge);
        return charge;
    }

    public Car outByNo(int no) {
        CarPort pop = null;
        if(search(no) != -1) {
            while ((pop = lot.pop()).getCar().getNo() != no) {
                tempLot.push(pop);
            }
            while (!tempLot.isEmpty()) {
                lot.push(tempLot.pop());
            }
        }
        throw new RuntimeException("没有这个车辆异常");
    }

    /**
     * 查看停车场是否有这辆车
     */
    public int search(int no) {
        return lot.search(no);
    }

    public boolean isEmpty() {
        return lot.isEmpty();
    }

    public boolean isFull() {
        return lot.size() == PARKINGLOT_SIZE;
    }
}
