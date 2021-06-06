package experiment4.Florence.test;

import com.sun.javafx.geom.Edge;
import experiment4.Florence.pojo.BusLineManger;
import experiment4.Florence.pojo.BusNetwork;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Florence
 */
public class Test {
    @org.junit.Test
    public void testSet(){
//        BusNetwork network = new BusNetwork();
//        BusNetwork.Edge edge =network.new Edge(1,2);
//        BusNetwork.Edge edge1 =network.new Edge(2,1);
//        Set<BusNetwork.Edge> edges =new HashSet<>();
//        edges.add(edge);
//        edges.add(edge1);
//        System.out.println(edges.size());
    }
    @org.junit.Test
    public void testRemoveTestElement(){
//        BusNetwork network = new BusNetwork();
//        BusNetwork.Edge edge =network.new Edge(1,2);
//        BusNetwork.Edge edge1 =network.new Edge(2,1);
//        Set<BusNetwork.Edge> edges =new HashSet<>();
//        edges.add(edge);
//        edges.remove(edge1);
//        System.out.println(edges.size());
    }
    @org.junit.Test
    public void testRemoveTesElement() throws IOException {
        BusLineManger busLineManger = new BusLineManger();
        busLineManger.addLine("A","ss");
    }

    @org.junit.Test
    public void testFindway(){
        BusLineManger busLineManger = new BusLineManger();
        busLineManger.findWay("B","E");
    }
}
