package experiment4.dqy;

import experiment4.dqy.util.LinkList;

public class Map {
    private LinkList place = new LinkList();

    public void addPlace(Station station) {
        place.addBack(station);
    }
}

class Station<T> {
    private String name;
    @Override
    public boolean equals(Object bus) {
        Station cur = (Station) bus;
        if (this.name.equals(cur.name)) {
            return true;
        } else {
            return false;
        }
    }
    Station(String s) {
        this.name = s;
    }
}

class Edge<T> {
    private Station u, v;
    private int length;
    @Override
    public boolean equals(Object bus) {
        Edge cur = (Edge) bus;
        if ((this.u.equals(cur.u) && this.v.equals(cur.v)) || (this.u.equals(cur.v) && this.v.equals(cur.u))) {
            return true;
        } else {
            return false;
        }
    }

    Edge(Station u,Station v, int length) {
        this.u = u;
        this.v = v;
        this.length = length;
    }
}