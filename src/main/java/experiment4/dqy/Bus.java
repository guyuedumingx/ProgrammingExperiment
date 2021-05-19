package experiment4.dqy;

import experiment4.dqy.util.LinkList;

import java.util.Iterator;
import java.util.Scanner;

public class Bus {
    private Scanner src = new Scanner(System.in);
    private String no;
    private LinkList busLine = new LinkList();

    @Override
    public boolean equals(Object bus) {
        Bus cur = (Bus) bus;
        if (this.no.equals(cur.no)) {
            return true;
        } else {
            return false;
        }
    }

    //查看公交线路是否会到达站点carNo
    public boolean contain(String carNo) {
        Iterator iterator = busLine.iterator();
        while (iterator.hasNext()) {
            if (carNo.equals(iterator.next())) return true;
        }
        return false;
    }

    //创建线路
    public void createLine(int num) {
        for (int i = 1; i <= num; i++) {
            String sta = src.next();
            if (busLine.contains(sta)) {
                busLine.clear();
                System.out.println("The station is repeated");
                return;
            }
            busLine.addBack(sta);
        }
    }

    //在第idx个点后面添加站点
    public void addStation(int idx, String sta) {
        if (busLine.contains(sta)) {
            System.out.println("Station " + sta + " has been in the Line");
            return;
        }
        busLine.addAt(idx, sta);
    }

    //删除车站
    public void deleteStation(String sta) {
        if (busLine.contains(sta)) {
            System.out.println("Station " + sta + " has been in the Line");
            return;
        }
        busLine.delete(sta);
    }

    //查看线路
    public void printLine() {
        Iterator iterator = busLine.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " -----> ");
        }
    }
}