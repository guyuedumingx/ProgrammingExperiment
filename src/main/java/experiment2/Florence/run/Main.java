package experiment2.Florence.run;

import experiment2.Florence.pojo.Car;
import experiment2.Florence.pojo.CarPark;
import experiment2.Florence.util.Md5Until;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import static experiment2.Florence.util.Md5Until.getRandom;

/**
 * @author Florence
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        List<String> tokens = new ArrayList<>();
        CarPark carPark = new CarPark(tokens);
        Scanner scanner = new Scanner(System.in);
        String carNo;
        Random random = new Random(System.currentTimeMillis());
        while(true) {
            menu();
            int item = -1;
            while (true) {
                item = getRandom(1,3);
                if(item>0 && item <5) {
                    System.out.print(item);
                    break;
                }
                System.out.println("\n 输入有误，请重新选择： 1~4: ");
            }
            switch(item) {
                case 1:
                    carNo = Md5Until.getMd5(String.valueOf(System.currentTimeMillis()));
                    System.out.println("请输入车牌号："+carNo);
                    //true 是不在里面
                    boolean isIn = carPark.arrival(carNo);
                    if (isIn){
                        tokens.add(carNo);
                    }
                    break;
                case 2:
                    if (tokens.size()==0){
                        System.out.println("停车场内无车");
                        break;
                    }
                    int indexRandom = getRandom(0,tokens.size());
                    carNo = tokens.get(indexRandom);
                    System.out.println("请输入车牌号："+carNo);
                    tokens.remove(carNo);
                    Car car = carPark.leave(carNo);
                    long time = car.getPassTime();
                    DecimalFormat df = new DecimalFormat("#.00");
                    String fee = df.format(carPark.charging(car));
                    System.out.println("车辆"+carNo+"停车时长"+time+"分钟，共收费"+fee+"元。");
                    break;
                case 3:
                    carPark.showPark();
                    carPark.showWaiting();
                    break;
                case 4:
                    System.exit(0);
            }
            carPark.showPark();
            carPark.showWaiting();
            Thread.sleep(getRandom(1000,2000));
        }

    }
    public static void menu() {
        System.out.println("\n    §※§※§※§※§※§ 欢迎使用停车场系统.§※§※§※§※§※§\t\n");
        System.out.println("\t※◎※◎※◎※◎  1. 车辆到达登记.※◎※◎※◎※◎\t");
        System.out.println("\t※◎※◎※◎※◎  2. 车辆离开登记.※◎※◎※◎※◎\t");
        System.out.println("\t※◎※◎※◎※◎  3. 显示车辆信息.※◎※◎※◎※◎\t");
        System.out.println("\t※◎※◎※◎※◎  4. 退出系统.※◎※◎※◎※◎\t");
        System.out.println("\n\t请选择：\t");
    }

}
