package experiment4.Florence.pojo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Florence
 */
public class BusLine {
    private String lineName;
    private List<BusNode> lineNodes=new ArrayList<>();

    public BusLine(String lineName) {
        this.lineName=lineName;
    }

    public List<BusNode> addLineNode(BusNode node){
        lineNodes.add(node);
        return lineNodes;
    }
    public int lineSize(){
        return lineNodes.size();
    }
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(lineNodes.get(0));
        for (int i=1;i<lineNodes.size();i++){
            stringBuilder.append("->");
            stringBuilder.append(lineNodes.get(i).getInf());
        }
        return lineName+":"+stringBuilder.toString();
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
}
