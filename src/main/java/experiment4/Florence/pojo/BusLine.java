package experiment4.Florence.pojo;

import experiment2.Florence.util.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Florence
 */
public class BusLine {
    private String lineName;
    private List<BusNode> lineNodes = new ArrayList<>();

    public BusLine(String lineName) {
        this.lineName = lineName;
    }

    public List<BusNode> addLineNode(BusNode node) {
        lineNodes.add(node);
        return lineNodes;
    }

    public List<BusNode> getAdjNode(BusNode node ){
        List<BusNode> nodes = new ArrayList<>();
        int i = lineNodes.indexOf(node);
        if (i>0){
            nodes.add(lineNodes.get(i-1));
        }
        if (i<lineNodes.size()-1){
            nodes.add(lineNodes.get(i+1));
        }
        return nodes;
    }

    public boolean isContainNode(BusNode node) {
        return lineNodes.contains(node);
    }

    public BusNode getLastAddNode() {
        return lineNodes.get(lineNodes.size() - 1);
    }

    public int lineSize() {
        return lineNodes.size();
    }


    public String getLineName() {
        return lineName;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName;
    }

    public List<BusNode> getLineNodes() {
        return lineNodes;
    }

    public void setLineNodes(List<BusNode> lineNodes) {
        this.lineNodes = lineNodes;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(lineName + ":");
        stringBuilder.append(lineNodes.get(0).getInf());
        for (int i = 1; i < lineNodes.size(); i++) {
            stringBuilder.append("->");
            stringBuilder.append(lineNodes.get(i).getInf());
        }
        return lineName + ":" + stringBuilder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BusLine)) {
            return false;
        }
        BusLine busLine = (BusLine) o;
        return Objects.equals(getLineName(), busLine.getLineName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLineName(), getLineNodes());
    }
}
