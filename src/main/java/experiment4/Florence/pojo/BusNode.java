package experiment4.Florence.pojo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 公交节点类
 * @author Florence
 */
public class BusNode {
    private int id;
    private String inf;
    private List<BusNode> adjNodes = new ArrayList<>();



    public BusNode(int id, String inf) {
        this.id = id;
        this.inf = inf;
    }

    public BusNode() {
    }

    public void addAdj(BusNode busNode){
        adjNodes.add(busNode);
    }

    public void deleteAdj(BusNode busNode){
        adjNodes.remove(busNode);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInf() {
        return inf;
    }

    public void setInf(String inf) {
        this.inf = inf;
    }

    public List<BusNode> getList() {
        return adjNodes;
    }

    public void setList(List<BusNode> list) {
        this.adjNodes = list;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BusNode)) {
            return false;
        }
        BusNode busNode = (BusNode) o;
        return getId() == busNode.getId() &&
                Objects.equals(getInf(), busNode.getInf());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getInf(), adjNodes);
    }

    @Override
    public String toString() {
        return "BusNode{" +
                "id=" + id +
                ", inf='" + inf + '\'' +
                '}';
    }
}
