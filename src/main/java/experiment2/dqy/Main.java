package experiment2.dqy;

import experiment2.dqy.Model.Car;
import experiment2.dqy.Model.ParkingLot;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ParkingLot pkl = new ParkingLot();
        Scanner src = new Scanner(System.in);
        int op;
        System.out.println("Welcome to the ParkingLot system, please enter your orders:");
        System.out.println("1: enterCar");
        System.out.println("2: leaveCar");
        System.out.println("3: isInParkingLot");
        System.out.println("4: printParkingRoom");
        System.out.println("5: printWaitingLine");
        System.out.println("6: Exit");
        op = src.nextInt();
        while (op != 6) {
            if (op == 1) {
                Car cur = new Car();
                System.out.println("please input the information of the car");
                System.out.print("Number of the car: ");
                cur.setNo(src.next());
                System.out.print("Owner of the car: ");
                cur.setName(src.next());
                if(pkl.enterCar(cur)) {
                    System.out.println("Success!");
                } else {
                    System.out.println("The car is already in the system!");
                }
            } else if (op == 2) {
                System.out.println("please input the Number of the car");
                Car cur = new Car();
                int money = pkl.leaveCar(cur);
                if (money == -1) {
                    System.out.println("There is no such car!");
                } else {
                    System.out.println("The car is out, please pay for you bill: " + money + " $");
                }
            } else if (op == 3) {

            } else if (op == 4) {

            } else if (op == 5) {

            } else {
                System.out.println("Wrong order, please input again!");
            }
        }
    }
}
