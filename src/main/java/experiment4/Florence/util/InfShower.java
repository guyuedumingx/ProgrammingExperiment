package experiment4.Florence.util;

import experiment4.Florence.pojo.BusLine;
import experiment4.Florence.pojo.BusNode;

import java.util.Iterator;
import java.util.List;

/**
 * @author Florence
 */
public class InfShower {

    /**
     * 根据相邻节点与已经加入线路的节点来展示数据
     * @param adjNodes 相邻的列表
     * @param alreadyExistNodes 已经存在的列表0
     */
    public void showAdjNode(List<BusNode> adjNodes,List<BusNode> alreadyExistNodes) {
        System.out.println("下一站点你可以选择");
        int index=0;
        for (BusNode busNode:adjNodes){
            if (!alreadyExistNodes.contains(busNode)){
                System.out.println((index)+":"+busNode.getInf());
            }
            index++;
        }
        System.out.println("-1:结束线路建立");
    }

    public void showAddLineSuccess(BusLine busLine) {
        System.out.println("添加线路:"+busLine+"成功");
    }

    public void menu(){
        System.out.println("欢迎来到公交线路系统:输入对应序号使用功能~");
        System.out.println("1:添加站点");
        System.out.println("2:查询站点信息");
        System.out.println("3:查询某站点的所有相邻站点");
        System.out.println("4:添加线路");
        System.out.println("5:查询线路");
        System.out.println("exit:退出程序");
    }

    public void inputNodeName() {
        System.out.println("请输入站点名");
    }

    public void inputNodeNameAndLineName() {
        System.out.println("请输入站点名和线路名（空格隔开）");
    }

    public void inputBeginNameAndEndName() {
        System.out.println("请输入起点站点名与终点站点名");
    }

    public void after() {
        System.out.println("===============================");
    }
}
