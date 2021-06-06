package experiment4.Florence.pojo;

import experiment4.Florence.util.ErrorShower;
import experiment4.Florence.util.InfShower;
import experiment4.Florence.util.IoUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Florence
 */
public class BusLineManger {
    private static final BusNetwork busNetwork = new BusNetwork();
    private static final List<BusLine> lines = new ArrayList<>();
    private static ErrorShower errorShower = new ErrorShower();
    private static InfShower infShower = new InfShower();
    private static IoUtil ioUtil = new IoUtil();

    public BusLineManger() {
        init();
    }

    BusLineManger(String fileName) {
        //根据文件初始化
    }

    /**
     * @param beginBusNodeName 起点
     * @param lineName         线路名
     * @throws IOException
     */
    public void addLine(String beginBusNodeName, String lineName) throws IOException {
        BusNode node = busNetwork.findNode(beginBusNodeName);
        if (node != null) {
            //声明新线路
            BusLine busLine = new BusLine(lineName);
            List<BusNode> alreadyAddNodes = busLine.getLineNodes();
            alreadyAddNodes.add(node);
            do {
                infShower.showAdjNode(node.getList(), alreadyAddNodes);
                int chooseIndex = Integer.parseInt(ioUtil.readLine());
                if (chooseIndex == -1) {
                    break;
                }
                BusNode nowNode = node.getAdjNodeByIndex(chooseIndex);
                BusNode lastAddNode = busLine.getLastAddNode();
                //添加到线路中
                busLine.addLineNode(nowNode);
                //获取对应的边
                BusNetwork.Edge edge = busNetwork.getEdge(new BusNetwork.Edge(lastAddNode.getId(), nowNode.getId()));
                //对应边线路增加
                edge.lines++;
                node = nowNode;
            }
            while (true);
            //大小至少超过2才是一条公交路线
            if (busLine.lineSize() >= 2) {
                lines.add(busLine);
                infShower.showAddLineSuccess(busLine);
            } else {
                errorShower.lineLengthNoEnough();
            }
        } else {
            errorShower.noExistBusNode(beginBusNodeName);
        }
    }

    public void findWay(String beginName, String endName) {
        BusNode beginNode = busNetwork.findNode(beginName);
        BusNode endNode = busNetwork.findNode(endName);
        if (beginNode != null && endNode != null) {
            int wayCount = 1;
            //直达线路查询
            System.out.println("直达线路：");
            for (BusLine busLine : lines) {
                List<BusNode> lineNodes = busLine.getLineNodes();
                if (lineNodes.contains(beginNode) && lineNodes.contains(endNode)) {
                    System.out.println(wayCount + "." + busLine.getLineName());
                    wayCount++;
                }
            }
            //转车线路查询
            System.out.println("转车线路：");
            ArrayList<BusNode> alreadyExistNode = new ArrayList<>();
            alreadyExistNode.add(beginNode);
            DFS(beginNode, endNode, lines, new ArrayList<>(),alreadyExistNode,new ArrayList<>());
            wayCount=1;
        } else {
            errorShower.noExistBusNode(beginName, endName);
        }

    }

    /**
     * @param curLocationNode 当前节点
     * @param endNode         终点
     * @param lines           所有线路
     * @param strategy        搭乘线路
     * @param alreadyExistNode 已经存在的点
     */
    int wayCount=1;
    private void DFS(BusNode curLocationNode, BusNode endNode, List<BusLine> lines, ArrayList<BusLine> strategy,ArrayList<BusNode> alreadyExistNode,ArrayList<String> changeBusLineNodeName) {
        //到达终点
        if (curLocationNode.equals(endNode)) {
            if (strategy.size()<2){
                return;
            }
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("(");
            stringBuilder.append(changeBusLineNodeName.get(0));
            stringBuilder.append(")");
            stringBuilder.append(strategy.get(0).getLineName());
            for (int i=1;i<strategy.size();i++){
                stringBuilder.append("->");
                stringBuilder.append("(");
                stringBuilder.append(changeBusLineNodeName.get(i));
                stringBuilder.append(")");
                stringBuilder.append(strategy.get(i).getLineName());
            }
            stringBuilder.append("->");
            stringBuilder.append("(");
            stringBuilder.append(endNode.getInf());
            stringBuilder.append(")");
            stringBuilder.append("终点");
            //打印线路
            System.out.println(wayCount+"."+stringBuilder.toString());
            wayCount++;
            return;
        }
        //dfs寻找下一个点
        for (BusLine busLine:lines){
            //该线路是否包含当前节点
            boolean isAdd = false;
            if (busLine.isContainNode(curLocationNode)){
                //继续乘坐相同线路
                if (strategy.size()==0||!strategy.get(strategy.size()-1).equals(busLine)){
                    isAdd=true;
                    strategy.add(busLine);
                    changeBusLineNodeName.add(curLocationNode.getInf());
                }
                //获取下一个可达点
                List<BusNode> nextNodes= busLine.getAdjNode(curLocationNode);
                for (BusNode node :nextNodes){
                    //如果未经过点
                    if (!alreadyExistNode.contains(node)) {
                        alreadyExistNode.add(node);
                        //进入DFS下一个可达点
                        DFS(node, endNode, lines, strategy, alreadyExistNode,changeBusLineNodeName);
                        alreadyExistNode.remove(alreadyExistNode.size() - 1);
                    }
                }
                if (isAdd){
                    strategy.remove(strategy.size()-1);
                    changeBusLineNodeName.remove(changeBusLineNodeName.size()-1);
                }
            }
        }
    }

    /**
     * 初始化
     */
    private void init() {
        //点
        busNetwork.addNode("A");
        busNetwork.addNode("B");
        busNetwork.addNode("C");
        busNetwork.addNode("D");
        busNetwork.addNode("E");
        busNetwork.addNode("F");
        //找出节点
        BusNode a = busNetwork.findNode("A");
        BusNode b = busNetwork.findNode("B");
        BusNode c = busNetwork.findNode("C");
        BusNode d = busNetwork.findNode("D");
        BusNode e = busNetwork.findNode("E");
        BusNode f = busNetwork.findNode("F");
        //边
        busNetwork.linkBusNode("A", "B");
        busNetwork.linkBusNode("B", "D");
        busNetwork.linkBusNode("B", "F");
        busNetwork.linkBusNode("C", "D");
        busNetwork.linkBusNode("C", "E");
        busNetwork.linkBusNode("C", "F");
        BusLine busLine1 = new BusLine("1号线");
        busLine1.addLineNode(a);
        busLine1.addLineNode(b);
        BusLine busLine2 = new BusLine("2号线");
        busLine2.addLineNode(b);
        busLine2.addLineNode(d);
        busLine2.addLineNode(c);
        BusLine busLine3 = new BusLine("3号线");
        busLine3.addLineNode(b);
        busLine3.addLineNode(f);
        busLine3.addLineNode(c);
        BusLine busLine4 = new BusLine("4号线");
        busLine4.addLineNode(c);
        busLine4.addLineNode(e);
        //添加线路
        lines.add(busLine1);
        lines.add(busLine2);
        lines.add(busLine3);
        lines.add(busLine4);

    }

    public void addNode(String name) {
        busNetwork.addNode(name);
    }

    public void findNodeInf(String name) {
        BusNode node = busNetwork.findNode(name);
        if (node != null) {
            System.out.println(node);
        } else {
            errorShower.noExistBusNode(name);
        }
    }

    public void findNodeAllAdjNodes(String name) {
        BusNode node = busNetwork.findNode(name);
        if (node != null) {
            System.out.println(node.getList());
        } else {
            errorShower.noExistBusNode(name);
        }
    }
}
