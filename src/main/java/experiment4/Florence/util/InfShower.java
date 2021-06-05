package experiment4.Florence.util;

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
                System.out.println((index++)+":"+busNode.getInf());
            }
        }
        System.out.println("-1:结束站点建立");
    }
}
