package experiment2.wangwei.pojo;

import experiment2.wangwei.utils.WStack;
import java.text.DecimalFormat;
import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.Queue;
/**
 * 停车场类
 * @author yohoyes
 */
public class CarPark {
    private WStack<CarPort> lot = new WStack<>();
    private WStack<CarPort> tempLot = new WStack<>();
    public static final int PARKINGLOT_SIZE = 3;
    public static final double CHARGE_PER_MILLIS = 3;
    private static CarPark carPark = new CarPark();
    private static Lane lane = Lane.getLane();

    private CarPark() {
    }

    public void arrival(Car car) {
        if(!isFull()) {
            lot.push(new CarPort(car, lot.size()));
        }else {
            lane.in(car);
        }
    }

    public Car leave(int no) {
        CarPort pop = null;
        if(search(no) != -1) {
            while ((pop = lot.pop()).getCar().getNo() != no) {
                tempLot.push(pop);
            }
            while (!tempLot.isEmpty()) {
                lot.push(tempLot.pop());
            }
            if(!lane.isEmpty()){
                while (!isFull()) {
                    lot.push(new CarPort(lane.out(), lot.size()));
                }
            }
            System.out.println(charging(pop));
            return pop.getCar();
        }
        throw new RuntimeException("没有这个车辆异常");
    }

    /**
     * 根据时间收费
     * @return 收费信息
     */
    public String charging(CarPort port) {
        double time = System.currentTimeMillis() - port.getParkTime();
//        time = time * Math.pow(10,-6) / 3600 ;
        double charge = time / 60000 * CHARGE_PER_MILLIS;
        DecimalFormat df = new DecimalFormat("#.00");
        String fee = df.format(charge);
        String msg = "停车时长为： " + time + " 小时 \n 收费为：" + fee;
        return msg;
    }

    /**
     * 查看停车场是否有这辆车
     */
    public int search(int no) {
        System.out.println(no);
        return lot.search(no);
    }

    public boolean isEmpty() {
        return lot.isEmpty();
    }

    public boolean isFull() {
        return lot.size() == PARKINGLOT_SIZE;
    }

    public void showPark() {
        Iterator<CarPort> iterator = lot.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next().toString());
        }
    }

    public void showWaiting() {
        lane.showWaiting();
    }

    public static CarPark getParkingLot() {
        return carPark;
    }
}

/**
 * 车位
 * 像车位，但不是车位，像是把车包起来的包裹
 * @author yohoyes
 */
class CarPort {
    private Car car;
    //车编号
    private int no;
    //停车场给这部车的编号
    private int serialNumber;
    //停车时间
    private long parkTime;

    public CarPort(Car car, int serialNumber) {
        this.car = car;
        this.no = car.getNo();
        this.serialNumber = serialNumber;
        this.parkTime = System.currentTimeMillis();
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public int getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    public long getParkTime() {
        return parkTime;
    }

    public void setParkTime(long parkTime) {
        this.parkTime = parkTime;
    }

    @Override
    public boolean equals(Object obj) {
        int no = (Integer) obj;
        return this.no == no;
    }

    @Override
    public String toString() {
        return "Parking {\n"
                + car.toString() +
                "\n serialNumber=" + serialNumber +
                "\n parkTime=" + parkTime +
                "\n}";
    }
}

/**
 * 便车道
 * @author yohoyes
 */
class Lane {
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
