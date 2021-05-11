package experiment4.ww;

/**
 * @author yohoyes
 * @date 2021/5/10 8:39
 */
public class Main {
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
