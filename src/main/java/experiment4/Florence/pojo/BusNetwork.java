package experiment4.Florence.pojo;

import experiment4.Florence.util.ErrorShower;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Florence
 * 公交网络类
 */
public class BusNetwork {

    private int size=0;
    private final static List<BusNode> nodes = new ArrayList<>();
    private final static Set<Edge> edges  = new HashSet<>();
    private final static Map<String,Integer> nameToId=new ConcurrentHashMap<>();
    private final ErrorShower errorShower = new ErrorShower();

    /**
     * 添加公交节点（还没连接边）
     * @param name
     */
    public void addNode(String name){
        if (!isContainNode(name)) {
            nameToId.put(name,size);
            BusNode busNode = new BusNode(size++, name);
            nodes.add(busNode);
            return;
        }
        errorShower.BusNodeExist();
    }

    /**
     * 修改节点信息
     * @param oldName
     * @param newName
     */
    public void editNode(String oldName,String newName){
        BusNode oldBusNode=findNode(oldName);
        if (oldBusNode!=null){
            oldBusNode.setInf(newName);
            //添加新的映射,并且删除久的映射
            nameToId.put(newName,oldBusNode.getId());
            nameToId.remove(oldName);
            return;
        }
        errorShower.noExistBusNode(oldName,newName);
    }

    /**
     * 查看当前公交线路是否有该节点
     * @param name
     * @return
     */
    public BusNode findNode(String name){
        int id=nameToId.getOrDefault(name,-1);
        if (id!=-1){
            return nodes.get(id);
        }
        return null;
    }

    /**
     * 找到某个公交节点所有相邻的节点
     * @param name
     * @return
     */
    public List<BusNode> findNodeOfAllAdjNodes(String name){
        BusNode busNode = findNode(name);
        if (busNode!=null){
           return busNode.getList();
        }
        errorShower.noExistBusNode(name);
        return null;
    }

    /**
     * 删除某个节点
     * @param name
     */
    public void deleteNode(String name){
        BusNode busNode = findNode(name);
        if (busNode!=null){
            int id =busNode.getId();
            nodes.remove(id);
            nameToId.remove(name);
            //因为删除了元素后，后面的元素的id（也就是下标）会往前移动一位
            moveIndexAhead(id);
            size--;
        }
        errorShower.noExistBusNode(name);
    }

    private void moveIndexAhead(long id){
        //将Map所有大于这个id的值减一
        for (Map.Entry<String,Integer> entry:nameToId.entrySet()){
            Integer nowId = entry.getValue();
            if (nowId>id){
                nameToId.put(entry.getKey(),entry.getValue()-1);
            }
        }
        for (int i=0;i<nodes.size();i++){
            nodes.get(i).setId(i);
        }
    }

    /**
     * 是否包含点（传入类版本）
     * @param wantToExamNodes
     * @return
     */
    public boolean isContainNode(BusNode... wantToExamNodes){
        for (BusNode node:wantToExamNodes){
            if (!nodes.contains(node)){
                return false;
            }
        }
        return true;
    }

    /**
     * 是否包含点（名字版本）
     * @param wantToExamNodesName
     * @return
     */
    public boolean isContainNode(String... wantToExamNodesName){
        for (String nodeName:wantToExamNodesName){
            BusNode node = findNode(nodeName);
            if (node==null){
                return false;
            }
        }
        return true;
    }
    public boolean isContainEdge(Edge edge){
        return edges.contains(edge);
    }
    /**
     * 站点连线（添加边）
     * @param name1
     * @param name2
     */
    public void linkBusNode(String name1,String name2){
        if (isContainNode(name1,name2)){
            edges.add(new Edge(name1,name2));
            BusNode node1 = findNode(name1);
            BusNode node2 = findNode(name2);
            //添加相邻节点
            node1.addAdj(node2);
            node2.addAdj(node1);
            return;
        }
        errorShower.noExistBusNode(name1,name2);
    }

    /**
     * 删除边
     * @param name1
     * @param name2
     */
    public void deleteEdge(String name1,String name2){
        if (isContainNode(name1,name2)){
            edges.remove(new Edge(name1,name2));
            BusNode node1 = findNode(name1);
            BusNode node2 = findNode(name2);
            //删除相邻节点
            node1.deleteAdj(node2);
            node2.deleteAdj(node1);
            return;
        }
        errorShower.noExistBusNode(name1,name2);
    }



    public void editEdge(String oldName1,String oldName2,String newName1,String newName2){
        //先判断四个点是否存在
        if (isContainNode(oldName1,oldName2,newName1,newName2)){
            //删除原来的边
            deleteEdge(oldName1,oldName2);
            //添加新的边
            linkBusNode(newName1,newName2);
        }
        errorShower.noExistEdge();
    }


    public Edge getEdge(Edge wantToGetEdge){
        for (Edge edge:edges){
            if (wantToGetEdge.equals(edge)){
                return edge;
            }
        }
        return null;
    }
    public List<BusNode> getNodes() {
        return nodes;
    }

    public Set<Edge> getEdges() {
        return edges;
    }


    /**
     * 边类
     */
     public static class Edge{
        Integer id1;
        Integer id2;
        Integer lines=0;
        public Edge(Integer id1, Integer id2){
            this.id1=id1;
            this.id2=id2;
        }
        public Edge(String name1, String name2){
            id1=nameToId.get(name1);
            id2=nameToId.get(name2);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof Edge)) {
                return false;
            }
            Edge edge = (Edge) o;
            return (Objects.equals(id1, edge.id1) &&
                    Objects.equals(id2, edge.id2))
                    ||
                    (Objects.equals(id1, edge.id2) &&
                    Objects.equals(id2, edge.id1));
        }

        @Override
        public int hashCode() {
            if (id1<id2){
                return Objects.hash(id1,id2);
            }
            return Objects.hash(id2, id1);
        }

        @Override
        public String toString() {
            return "Edge{" +
                    "id1=" + id1 +
                    ", id2=" + id2 +
                    ", lines=" + lines +
                    '}';
        } 
    }

}
