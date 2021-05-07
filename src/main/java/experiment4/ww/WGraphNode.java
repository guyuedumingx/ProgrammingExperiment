package experiment4.ww;

import experiment4.ww.util.LinkedNode;
import util.graphutil.GraphNode;

/**
 * 站点类
 * @author yohoyes
 * @date 2021/5/7 22:08
 */
public class WGraphNode implements GraphNode {
    //站点的名称
    private String name = null;
    //邻接链表
    private LinkedNode<WGraphNode> linkedHead = new LinkedNode<>(this);

    @Override
    public String getName() {
        return name;
    }

    /**
     * 利用头插法给当前节点添加邻居
     * @param node 邻居节点
     */
    public void addNeighbor(WGraphNode node,float len){
        LinkedNode next = linkedHead.getNext();
        LinkedNode<WGraphNode> lNode = new LinkedNode<>(node, len);
        lNode.setNext(next);
        linkedHead.setNext(lNode);
    }


}
