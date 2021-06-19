package experiment4.Florence;

import experiment4.Florence.pojo.BusLineManger;
import experiment4.Florence.pojo.BusNetwork;
import experiment4.Florence.util.InfShower;
import experiment4.Florence.util.IoUtil;

import java.io.IOException;

/**
 * @author Florence
 */
public class Run {

    static BusLineManger busLineManger = new BusLineManger();
    static InfShower infShower = new InfShower();
    static IoUtil ioUtil = new IoUtil();
    public static void main(String[] args) throws IOException {
        while (true){
            //打印菜单
            infShower.menu();
            String s = ioUtil.readLine();
            //添加站点
            if ("1".equals(s)){
                infShower.inputNodeName();
                String name = ioUtil.readLine();
                busLineManger.addNode(name);
            }
            //查询站点信息
            else if ("2".equals(s)){
                infShower.inputNodeName();
                String name =ioUtil.readLine();
                busLineManger.findNodeInf(name);
            }
            //查询某站点的所有相邻站点
            else if ("3".equals(s)){
                infShower.inputNodeName();
                String name =ioUtil.readLine();
                busLineManger.findNodeAllAdjNodes(name);
            }
            //添加线路
            else if ("4".equals(s)){
                infShower.inputNodeNameAndLineName();
                String[] nodeNameAndLineName= ioUtil.readLine().split(" ");
                busLineManger.addLine(nodeNameAndLineName[0],nodeNameAndLineName[1]);
            }
            //查询线路
            else if ("5".equals(s)){
                infShower.inputBeginNameAndEndName();
                String[] beginNameAndEndName= ioUtil.readLine().split(" ");
                busLineManger.findWay(beginNameAndEndName[0],beginNameAndEndName[1]);
            }
            //exit
            else if ("exit".equals(s)){
                break;
            }
            infShower.after();
        }
    }
}
