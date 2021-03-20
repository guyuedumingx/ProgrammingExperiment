package experiment2.yhicxu.carpark;

// 运行CarPark的测试类
public class TestCarPark {
    public static void main(String[] args) {

        // 每分钟的价钱3.2和最大容量3是随便设置的
        new CarPark(3.2, 3).run();
    }
}
