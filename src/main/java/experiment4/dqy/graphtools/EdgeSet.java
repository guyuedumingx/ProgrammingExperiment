package experiment4.dqy.graphtools;

import experiment4.dqy.Bus;
import experiment4.dqy.util.LinkList;

import java.util.Iterator;

public class EdgeSet {
    private LinkList edgeSet = new LinkList();

    //判断边集中是否存在某条边
    public boolean hasEdge(String u, String v) {
        return edgeSet.contains(new Edge(u, v, 0));
    }

    //加边
    public void addEdge(String u, String v, int length) {
        Edge edge = new Edge(u, v, length);
        edgeSet.addBack(edge);
    }

    //删边
    public void deleteEdge(String u, String v) {
        Edge edge = new Edge(u, v, 0);
        edgeSet.delete(edge);
    }

    public void deleteEdge(String name) {
        Iterator iterator = edgeSet.iterator();
        while (iterator.hasNext()) {
            Edge cur = (Edge) iterator.next();
            if(cur.getU().equals(name) || cur.getV().equals(name)) {
                edgeSet.delete(cur);
            }
        }
    }

    //改边
    public void modifyEdge(String u, String v, int length) {
        Edge edge = new Edge(u, v, length);
        Iterator iterator = edgeSet.iterator();
        while (iterator.hasNext()) {
            Edge cur = (Edge) iterator.next();
            if(cur.equals(edge)) cur.setLength(length);
            return;
        }
    }

    //查边
    public int getEdgeLength(String u, String v) {
        Edge edge = new Edge(u, v, 0);
        Iterator iterator = edgeSet.iterator();
        while (iterator.hasNext()) {
            Edge cur = (Edge) iterator.next();
            if(cur.equals(edge)) return cur.getLength();
        }
        return -1;
    }

    //更新边集中某个点的名字
    public void updateName(String name , String newName) {
        Iterator iterator = edgeSet.iterator();
        while (iterator.hasNext()) {
            Edge cur = (Edge) iterator.next();
            if(cur.getU().equals(name)) {
                cur.setU(newName);
            }
            if(cur.getV().equals(name)) {
                cur.setV(newName);
            }
        }
    }

    //输出所有边的连接情况
    public void printWeb() {
        Iterator iterator = edgeSet.iterator();
        System.out.println("Connection State:");
        while (iterator.hasNext()) {
            Edge cur = (Edge) iterator.next();
            System.out.println(cur.getU() + " ----- " + cur.getV());
        }
    }

    //在给这条边添加可用的公交
    public void addBusToEdge(String u, String v, String no) {
        Edge edge = new Edge(u, v, 0);
        Iterator iterator = edgeSet.iterator();
        while (iterator.hasNext()) {
            Edge cur = (Edge) iterator.next();
            if(cur.equals(edge)) cur.addToChannel(no);
            return;
        }
    }
}

class Edge {
    private String u, v;
    private int length;
    private LinkList channel = new LinkList();
    @Override
    public boolean equals(Object bus) {
        Edge cur = (Edge) bus;
        if ((this.u.equals(cur.u) && this.v.equals(cur.v)) || (this.u.equals(cur.v) && this.v.equals(cur.u))) {
            return true;
        } else {
            return false;
        }
    }

    public String getU() {
        return u;
    }

    public void setU(String u) {
        this.u = u;
    }

    public String getV() {
        return v;
    }

    public void setV(String v) {
        this.v = v;
    }

    Edge(String u, String v, int length) {
        this.u = u;
        this.v = v;
        this.length = length;
    }

    public LinkList getChannel() {
        return channel;
    }

    public void addToChannel(String no) {
        this.channel.addBack(no);
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}