package experiment4.ww;

import experiment4.dqy.Bus;
import experiment4.ww.util.LinkedNode;
import util.graphutil.GraphNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yohoyes
 * @date 2021/5/10 8:39
 */
public class Main {
    private static List<BusRoute> routes = new ArrayList<>();
    public static void main(String[] args) {
        WGraphNode nodeA = new WGraphNode("A");
        WGraphNode nodeB = new WGraphNode("B");
        WGraphNode nodeC = new WGraphNode("C");
        WGraphNode nodeD = new WGraphNode("D");
        WGraphNode nodeE = new WGraphNode("E");
        WGraphNode nodeF = new WGraphNode("F");
        WGraphNode nodeG = new WGraphNode("G");

        BusRoute route1 = new BusRoute("1");
        BusRoute route2 = new BusRoute("2");
        BusRoute route3 = new BusRoute("3");
        routes.add(route1);
        routes.add(route2);
        routes.add(route3);

        addLink(nodeA,nodeB,200);
        addLink(nodeE,nodeA,300);
        addLink(nodeB, nodeC,300);
        addLink(nodeE,nodeF, 100);
        addLink(nodeF,nodeD,100);
        addLink(nodeA,nodeD,500);
        addLink(nodeD,nodeG,100);
        addLink(nodeG, nodeB,200);

        route1.add(nodeC,nodeB,nodeG,nodeD,nodeF);
        route2.add(nodeD,nodeA,nodeE,nodeF);
        route3.add(nodeC,nodeB,nodeA,nodeE,nodeF);

        prt(nodeA,nodeB,nodeC,nodeD,nodeE,nodeF,nodeG);
        System.out.println(route1.toString());
        System.out.println(route2.toString());
        System.out.println(route3.toString());

        WGraphNode srcNode = nodeC;
        WGraphNode dstNode = nodeE;
        List<BusRoute> srcRoutes = srcNode.getAllRoutes();
        List<BusRoute> dstRoutes = dstNode.getAllRoutes();
        BusRoute res = new BusRoute(srcNode.getName()+"->"+dstNode.getName());

        for(BusRoute route : srcRoutes){
            if(dstRoutes.contains(route)){
                route.addSelectedRoute(res,srcNode,dstNode);
                System.out.println();
                System.out.println(res);
            } else {
                List<WGraphNode> nodes = route.getNodesExtra(srcNode);
                for(WGraphNode node : nodes){
                    List<BusRoute> allRoutes = node.getAllRoutes();
                    List<BusRoute> intersection = getIntersection(allRoutes, dstRoutes);
                    while (intersection.size() > 0){
                        System.out.println();
                        res = new BusRoute("先"+route.getName()+"号线 "+srcNode.getName()+"->"+node.getName());
                        route.addSelectedRoute(res,srcNode, node);
                        System.out.println(res);
                        BusRoute remove = intersection.remove(0);
                        res = new BusRoute("再"+remove.getName()+"号线 "+node.getName()+"->"+dstNode.getName());
                        remove.addSelectedRoute(res, node, dstNode);
                        System.out.println(res);
                    }
                }
            }
        }

    }

    public static List<BusRoute> getIntersection(List<BusRoute> list1, List<BusRoute> list2){
        List<BusRoute> res =  new ArrayList<>();
        for(BusRoute route1:list1){
            for(BusRoute route2: list2){
                if(route1.equals(route2)){
                    res.add(route1);
                }
            }
        }
        return res;
    }

    public static void addLink(WGraphNode node1, WGraphNode node2, float len){
        node1.addNeighbor(node2,len);
        node2.addNeighbor(node1,len);
    }

    /**
     * 打印节点
     */
    public static void prt(WGraphNode... nodes){
        for(WGraphNode node : nodes) {
            System.out.println(node);
        }
    }
}
