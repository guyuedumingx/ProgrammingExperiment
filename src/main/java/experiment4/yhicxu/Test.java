package experiment4.yhicxu;

import experiment4.yhicxu.trafficnetwork.TrafficNetwork;
import util.graphutil.GraphUtil;

public class Test {
    public static void main(String[] args) {
        TrafficNetwork trafficNetwork = new TrafficNetwork();
        trafficNetwork.addSite("A");
        trafficNetwork.addSite("B");
        trafficNetwork.addSite("C");
        trafficNetwork.addSite("D");
        trafficNetwork.addRoad("A", "B");
        trafficNetwork.addRoad("A", "C");
        trafficNetwork.addRoad("A", "D");
        trafficNetwork.addRoad("B", "C");
        trafficNetwork.addRoute("2号线", new String[]{"C", "B", "A", "D"});
        trafficNetwork.addRoute("3号线", new String[]{"A", "B", "C"});
        GraphUtil.exportGraph(trafficNetwork, "C:\\Users\\Administrator\\Desktop\\aaa\\tu.ycx");
    }
}
