package experiment4.yhicxu;

import experiment4.yhicxu.trafficnetwork.TrafficNetwork;
import util.graphutil.GraphData;
import util.graphutil.GraphUtil;

public class Test {
    public static void main(String[] args) {
//        TrafficNetwork tn = new TrafficNetwork(0);
//        tn.addSite("A");
//        tn.addSite("B");
//        tn.addSite("C");
//        tn.addSite("D");
//        tn.addRoad("A", "B", 100);
//        tn.addRoad("A", "C", 120);
//        tn.addRoad("A", "D", 150);
//        tn.addRoad("B", "C", 80);
//        tn.addRoute("2号线", new String[]{"C", "B", "A", "D"});
//        tn.addRoute("3号线", new String[]{"A", "B", "C"});
//        System.out.println(tn.renameRoute("2号线", "5号线"));
//        System.out.println(tn.modifyRoute("3号线", new String[]{"B", "A"}));
//        GraphUtil.exportGraph(tn, "C:\\Users\\Administrator\\Desktop\\aaa\\tu.ycx");
        GraphData graphData = GraphUtil.importGraph("C:\\Users\\Administrator\\Desktop\\aaa\\tu.ycx");
        TrafficNetwork tn = new TrafficNetwork(graphData);
        tn.findFeasibleRoutes("A", "H");
    }
}
