package experiment4.dqy;

import experiment4.dqy.graphtools.EdgeSet;
import experiment4.dqy.graphtools.StationSet;
import experiment4.dqy.util.LinkList;

import java.util.Iterator;

public class Map {
    private StationSet stationSet = new StationSet();
    private EdgeSet edgeSet = new EdgeSet();
    private int[] fa = new int [200010];

    /**
     * 对点的操作
     */

    //加点
    void addNode(String name) {
        if (stationSet.contains(name)) {
            System.out.println("Station " + name + " has been added!");
            return;
        }
        stationSet.addStation(name);
    }

    //删点
    void deleteNode(String name) {
        if (!stationSet.contains(name)) {
            System.out.println("Station " + name + " doesn't exist!");
            return;
        }
        stationSet.deleteStation(name);
        edgeSet.deleteEdge(name);
    }

    //改点
    void modifyNode(String name, String newName) {
        if (!stationSet.contains(name)) {
            System.out.println("Station " + name + " doesn't exist!");
            return;
        }
        stationSet.modifyStation(name, newName);
        edgeSet.updateName(name, newName);
    }

    //查点的连接情况
    void getConnectionInfo() {
        edgeSet.printWeb();
    }

    //获取某个点的连接情况
    public LinkList getConnection(String name) {
        return stationSet.getConnection(name);
    }

    /**
     * 对边的操作
     */

    //加边
    void addEdge(String u, String v, int length) {
        if(edgeSet.hasEdge(u, v)) {
            System.out.println("Edge " + u + "-----" + v + "has been added!");
            return;
        }
        stationSet.connect(u, v);
        edgeSet.addEdge(u, v, length);
    }

    //删边
    void deleteEdge(String u, String v) {
        if (!edgeSet.hasEdge(u, v)) {
            System.out.println("Edge " + u + "-----" + v + " doesn't exist!");
            return;
        }
        stationSet.disconnect(u, v);
        edgeSet.deleteEdge(u, v);
    }

}


