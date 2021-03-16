package experiment2.yhicxu.carpark;

import experiment2.yhicxu.bean.Car;
import experiment2.yhicxu.utils.LinkQueue;
import experiment2.yhicxu.utils.SeqStack;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.Scanner;

public class CarPark {
    private final double price;
    private final int capacity;
    private SeqStack<Car> stack;
    private LinkQueue<Car> queue;

    public CarPark(double price, int capacity) {
        this.price = price;
        this.capacity = capacity;
        stack = new SeqStack<>(capacity);
        queue = new LinkQueue<>();
    }

    /**
     * 功能： 将carNo车牌的汽车驶入，如果停车场有车位则进入停车场，设定入场时间，否则在便道等待进入
     * 参数：
     * carNo -- 车牌信息
     * 返回值：
     */
    private void arrival(String carNo) {
        if (stack.isFull()) {
            queue.offer(new Car(carNo, new Date().getTime()));
        } else {
            stack.push(new Car(carNo, new Date().getTime()));
        }
    }

    /**
     * 功能： 将carNo车牌的汽车驶离停车场，设定离开时间，同时便道汽车进入停车场
     * 参数：
     * carNo -- 车牌信息
     * 返回值：离开汽车
     */
    public Car leave(String carNo) {
        Car now = null;
        for (Car car : stack) {
            if (car.getNum().equals(carNo)) {
                now = car;
                break;
            }
        }
        if (now == null) {
            return null;
        } else {
            SeqStack<Car> stack2 = new SeqStack<>(capacity);
            while (!stack.isEmpty()) {
                Car t = stack.pop();
                if (t == now) {
                    while (!stack2.isEmpty()) {
                        stack.push(stack2.pop());
                    }
                    if (!queue.isEmpty()) {
                        stack.push(queue.pool());
                    }
                    break;
                } else {
                    stack2.push(t);
                }
            }
            now.setLeave(new Date().getTime());
            return now;
        }
    }


    /**
     * 功能： 根据车辆的出入时间，计算费用及停车时长
     * 参数：
     * car -- 车辆信息
     * 返回值：停车费用
     */
    public double charging(Car car) {
        return getTime(car) * price;
    }

    // 显示所有入库车辆信息
    public void showPark() {
        if (stack.isEmpty()) {
            System.out.println("The parking lot is empty");
        }
        System.out.println("Park:");
        int index = 1;
        for (Car car : stack) {
            System.out.println(index + " : " + car.getNum());
            index++;
        }
    }

    // 显示所有在便道上等待信息
    public void showWaiting() {
        if (queue.isEmpty()) {
            return;
        }
        System.out.println("Waiting:");
        int index = 1;
        for (Car car : queue) {
            System.out.println(index + " : " + car.getNum());
            index++;
        }
    }

    // 计算停车时间
    private int getTime(Car car) {
        long offset = car.getLeave() - car.getReach();
        return (int) Math.ceil((offset + 0.0) / 60000);
    }

    // 显示菜单
    private void showMenu() {
        System.out.println("\n欢迎使用停车场系统.\t\n");
        System.out.println("1. 车辆到达登记.\t");
        System.out.println("2. 车辆离开登记.\t");
        System.out.println("3. 显示车辆信息.\t");
        System.out.println("4. 退出系统.\t");
        System.out.println("\n请选择：\t");
    }

    // 运行
    public void run() {
        CarPark carPark = new CarPark(3.2, 3);
        Scanner scanner = new Scanner(System.in);
        String carNo;
        while (true) {
            showMenu();
            int item = -1;
            while (true) {
                item = scanner.nextInt();
                if (item > 0 && item < 5)
                    break;
                System.out.println("\n 输入有误，请重新选择： 1~4: ");
            }
            switch (item) {
                case 1:
                    System.out.println("请输入车牌号：");
                    carNo = scanner.next();
                    carPark.arrival(carNo);
                    break;
                case 2:
                    System.out.println("请输入车牌号：");
                    carNo = scanner.next();
                    Car car = carPark.leave(carNo);
                    if (car == null) {
                        System.out.println("停车场中没有车牌号为" + carNo + "的车");
                        break;
                    }
                    long time = getTime(car);
                    DecimalFormat df = new DecimalFormat("#.00");
                    String fee = df.format(carPark.charging(car));
                    System.out.println("车辆" + carNo + "停车时长" + time + "分钟，共收费" + fee + "元。");
                    break;
                case 3:
                    carPark.showPark();
                    carPark.showWaiting();
                    break;
                case 4:
                    System.exit(0);
            }
        }
    }
}
