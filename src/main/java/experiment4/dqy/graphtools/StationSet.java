package experiment4.dqy.graphtools;

import experiment4.dqy.util.LinkList;
import java.util.Iterator;

public class StationSet {
    private int size;
    private LinkList place = new LinkList();

    /**
     * 以下是对单点的操作
     */

    //通过名字获取某个结点
    public Station getStation(String name) {
        Station need = new Station(name);
        Iterator iterator = place.iterator();
        while (iterator.hasNext()) {
            Station cur = (Station) iterator.next();
            if (cur.equals(need)) return cur;
        }
        return null;
    }

    //获取某个点的邻接点
    public LinkList getConnection(String name) {
        Station start = this.getStation(name);
        Iterator iterator = place.iterator();
        while (iterator.hasNext()) {
            Station station = (Station) iterator.next();
            if(station.equals(start)) return station.getConnection();
        }
        return null;
    }

    //判断是否存在某个点
    public boolean contains(String name) {
        Station s = new Station(name);
        return place.contains(s);
    }

    //加点
    public void addStation(String name) {
        place.addBack(new Station(name));
        size++;
    }

    //删点
    public void deleteStation(String name) {
        Station station = this.getStation(name);
        Iterator iterator = place.iterator();
        while (iterator.hasNext()) {
            Station cur = (Station) iterator.next();
            if(station.equals(station)) continue;
            cur.cancelConnection(station);
        }
        place.delete(station);
        size--;
    }

    //改点
    public void modifyStation(String name, String newName) {
        Station station = this.getStation(name);
        station.setName(name);
    }

    /**
     * 以下是对连点间关联关系的操作
     */

    //连接两点
    public void connect(String nameU, String nameV) {
        Station u = this.getStation(nameU);
        Station v = this.getStation(nameV);
        u.connectTo(v);
        v.connectTo(u);
    }

    //解除两点的连接关系
    public void disconnect(String nameU, String nameV) {
        Station u = this.getStation(nameU);
        Station v = this.getStation(nameV);
        u.cancelConnection(v);
        v.cancelConnection(u);
    }
}

class Station {
    private String name;
    private LinkList connection = new LinkList();
    @Override
    public boolean equals(Object bus) {
        Station cur = (Station) bus;
        if (this.name.equals(cur.name)) {
            return true;
        } else {
            return false;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void connectTo(Station s) {
        this.connection.addBack(s);
    }

    public void cancelConnection(Station s) {
        connection.delete(s);
    }

    public LinkList getConnection() {
        return connection;
    }

    Station(String s) {
        this.name = s;
    }
}