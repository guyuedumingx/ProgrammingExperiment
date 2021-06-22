package experiment4.ww;

import util.graphutil.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author yohoyes
 * @date 2021/5/10 8:39
 */
public class Main {
    private static List<BusRoute> routes = new ArrayList<>();
    private static List<GraphNode> nodes = new ArrayList<>();
    private static List<GraphEdge> edges = new ArrayList<>();

    public static void main(String[] args) {
        WGraphNode nodeA = new WGraphNode("A");
        WGraphNode nodeB = new WGraphNode("B");
        WGraphNode nodeC = new WGraphNode("C");
        WGraphNode nodeD = new WGraphNode("D");
        WGraphNode nodeE = new WGraphNode("E");
        WGraphNode nodeF = new WGraphNode("F");
        WGraphNode nodeG = new WGraphNode("G");
        WGraphNode nodeH = new WGraphNode("H");
        WGraphNode nodeI = new WGraphNode("I");
        WGraphNode nodeJ = new WGraphNode("J");

        nodes.add(nodeA);
        nodes.add(nodeB);
        nodes.add(nodeC);
        nodes.add(nodeD);
        nodes.add(nodeE);
        nodes.add(nodeF);
        nodes.add(nodeG);
        nodes.add(nodeH);
        nodes.add(nodeI);
        nodes.add(nodeJ);

        BusRoute route1 = new BusRoute("1");
        BusRoute route2 = new BusRoute("2");
        routes.add(route1);
        routes.add(route2);

        addLink(nodeA, nodeB);
        addLink(nodeB, nodeC);
        addLink(nodeA, nodeD);
        addLink(nodeD, nodeG);
        addLink(nodeG, nodeE);
        addLink(nodeE, nodeC);
        addLink(nodeE, nodeF);
        addLink(nodeF, nodeJ);
        addLink(nodeF, nodeJ);
        addLink(nodeI, nodeJ);
        addLink(nodeI, nodeH);

        route1.add(nodeA,nodeD,nodeG,nodeE,nodeC, nodeB);
        route2.add(nodeH,nodeI,nodeJ,nodeF, nodeE);

        prt(nodeA,nodeB,nodeC,nodeD,nodeE,nodeF,nodeG, nodeH, nodeI, nodeJ);
        System.out.println(route1.toString());
        System.out.println(route2.toString());

        WGraphNode srcNode = nodeA;
        WGraphNode dstNode = nodeH;
        List<BusRoute> srcRoutes = srcNode.getAllRoutes();
        List<BusRoute> dstRoutes = dstNode.getAllRoutes();
        Iterator<BusRoute> iterator = srcRoutes.iterator();
        BusRoute res = null;

        while (iterator.hasNext()){
            BusRoute route = iterator.next();
            if(dstRoutes.contains(route)){
                res = new BusRoute(route.getName()+"号线 "+srcNode.getName()+"->"+dstNode.getName());
                res = route.addSelectedRoute(res, srcNode, dstNode);
                System.out.println();
                System.out.println(res);
            } else {
                List<WGraphNode> nodes = route.getNodesExtra(srcNode);
                for(WGraphNode node : nodes){
                    List<BusRoute> allRoutes = node.getAllRoutesExtra(route);
                    if(allRoutes.size()==0){
                        continue;
                    }
                    List<BusRoute> intersection = getIntersection(allRoutes, dstRoutes);
                    while (intersection.size() > 0){
                        System.out.println();
                        res = new BusRoute("先"+route.getName()+"号线 "+srcNode.getName()+"->"+node.getName());
                        res = route.addSelectedRoute(res, srcNode, node);
                        System.out.println(res);
                        BusRoute remove = intersection.remove(0);
                        res = new BusRoute("再"+remove.getName()+"号线 "+node.getName()+"->"+dstNode.getName());
                        res = remove.addSelectedRoute(res, node, dstNode);
                        System.out.println(res);
                    }
                }
            }
        }
        GraphUtil.exportGraph(getGraph(), "a.ycx");
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
        GraphEdge graphEdge = new GraphEdge(){
            @Override
            public int getRank() {
                return 0;
            }
            @Override
            public GraphNode getFirst() {
                return node1;
            }
            @Override
            public GraphNode getSecond() {
                return node2;
            }
        };
        edges.add(graphEdge);

        node1.addNeighbor(node2,len);
        node2.addNeighbor(node1,len);
    }

    public static void addLink(WGraphNode node1, WGraphNode node2){
        addLink(node1,node2,0);
    }

    /**
     * 打印节点
     */
    public static void prt(WGraphNode... nodes){
        for(WGraphNode node : nodes) {
            System.out.println(node);
        }
    }

    public static Graph getGraph() {
        return new Graph(){
            @Override
            public int getType() {
                return 0;
            }

            @Override
            public List<GraphNode> getNodes() {
                return nodes;
            }

            @Override
            public List<GraphEdge> getEdges() {
                return edges;
            }

            @Override
            public List<GraphRoute> getRoutes() {
                return getGraphRoutes();
            }
        };
    }

    private static List<GraphRoute> getGraphRoutes() {
        Iterator<BusRoute> iterator = routes.iterator();
        List<GraphRoute> nodes = new ArrayList<>();
        while (iterator.hasNext()) {
            nodes.add(iterator.next());
        }
        return nodes;
    }
}
