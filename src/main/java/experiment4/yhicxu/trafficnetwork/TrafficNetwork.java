package experiment4.yhicxu.trafficnetwork;

import experiment4.yhicxu.bean.Road;
import experiment4.yhicxu.bean.Route;
import experiment4.yhicxu.bean.Site;
import util.graphutil.Graph;
import util.graphutil.GraphEdge;
import util.graphutil.GraphNode;
import util.graphutil.GraphRoute;

import java.util.*;

public class TrafficNetwork implements Graph {

    private int type; // 类型，0为无向图
    private int nodeId = 1;
    private List<GraphRoute> routes;
    private Map<String, Site> sites; // 节点集合，键为节点名称
    private Map<String, Road> roads; // 道路集合，键为道路两端两个节点中小的id+空格+大的id组成的字符串

    public TrafficNetwork(int type) {
        this.type = type;
        routes = new LinkedList<>();
        sites = new HashMap<>();
        roads = new HashMap<>();
    }

    // 添加站点
    public boolean addSite(String name) {

        // 判断站点是否存在
        if (sites.get(name) == null) {

            // 不存在则创建节点
            Site site = new Site(nodeId++, name);
            sites.put(name, site);
            return true;
        } else {
            return false;
        }
    }

    // 重命名站点
    public boolean renameSite(String oldName, String newName) {
        Site site = sites.get(oldName);
        if (site != null && sites.get(newName) == null) {
            site.setName(newName);
            sites.put(newName, site);
            sites.remove(oldName);
            return true;
        } else {
            return false;
        }
    }

    // 删除站点(未完成)
    public boolean removeSite(String name) {

        Site site = sites.get(name);
        if (site != null) {
            Set<Site> adjacentSites = site.getAdjacentSites();
            for (Site s : adjacentSites) {
                if (getRoadBySite(s, site).getRouteNum() != 0) {
                    return false;
                }
            }

            // 删除所有关联的边
            for (Site s : adjacentSites) {
                removeRoad(s.getName(), site.getName());
            }

            // 删除该站点
            sites.remove(site.getName());
            return true;
        } else {
            return false;
        }
    }

    // 添加道路
    public boolean addRoad(String site1Name, String site2Name, int length) {

        Site site1 = sites.get(site1Name);
        Site site2 = sites.get(site2Name);

        // 判断车站是否存在
        if (site1 != null && site2 != null) {

            // 判断路是否已存在
            String roadKey = getRoadKeyBySite(site1, site2);
            Road road = getRoadByRoadKey(roadKey);
            if (road != null) {
                return false;
            }

            // 如果不存在则创建路
            road = new Road(site1, site2, length);
            road.getFirst().addAdjacentSite(road.getSecond());
            road.getSecond().addAdjacentSite(road.getFirst());
            roads.put(roadKey, road);
            return true;
        } else {
            return false;
        }
    }

    // 修改道路长度
    public boolean modifyRoadLength(String site1Name, String site2Name, int newLength) {

        Site site1 = sites.get(site1Name);
        Site site2 = sites.get(site2Name);

        if (site1 != null && site2 != null) {

            Road road = getRoadBySiteId(site1.getId(), site2.getId());

            // 如果道路存在则修改
            if (road != null) {
                road.setLength(newLength);
                return true;
            }
        }
        return false;
    }

    // 删除道路
    public boolean removeRoad(String site1Name, String site2Name) {

        Site site1 = sites.get(site1Name);
        Site site2 = sites.get(site2Name);

        if (site1 != null && site2 != null) {
            String roadKey = getRoadKeyById(site1.getId(), site2.getId());
            Road road = getRoadByRoadKey(roadKey);

            // 如果道路上没有公交线路则可以移除
            if (road.getRouteNum() == 0) {

                // 断开左右站点的联系
                road.getFirst().removeAdjacentSite(road.getSecond());
                road.getSecond().removeAdjacentSite(road.getFirst());

                //  删除道路
                roads.remove(roadKey);
                return true;
            }
        }
        return false;
    }

    // 添加公交线路
    public boolean addRoute(String name, String[] sNames) {

        // 判断所有站点是否都存在和是否有重复站点
        Set<String> set = new HashSet<>();
        for (String sName : sNames) {
            if (sites.get(sName) == null) {
                return false;
            }
            set.add(sName);
        }
        if (set.size() != sNames.length) {
            return false;
        }

        // 判断相邻节点间是否有道路
        Site now = sites.get(sNames[0]);
        for (int i = 1; i < sNames.length; i++) {
            Site s = sites.get(sNames[i]);
            if (getRoadBySiteId(now.getId(), s.getId()) != null) {
                now = s;
            } else {
                return false;
            }
        }

        // 如果一切正常，则创建公交线路
        List<GraphNode> list = new ArrayList<>(sNames.length);
        for (int i = 0; i < sNames.length; i++) {
            list.add(i, sites.get(sNames[i]));
        }

        // 给所有的道路的线路都加一
        now = sites.get(sNames[0]);
        for (int i = 1; i < sNames.length; i++) {
            Site s = sites.get(sNames[i]);
            getRoadBySite(now, s).addRoute();
            now = s;
        }
        Route r = new Route(name, list);

        // 判断公交线路是否有重复的
        for (GraphRoute t : routes) {
            if (t.equals(r)) {
                return false;
            }
        }

        // 没有重复则成功创建
        routes.add(r);
        return true;
    }


    @Override
    public int getType() {
        return type;
    }

    @Override
    public List<GraphNode> getNodes() {
        List<GraphNode> res = new LinkedList<>();
        for (String name : sites.keySet()) {
            res.add(sites.get(name));
        }
        return res;
    }

    @Override
    public List<GraphEdge> getEdges() {
        List<GraphEdge> res = new LinkedList<>();
        for (String name : roads.keySet()) {
            res.add(roads.get(name));
        }
        return res;
    }

    @Override
    public List<GraphRoute> getRoutes() {
        return routes;
    }

    // 通过站点id获取道路的键
    private String getRoadKeyById(int id1, int id2) {
        return Math.min(id1, id2) + " " + Math.max(id1, id2);
    }

    // 通过站点获取道路的键
    private String getRoadKeyBySite(Site site1, Site site2) {
        return getRoadKeyById(site1.getId(), site2.getId());
    }

    // 通过站点获取道路
    private Road getRoadBySite(Site site1, Site site2) {
        return getRoadBySiteId(site1.getId(), site2.getId());
    }

    // 通过站点id获取道路
    private Road getRoadBySiteId(int id1, int id2) {
        return roads.get(getRoadKeyById(id1, id2));
    }

    // 通过道路的键获取道路
    private Road getRoadByRoadKey(String roadKey) {
        return roads.get(roadKey);
    }
}
