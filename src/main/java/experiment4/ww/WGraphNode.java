package experiment4.ww;

import experiment4.ww.util.LinkedNode;
import util.graphutil.GraphNode;
import java.util.ArrayList;

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

    public WGraphNode(String name){
        this.name = name;
    }

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

    /**
     * 清除某个邻居
     * @param node
     */
    public void removeNeighbor(WGraphNode node) {
        LinkedNode<WGraphNode> cur = linkedHead;
        while (cur.getNext() != null) {
            if(cur.getNext().getData().equals(node)) {
                cur.setNext(cur.getNext().getNext());
            }
            cur = cur.getNext();
        }
    }

    /**
     * 获取邻居列表
     */
    public ArrayList<WGraphNode> getNeighborList(){
        LinkedNode<WGraphNode> cur = linkedHead.getNext();
        ArrayList<WGraphNode> list = new ArrayList<>();
        while (cur != null) {
            list.add(cur.getData());
            cur = cur.getNext();
        }
        return list;
    }

    /**
     * 返回与指定相邻站点的距离
     * @return
     */
    public float getNeighborLength(WGraphNode node){
        LinkedNode<WGraphNode> cur = linkedHead.getNext();
        while (cur != null) {
            if(cur.getData().equals(node)){
                return cur.getLen();
            }
            cur = cur.getNext();
        }
        return -1;
    }

    @Override
    public boolean equals(Object obj) {
        WGraphNode node = (WGraphNode) obj;
        return this.getName().equals(node.getName());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(name + ":\t");
        LinkedNode<WGraphNode> cur = linkedHead.getNext();
        while (cur != null) {
            sb.append(cur.toString());
            if(cur.getNext() != null){
                sb.append("-->\t");
            }
            cur = cur.getNext();
        }
        return sb.toString();
    }
}
