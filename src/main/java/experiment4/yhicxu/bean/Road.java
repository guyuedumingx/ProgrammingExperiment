package experiment4.yhicxu.bean;

import util.graphutil.GraphEdge;
import util.graphutil.GraphNode;

public class Road implements GraphEdge {

    private int rank;
    private Site firstNode;
    private Site secondNode;

    public Road(Site firstNode, Site secondNode) {
        this.rank = 0;
        this.firstNode = firstNode;
        this.secondNode = secondNode;
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
        return rank;
    }

    @Override
    public Site getFirst() {
        return firstNode;
    }

    @Override
    public Site getSecond() {
        return secondNode;
    }
}
