package experiment2.wangwei.pojo;

import experiment2.wangwei.utils.WStack;

import java.util.Iterator;

/**
 * 停车场类
 * @author yohoyes
 */
public class Park {
    private WStack<CarPort> lot = new WStack<>();
    public static final int PARKINGLOT_SIZE = 8;
    public static final double CHARGE_PER_MILLIS = 3;

    private double lotStartX;
    private double lotEndX;
    private double lotY;
    private double nextShapeX;
    private double shapeWidth = 0;
    private double shapeMargin = 7;


    public Park(double lotStartX, double lotEndX, double lotY) {
        this.lotStartX = lotStartX;
        this.lotEndX = lotEndX;
        this.nextShapeX = lotStartX;
        this.lotY = lotY;
    }

    public void in(Car car, double delay) {
        in(new CarPort(car), delay);
    }

    public void in(Car car) {
        in(new CarPort(car),0);
    }

    public void in(CarPort carPort) {
        in(carPort,0);
    }

    public void in(CarPort carPort, double delay) {
        Car car = carPort.getCar();
        shapeWidth = car.getWidth();
        lot.push(carPort);
        car.moveTo(lotEndX+50, lotY, WShape.DEFAULT_TIME,delay);
        car.moveTo(nextShapeX, lotY);
        car.pause(1000);
        nextShapeX += shapeWidth + shapeMargin;
    }

    public CarPort out(){
        return out(0);
    }

    public CarPort out(double delay) {
        CarPort carPort = lot.pop();
        Car car = carPort.getCar();
        car.moveTo(lotEndX+100, lotY, delay == 0 ? WShape.DEFAULT_TIME : WShape.DEFAULT_TIME + delay, delay);
        addOutAction();
        nextShapeX -= shapeWidth + shapeMargin;
        return carPort;
    }

    private void addOutAction() {
        WStack<CarPort> temp = new WStack<>();
        while (!lot.isEmpty()){
            CarPort pop = lot.pop();
            Car peekCar = pop.getCar();
            peekCar.pause(1000);
            System.out.println(peekCar.getNo() + " Pause 1000");
            temp.push(pop);
        }
        while (!temp.isEmpty()) {
            lot.push(temp.pop());
        }
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

    public Iterator<CarPort> iterator() {
        return lot.iterator();
    }
}

