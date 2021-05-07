package experiment4.dqy.graphtools;

import experiment4.dqy.util.LinkList;
import java.util.Iterator;

public class StationSet {
    private LinkList place = new LinkList();

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
    public LinkList getConnection(Station start) {
        Iterator iterator = place.iterator();
        while (iterator.hasNext()) {
            Station station = (Station) iterator.next();
            if(station.equals(start)) return station.getConnection();
        }
        return null;
    }

    //两点间连边
    public void connect(Station u, Station v) {
        u.connectTo(v);
        v.connectTo(u);
    }

    //加点
    public void addStation(Station station) {
        place.addBack(station);
    }

    //删点
}

class Station<T> {
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

    public void connectTo(Station s) {
        this.connection.addBack(s);
    }

    public LinkList getConnection() {
        return connection;
    }

    Station(String s) {
        this.name = s;
    }
}