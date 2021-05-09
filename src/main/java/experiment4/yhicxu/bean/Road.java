package experiment4.yhicxu.bean;

import util.graphutil.GraphEdge;

public class Road implements GraphEdge {

    private int length;
    private int routeNum;
    private Site firstNode;
    private Site secondNode;

    public Road(Site firstNode, Site secondNode, int length) {
        this.routeNum = 0;
        this.length = length;
        this.firstNode = firstNode;
        this.secondNode = secondNode;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getRouteNum() {
        return routeNum;
    }

    public void addRoute() {
        routeNum++;
    }

    public void subRoute() {
        routeNum--;
    }

    @Override
    public boolean equals(Object o) {
        Road that = (Road) o;
        if (this.firstNode.equals(that.firstNode) && this.secondNode.equals(that.secondNode)) {
            return true;
        } else if (this.firstNode.equals(that.secondNode) && this.secondNode.equals(that.firstNode)) {
            return true;
        }
        return false;
    }

    @Override
    public int getRank() {
        return this.getLength();
    }

    @Override
    public Site getFirst() {
        return firstNode;
    }

    @Override
    public Site getSecond() {
        return secondNode;
    }

    @Override
    public String toString() {
        return "Road{" +
                ", routeNum=" + routeNum +
                ", firstNode=" + firstNode +
                ", secondNode=" + secondNode +
                '}';
    }
}
