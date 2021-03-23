package experiment2.yhicxu.carpark;

// 运行CarPark的测试类
public class TestCarPark {
    public static void main(String[] args) {

        // 每分钟的价钱3.2和最大容量3是随便设置的
        double price = 3.2;
        int capacity = 3;

        // 控制运行台
        // new CarPark(price, capacity).run();

        // 图形化界面运行
        CarPark.run(price, capacity);

    }

}
