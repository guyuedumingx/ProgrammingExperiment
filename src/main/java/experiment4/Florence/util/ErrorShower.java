package experiment4.Florence.util;

/**
 * @author Florence
 */
public class ErrorShower {
    public void noExistBusNode(String... nodes){
        StringBuilder stringBuilder = new StringBuilder("节点：");
        for (String s:nodes){
            stringBuilder.append(s);
            stringBuilder.append(" ");
        }
        stringBuilder.append("不存在");
        System.out.println(stringBuilder.toString());
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
