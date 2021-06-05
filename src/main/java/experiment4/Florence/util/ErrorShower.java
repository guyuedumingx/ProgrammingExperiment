package experiment4.Florence.util;

/**
 * @author Florence
 */
public class ErrorShower {
    public void noExistBusNode(){
        System.out.println("无此节点");
    }

    public void noExistEdge() {
        System.out.println("此边不存在");
    }

    public void BusNodeExist() {
        System.out.println("节点已存在");
    }

    public void lineLengthNoEnough() {
        System.out.println("线路长度不够");
    }
}
