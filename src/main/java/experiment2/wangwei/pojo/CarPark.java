package experiment2.wangwei.pojo;

import experiment2.wangwei.utils.WStack;
import java.text.DecimalFormat;
import java.util.Iterator;
/**
 * 停车场类
 * @author yohoyes
 */
public class CarPark {
    private WStack<CarPort> lot = new WStack<>();
    private WStack<CarPort> tempLot = new WStack<>();
    public static final int PARKINGLOT_SIZE = 8;
    public static final double CHARGE_PER_MILLIS = 3;
    private static CarPark carPark = new CarPark();
    private static Lane lane = Lane.getLane();

    private CarPark() {
    }

    public void arrival(Car car) {
        lot.push(new CarPort(car, lot.size()));
    }

    public Car leave(String no) {
        CarPort pop = null;
        if(search(no) != -1) {
            while (!(pop = lot.pop()).getCar().getNo().equals(no)) {
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
    public int search(String no) {
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

