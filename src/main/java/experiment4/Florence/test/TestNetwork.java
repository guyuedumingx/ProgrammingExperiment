package experiment4.Florence.test;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import experiment4.Florence.pojo.BusNetwork;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author Florence
 */
public class TestNetwork {
    static BusNetwork busNetwork = new BusNetwork();
    @BeforeClass
    public static void before(){
        busNetwork.addNode("广州");
        busNetwork.addNode("揭阳");
        busNetwork.addNode("北京");
        busNetwork.addNode("杭州");
    }


    @Test
    public void curd(){

        busNetwork.deleteNode("揭阳");
        System.out.println("ok");
    }
    @Test
    public void link(){
//        busNetwork.editNode("揭阳","汕头");
        busNetwork.linkBusNode("广州","北京");
        busNetwork.editEdge("北京","广州","北京","揭阳");
        System.out.println("ok");
    }
}
