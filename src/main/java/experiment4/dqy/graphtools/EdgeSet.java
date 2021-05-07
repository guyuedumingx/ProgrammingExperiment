package experiment4.dqy.graphtools;

import experiment4.dqy.util.LinkList;

import java.util.Iterator;

public class EdgeSet {
    private LinkList edgeSet = new LinkList();

    //判断边集中是否存在某条边
    private boolean hasEdge(Edge edge) {
        Iterator iterator = edgeSet.iterator();
        while (iterator.hasNext()) {
            Edge cur = (Edge) iterator.next();
            if (cur.equals(edge)) return true;
        }
        return false;
    }

    //加边
    public void addEdge(String u, String v, int length) {
        Edge edge = new Edge(u, v, length);
        if (!hasEdge(edge)) {
            edgeSet.addBack(edge);
        } else {
            System.out.println("Edge has been added");
        }
    }

    //删边
    public void deleteEdge(String u, String v) {
        Edge edge = new Edge(u, v, 0);
        edgeSet.delete(edge);
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
        System.out.println("Edge doesn't exist");
    }

    //查边
    public int getEdgeLength(String u, String v) {
        Edge edge = new Edge(u, v, 0);
        Iterator iterator = edgeSet.iterator();
        while (iterator.hasNext()) {
            Edge cur = (Edge) iterator.next();
            if(cur.equals(edge)) return cur.getLength();
        }
        System.out.println("Edge doesn't exist");
        return -1;
    }

}

class Edge<T> {
    private String u, v;
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

    Edge(String u,String v, int length) {
        this.u = u;
        this.v = v;
        this.length = length;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}