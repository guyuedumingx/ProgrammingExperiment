package experiment4.Florence.pojo;

import experiment4.Florence.util.ErrorShower;
import experiment4.Florence.util.InfShower;
import experiment4.Florence.util.IoUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Florence
 */
public class BusLineManger {
    private final BusNetwork busNetwork = new BusNetwork();
    private final List<BusLine> lines = new ArrayList<>();
    ErrorShower errorShower = new ErrorShower();
    InfShower infShower = new InfShower();
    IoUtil ioUtil = new IoUtil();




    public void addLine(String beginBusNodeName,String lineName) throws IOException {
        BusNode node = busNetwork.findNode(beginBusNodeName);
        if (node!=null){
            //声明新线路
            BusLine busLine = new BusLine(lineName);

            List<BusNode> adjNodes = node.getList();
            busLine.addLineNode(node);
            infShower.showAdjNode(adjNodes,busLine.getLineNodes());
            int chooseIndex = Integer.parseInt(ioUtil.readLine());
            //输入-1结束选择路线
            while (chooseIndex!=-1&&adjNodes.size()!=0){
                //获取选择的点
                busLine.addLineNode(adjNodes.get(chooseIndex));
                infShower.showAdjNode(adjNodes,busLine.getLineNodes());
                chooseIndex = Integer.parseInt(ioUtil.readLine());
            }
            //大小至少超过2才是一条公交路线
            if (busLine.lineSize()>=2){
                lines.add(busLine);
            }
            else {
                errorShower.lineLengthNoEnough();
            }
        }
        errorShower.noExistBusNode();
    }

}
