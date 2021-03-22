package experiment2.dqy.Model;

import experiment2.Florence.util.Node;
import experiment2.dqy.Util.Queue;
import experiment2.dqy.Util.Stack;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

public class ParkingLot {
    private Queue waitingLine = new Queue();
    private Stack parkingRoom = new Stack();
    private Stack tmpRoom = new Stack();
    private int capacityOfParkingLot = 2;
    private int price = 200;

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getCapacityOfParkingLot() {
        return capacityOfParkingLot;
    }

    public void setCapacityOfParkingLot(int capacityOfParkingLot) {
        this.capacityOfParkingLot = capacityOfParkingLot;
    }

    //判断停车场内是否存在某辆车
    public boolean isInParkingLot(Car car) {
        Iterator<Car> iterator = parkingRoom.iterator();
        while (iterator.hasNext()) {
            Car cur = iterator.next();
            if(cur.equals(car)) return true;
        }
        return false;
    }

    //判断候车场内是否存在某辆车
    public boolean isInWaitingLine(Car car) {
        Iterator<Car> iterator = waitingLine.iterator();
        while (iterator.hasNext()) {
            Car cur = iterator.next();
            if(cur.equals(car)) return true;
        }
        return false;
    }

    //把车停入停车场
    public boolean enterCar(Car car) {
        if (isInParkingLot(car) || isInWaitingLine(car)) return false;
        int numberOfCars = parkingRoom.size();
        if (numberOfCars == capacityOfParkingLot) {
            waitingLine.push(car);
        } else {
            parkingRoom.push(car);
            car.setEnterTime(new Date().getTime());
        }
        return true;
    }

    //结算价钱
    private int getMoney(Car car) {
        Long leaveTime = new Date().getTime();
        int sumTime = (int)(car.getEnterTime() - leaveTime) / 60000;
        return sumTime * price;
    }

    //让车离开停车场,同时让候车场的车进入
    public int leaveCar(Car car) {
        //表示当前停车场没有这车
        if(!isInParkingLot(car)) return -1;
        Iterator<Car> iterator = parkingRoom.iterator();
        Car cur = iterator.next();
        while (!cur.equals(car)) {
            tmpRoom.push(parkingRoom.getTop());
            parkingRoom.pop();
            cur = iterator.next();
        }
        int money = getMoney(cur);
        parkingRoom.pop();
        iterator = tmpRoom.iterator();
        while (iterator.hasNext()) {
            parkingRoom.push(tmpRoom.getTop());
            tmpRoom.pop();
        }
        if (waitingLine.size() > 0) {
            Car newCar = (Car) waitingLine.getFront();
            newCar.setEnterTime(new Date().getTime());
            parkingRoom.push(newCar);
            waitingLine.pop();
        }
        return money;
    }

    //显示当前停车场中的所有车辆信息
    public void printParkingRoom() {
        Iterator<Car> iterator = parkingRoom.iterator();
        System.out.println("Information of cars in ParkingRoom:");
        int idx = 1;
        while (iterator.hasNext()) {
            Car cur = iterator.next();
            System.out.println("Information of car " + idx + " :");
            System.out.println("Car Number:" + cur.getNo());
            System.out.println("Owner of the car: " + cur.getName());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Date date = new Date(cur.getEnterTime());
            String str = sdf.format(date);
            System.out.println("EnterTime of the car: " + str);
            System.out.println();
            idx++;
        }
    }

    //显示当前候车场中的所有车辆信息
    public void printWaitingLine() {
        Iterator<Car> iterator = waitingLine.iterator();
        System.out.println("Information of cars in WaitingLine:");
        int idx = 1;
        while (iterator.hasNext()) {
            Car cur = iterator.next();
            System.out.println("Information of car " + idx + " :");
            System.out.println("Car Number: " + cur.getNo());
            System.out.println("Owner of the car: " + cur.getName());
            System.out.println();
            idx++;
        }
    }


}
