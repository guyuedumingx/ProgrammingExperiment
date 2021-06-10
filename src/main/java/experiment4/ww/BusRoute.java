package experiment4.ww;

import experiment4.dqy.Bus;
import experiment4.ww.exception.NoNeighborException;
import experiment4.ww.exception.NodeExistException;
import experiment4.ww.util.LinkedNode;
import util.graphutil.GraphNode;
import util.graphutil.GraphRoute;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 公交线路
 * @author yohoyes
 * @date 2021/5/9 18:29
 */
public class BusRoute implements GraphRoute {
    private LinkedNode<WGraphNode> routeHead = new LinkedNode<>();
    //线路名
    private String name;

    public BusRoute(String name) {
        this.name = name;
    }

    public void add(WGraphNode... nodes) {
        for(WGraphNode node : nodes) {
            try {
                addNodeForRoute(node);
            }catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void addFist(WGraphNode node) {
        LinkedNode<WGraphNode> routeHead = this.routeHead;
        LinkedNode<WGraphNode> routeNode = new LinkedNode<>(node);
        routeNode.setNext(routeHead.getNext());
        routeHead.setNext(routeNode);
    }

    private void addNodeForRoute(WGraphNode node) throws NodeExistException, NoNeighborException{
        LinkedNode<WGraphNode> cur = routeHead;
        while (cur.getNext() != null){
            if(node.equals(cur.getNext().getData())){
                throw new NodeExistException(node.getName() + " 在线路 " + name + " 中已存在,不可重复添加!");
            }
            cur = cur.getNext();
        }
        boolean flag = false;
        WGraphNode data = cur.getData();
        float len = 0;
        if(!cur.equals(routeHead)) {
            ArrayList<WGraphNode> neighborList = data.getNeighborList();
            for(WGraphNode neighbor : neighborList) {
                if(neighbor.equals(node)) {
                    flag = true;
                    break;
                }
            }
        }else{
            flag = true;
        }
        if(flag) {
            cur.setNext(new LinkedNode<>(node));
            node.addToRoute(this);
        }else {
            throw new NoNeighborException(this.name+ " 号线的最后一个站点 " + data.getName() + " 不与站点 " + node.getName()+" 相邻, 无法将站点 " + node.getName()+ " 添加进入线路中!");
        }
    }

    public boolean contains(WGraphNode node){
        LinkedNode<WGraphNode> cur = routeHead;
        while (cur.getNext() != null){
            if(node.equals(cur.getNext().getData())){
                return true;
            }
            cur = cur.getNext();
        }
        return false;
    }

    public Set<WGraphNode> getIntersectionWithRoute(BusRoute route){
        LinkedNode<WGraphNode> cur = routeHead;
        Set<WGraphNode> set = new HashSet<>();
        while (cur.getNext() != null){
            if(route.contains(cur.getData())){
                set.add(cur.getData());
            }
            cur = cur.getNext();
        }
        return set;
    }

    public BusRoute addSelectedRoute(BusRoute route, WGraphNode src, WGraphNode dst) {
        BusRoute res = route;
        try {
            LinkedNode<WGraphNode> cur = routeHead.getNext();
            while (cur.getData() != src) {
                cur = cur.getNext();
            }
            while (cur.getData() != dst) {
                res.add(cur.getData());
                cur = cur.getNext();
            }
            res.add(cur.getData());
        } catch (Exception e) {
            res = addReverseSelectedRoute(route, src, dst);
        }
        return res;
    }

    public BusRoute addReverseSelectedRoute(BusRoute route, WGraphNode src, WGraphNode dst) {
        return this.reverse().addSelectedRoute(route,src, dst);
    }

    public BusRoute reverse(){
        BusRoute route = new BusRoute(this.name);
        LinkedNode<WGraphNode> cur = routeHead.getNext();
        while (cur != null){
            route.addFist(cur.getData());
            cur = cur.getNext();
        }
        return route;
    }

    @Override
    public List<GraphNode> getNodes() {
        ArrayList<GraphNode> list = new ArrayList<>();
        LinkedNode<WGraphNode> cur = routeHead.getNext();
        while (cur != null){
            list.add(cur.getData());
            cur = cur.getNext();
        }
        return list;
    }

    public List<WGraphNode> getNodesExtra(WGraphNode node) {
        ArrayList<WGraphNode> list = new ArrayList<>();
        LinkedNode<WGraphNode> cur = routeHead.getNext();
        while (cur != null){
            if(cur.getData() != node){
                list.add(cur.getData());
            }
            cur = cur.getNext();
        }
        return list;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(name +" 号线:\t");
        LinkedNode<WGraphNode> cur = routeHead.getNext();
        while (cur != null) {
            sb.append(cur.toString());
            if(cur.getNext() != null){
                sb.append(" -- ");
            }
            cur = cur.getNext();
        }
        return sb.toString();
    }
}
