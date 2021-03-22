package experiment2.yhicxu.carpark;

import experiment2.yhicxu.bean.Car;
import experiment2.yhicxu.utils.LinkQueue;
import experiment2.yhicxu.utils.SeqStack;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * <p><b>类名：</b>{@code CarPark}</p>
 * <p><b>功能：</b></p><br>停车场管理系统
 * <p><b>方法：</b></p>
 * <br> {@link #CarPark(double, int)}构造方法
 * <br> {@link #run()}运行系统
 *
 * @author 60rzvvbj
 * @date 2021/3/20
 */
public class CarPark {

    /**
     * 每分钟价钱
     */
    private final double price;

    /**
     * 停车场容量
     */
    private final int capacity;

    /**
     * 停车场
     */
    private SeqStack<Car> stack;

    /**
     * 便车道
     */
    private LinkQueue<Car> queue;

    /**
     * <p><b>方法名：</b>{@code CarPark}</p>
     * <p><b>功能：</b></p><br>构造方法
     *
     * @param price    每分钟价钱
     * @param capacity 停车场容量
     * @author 60rzvvbj
     * @date 2021/3/20
     */
    public CarPark(double price, int capacity) {

        // 成员变量赋值
        this.price = price;
        this.capacity = capacity;

        // 创建顺序栈和链队列
        stack = new SeqStack<>(capacity);
        queue = new LinkQueue<>();
    }

    /**
     * <p><b>方法名：</b>{@code arrival}</p>
     * <p><b>功能：</b></p><br>将carNo车牌的汽车驶入，如果停车场有车位则进入停车场，设定入场时间，否则在便道等待进入
     *
     * @param carNo 车牌号
     * @author 60rzvvbj
     * @date 2021/3/20
     */
    private void arrival(String carNo) {

        // 先判断停车系统中有没有这个车
        for (Car car : stack) {
            if (car.getNum().equals(carNo)) {
                showTips("该车已经在停车场系统中！");
                return;
            }
        }
        for (Car car : queue) {
            if (car.getNum().equals(carNo)) {
                showTips("该车已经在停车场系统中！");
                return;
            }
        }

        // 如果没有，则按规则入栈/队列
        if (stack.isFull()) {
            queue.offer(new Car(carNo, new Date().getTime()));
        } else {
            stack.push(new Car(carNo, new Date().getTime()));
        }
    }

    /**
     * <p><b>方法名：</b>{@code leave}</p>
     * <p><b>功能：</b></p><br>将carNo车牌的汽车驶离停车场，设定离开时间，同时便道汽车进入停车场，返回null代表要离开的车辆不存在
     *
     * @param carNo 车牌号
     * @return 驶离的车
     * @author 60rzvvbj
     * @date 2021/3/20
     */
    private Car leave(String carNo) {
        Car now = null;
        int size = 0;

        // 先获取这个车的对象和它的应位置
        for (Car car : stack) {
            if (car.getNum().equals(carNo)) {
                now = car;
                break;
            }
            size++;
        }

        // 如果停车场没有这个车则返回null
        if (now == null) {
            return null;
        } else {

            // 如果有则执行驶离操作
            SeqStack<Car> stack2 = new SeqStack<>(size);
            while (true) {
                Car t = stack.pop();
                if (t == now) {

                    // 如果找到了要驶离的车，则将另一个栈中的车再调回停车场栈中
                    while (!stack2.isEmpty()) {
                        stack.push(stack2.pop());
                    }

                    // 如果便车道有车则驶入第一辆到停车场
                    if (!queue.isEmpty()) {
                        stack.push(queue.pool());
                    }
                    break;
                } else {

                    // 如果当期弹出的车不是要驶离的车，则将该车放到另一个栈中
                    stack2.push(t);
                }
            }

            // 设置驶离时间
            now.setLeave(new Date().getTime());
            return now;
        }
    }

    /**
     * <p><b>方法名：</b>{@code charging}</p>
     * <p><b>功能：</b></p><br>计算驶离的车的费用
     *
     * @param car 驶离的车
     * @return 费用
     * @author 60rzvvbj
     * @date 2021/3/20
     */
    private double charging(Car car) {
        return getTime(car) * price;
    }

    /**
     * <p><b>方法名：</b>{@code showPark}</p>
     * <p><b>功能：</b></p><br>显示所有入库车辆信息
     *
     * @author 60rzvvbj
     * @date 2021/3/20
     */
    private void showPark() {

        // 如果停车场没有车则显示提示语
        if (stack.isEmpty()) {
            showTips("The parking lot is empty");
            return;
        }

        // 按照顺序显示车辆
        showTips("Park:");
        int index = stack.size();
        for (Car car : stack) {
            showTips(index + " : " + car.getNum());
            index--;
        }
    }

    /**
     * <p><b>方法名：</b>{@code showWaiting}</p>
     * <p><b>功能：</b></p><br>显示所有在便道上等待信息
     *
     * @author 60rzvvbj
     * @date 2021/3/20
     */
    private void showWaiting() {

        // 如果便车道没有车则不显示
        if (queue.isEmpty()) {
            return;
        }

        // 按顺序显示车辆
        showTips("Waiting:");
        int index = 1;
        for (Car car : queue) {
            showTips(index + " : " + car.getNum());
            index++;
        }
    }

    /**
     * <p><b>方法名：</b>{@code getTime}</p>
     * <p><b>功能：</b></p><br>计算停车时间
     *
     * @author 60rzvvbj
     * @date 2021/3/20
     */
    private int getTime(Car car) {

        // 计算等待时间
        long offset = car.getLeave() - car.getReach();

        // 换算成分钟向上取整
        return (int) Math.ceil((offset + 0.0) / 60000);
    }

    /**
     * <p><b>方法名：</b>{@code showMenu}</p>
     * <p><b>功能：</b></p><br>显示菜单
     *
     * @author 60rzvvbj
     * @date 2021/3/20
     */
    private void showMenu() {
        // System.out.println("\n欢迎使用停车场系统.\t\n");
        showTips("");
        showTips("1. 车辆到达登记.\t");
        showTips("2. 车辆离开登记.\t");
        showTips("3. 显示车辆信息.\t");
        showTips("4. 退出系统.\t");
        showTips("\n请选择：\t");
    }

    /**
     * <p><b>方法名：</b>{@code showTips}</p>
     * <p><b>功能：</b></p><br>显示提示信息
     *
     * @author 60rzvvbj
     * @date 2021/3/20
     */
    private void showTips(String tips) {
        System.out.println(tips);
    }

    /**
     * <p><b>方法名：</b>{@code run}</p>
     * <p><b>功能：</b></p><br>运行系统
     *
     * @author 60rzvvbj
     * @date 2021/3/20
     */
    public void run() {

        // 初始化
        showTips("欢迎使用停车场管理系统!");
        Scanner scanner = new Scanner(System.in);

        // 结束标志
        boolean flag = false;

        while (!flag) {

            // 显示菜单
            showMenu();
            int item = -1;
            while (true) {
                String inp = scanner.nextLine();
                try {
                    item = Integer.parseInt(inp);
                } catch (Exception e) {
                    // 如果输入的不是数字，则重来
                    showTips("\n输入有误，请重新选择：");
                    continue;
                }

                // 如果输入的没问题则跳出循环
                if (item > 0 && item < 5) {
                    break;
                }

                // 输入有问题则不跳出循环，重来
                showTips("\n输入有误，请重新选择：");
            }
            switch (item) {
                case 1:
                    showTips("请输入车牌号：");
                    arrival(scanner.nextLine());
                    break;
                case 2:
                    showTips("请输入车牌号：");

                    // 车牌号
                    String carNo = scanner.nextLine();
                    Car car = leave(carNo);

                    // 如果停车场中没有该车则显示提示信息
                    if (car == null) {
                        showTips("停车场中没有车牌号为" + carNo + "的车");
                        break;
                    }

                    // 计算时间并显示消息
                    long time = getTime(car);
                    showTips("车辆" + carNo + "停车时长" + time + "分钟，共收费" + new DecimalFormat("#.00").format(charging(car)) + "元。");
                    break;
                case 3:

                    // 显示停车场和便车道
                    showPark();
                    showWaiting();
                    break;
                case 4:
                    showTips("谢谢使用！");

                    // 更改结束标志
                    flag = true;
            }
        }
    }
}
