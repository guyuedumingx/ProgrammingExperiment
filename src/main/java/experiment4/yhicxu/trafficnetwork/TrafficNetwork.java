package experiment4.yhicxu.trafficnetwork;

import experiment4.yhicxu.bean.Road;
import experiment4.yhicxu.bean.Route;
import experiment4.yhicxu.bean.Site;
import util.graphutil.*;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class TrafficNetwork implements Graph {

    private int type; // 类型，0为无向图
    private int nodeId = 1;
    private Map<String, Site> sites; // 节点集合，键为节点名称
    private Map<String, Road> roads; // 道路集合，键为道路两端两个节点中小的id+空格+大的id组成的字符串
    private Map<String, Route> routes; // 线路集合，键为线路名

    // 通过图的数据构造
    public TrafficNetwork(GraphData graphData) {
        this(graphData.getType());
        for (String siteName : graphData.getNodes()) {
            addSite(siteName);
        }
        for (List<String> list : graphData.getEdges()) {
            addRoad(list.get(0), list.get(1), 0);
        }
        for (List<String> list : graphData.getRoutes()) {
            String name = list.get(0);
            int len = list.size();
            String[] strArr = new String[len - 1];
            for (int i = 0; i < strArr.length; i++) {
                strArr[i] = list.get(i + 1);
            }
            addRoute(name, strArr);
        }
    }

    // 构造
    public TrafficNetwork(int type) {
        this.type = type;
        routes = new ConcurrentHashMap<>();
        sites = new ConcurrentHashMap<>();
        roads = new ConcurrentHashMap<>();
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
            Site[] siteSet = adjacentSites.toArray(new Site[0]);

            // 删除所有关联的边
            for (Site s : siteSet) {
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

        // 判断名称是否重复
        if (routes.get(name) != null) {
            return false;
        }

        // 判断线路是否合理
        if (!judgeRoute(sNames)) {
            return false;
        }

        // 如果一切正常，则创建公交线路
        Route r = createRoute(name, sNames);

        // 判断公交线路是否无重复
        if (!judgeRouteRepeat(r)) {
            return false;
        }

        // 维护道路上的线路
        allRoadAdd(sNames);

        // 成功创建
        routes.put(name, r);
        return true;
    }

    // 修改线路名
    public boolean renameRoute(String oldName, String newName) {
        Route route = routes.get(oldName);
        if (route != null) {
            route.setName(newName);
            routes.remove(oldName);
            routes.put(newName, route);
            return true;
        } else {
            return false;
        }
    }

    // 修改线路
    public boolean modifyRoute(String name, String[] sNames) {

        // 判断名称是否存在
        Route route = routes.get(name);
        if (route == null) {
            return false;
        }

        // 判断线路是否合理
        if (!judgeRoute(sNames)) {
            return false;
        }

        // 创建新线路
        Route r = createRoute(name, sNames);

        // 判断新线路是否无重复
        if (!judgeRouteRepeat(r)) {
            return false;
        }

        // 维护道路上的线路
        allRoadSub(route);
        allRoadAdd(sNames);

        // 成功修改
        routes.put(name, r);
        return true;
    }

    // 删除线路
    public boolean removeRoute(String name) {
        // 判断名称是否存在
        Route route = routes.get(name);
        if (route == null) {
            return false;
        }

        // 维护道路上的线路
        allRoadSub(route);

        // 成功删除
        routes.remove(name);
        return true;
    }

    // 查询某个节点的信息
    public Site querySite(String name) {
        return sites.get(name);
    }

    // 查询某个节点的邻居节点
    public Set<Site> queryAdjacentSites(String name) {
        Site site = sites.get(name);
        if (site == null) {
            throw new RuntimeException("该节点不存在！");
        } else {
            return site.getAdjacentSites();
        }
    }


    // 查询某个站点上所有线路
    public Set<Route> queryRouteAtSite(Site site) {
        Set<Route> set = new HashSet<>();
        for (String routeName : routes.keySet()) {
            Route route = routes.get(routeName);
            for (GraphNode graphNode : route.getNodes()) {
                if (graphNode.equals(site)) {
                    set.add(route);
                    break;
                }
            }
        }
        return set;
    }

    // 查询某条线路上的所有线路
    private Set<Route> queryRouteAtRoute(Route route) {
        Set<Route> res = new HashSet<>();
        for (GraphNode site : route.getNodes()) {
            Set<Route> set = queryRouteAtSite((Site) site);
            res.addAll(set);
        }
        return res;
    }

    // 判断站点是否在线路中
    private boolean judgeWhetherSiteIsInRoute(Site site, Route route) {
        for (GraphNode node : route.getNodes()) {
            if (site.equals(node)) {
                return true;
            }
        }
        return false;
    }

    // 查询可达线路
    public Map<List<Route>, Integer> findFeasibleRoutes(String startName, String endName) {
        Site start = sites.get(startName);
        Site end = sites.get(endName);
        if (start == null) {
            throw new RuntimeException("起点不存在！");
        }
        if (end == null) {
            throw new RuntimeException("终点不存在！");
        }
        Map<List<Route>, Integer> res = new HashMap<>();
        findFeasibleRoutesDFS(res, new HashSet<>(), new HashSet<>(), new LinkedList<>(), start, end);
        return res;
    }

    // 深度优先搜索寻找可达路线
    private void findFeasibleRoutesDFS(Map<List<Route>, Integer> res, Set<String> routeStatus, Set<String> siteStatus, LinkedList<Route> list, Site start, Site end) {
        if (list.size() == 0) {
            for (Route route : queryRouteAtSite(start)) {

                // 如果该线路经过终点则记录
                if (judgeWhetherSiteIsInRoute(end, route)) {
                    List<Route> l = new ArrayList<>(list.size() + 1);
                    l.addAll(list);
                    l.add(route);
                    res.put(l, calculateSiteNumber(l, start, end));
                }

                // 递归
                siteStatus.add(end.getName());
                siteStatus.add(start.getName());
                routeStatus.add(route.getName());
                list.offerLast(route);
                findFeasibleRoutesDFS(res, routeStatus, siteStatus, list, start, end);
                list.pollLast();
                routeStatus.remove(route.getName());
                siteStatus.remove(start.getName());
                siteStatus.remove(end.getName());
            }
        } else {
            for (Route route : queryRouteAtRoute(list.getLast())) {

                // 判断该线路是否走过
                if (routeStatus.contains(route.getName())) {
                    continue;
                }

                String routeName = route.getName();
                String siteName;

                Site site = findRouteIntersection(route, list.getLast(), siteStatus);
                if (site == null) {
                    // debug
                    // System.out.println("!!!" + route.getName() + "!!!");
                    // System.out.println("!!!" + list.getLast().getName() + "!!!");
                    // System.out.println("!!!" + Arrays.toString(siteStatus.toArray(new String[0])));
                    continue;
                }
                siteName = site.getName();

//                String siteName = findRouteIntersection(route, list.getLast(), siteStatus).getName();

                // 判断交点是否已走过
                if (siteStatus.contains(siteName)) {
                    continue;
                }

                // 如果交点为终点则跳过
//                if (site.equals(end)) {
//                    continue;
//                }

                siteStatus.add(siteName);
                routeStatus.add(routeName);

                // 如果该线路经过终点则记录
                if (judgeWhetherSiteIsInRoute(end, route)) {
                    List<Route> l = new ArrayList<>(list.size());
                    l.addAll(list);
                    l.add(route);
                    res.put(l, calculateSiteNumber(l, start, end));
                }

                // 递归
                list.offerLast(route);
                findFeasibleRoutesDFS(res, routeStatus, siteStatus, list, start, end);
                list.pollLast();
                routeStatus.remove(routeName);
                siteStatus.remove(siteName);
            }
        }

    }

    // 寻找两个线路的交点
    private Site findRouteIntersection(Route route1, Route route2, Set<String> siteStatus) {
        for (GraphNode node1 : route1.getNodes()) {
            for (GraphNode node2 : route2.getNodes()) {
                if (node1.equals(node2)) {
                    if (siteStatus.contains(node1.getName())) {
                        continue;
                    }
                    return (Site) node1;
                }
            }
        }
        return null;
    }

    // 计算方案的总站数
    private int calculateSiteNumber(List<Route> routeList, Site start, Site end) {
        int len = routeList.size();
        List<Site> siteList = findTransferSite(routeList, start, end);
        int res = 0;
        if (len <= 1) {
            res += routeList.get(0).calculateDistance(start, end);
        } else {
            res += routeList.get(0).calculateDistance(start, siteList.get(0));
            for (int i = 1; i < len - 1; i++) {
                res += routeList.get(i).calculateDistance(siteList.get(i - 1), siteList.get(i));
            }
            res += routeList.get(len - 1).calculateDistance(siteList.get(len - 2), end);
        }
        return res;
    }

    // 找出所有方案的中转站
    public Map<List<Route>, List<Site>> findAllTransferSite(Set<List<Route>> set, String startName, String endName) {
        Map<List<Route>, List<Site>> res = new HashMap<>();
        for (List<Route> list : set) {
            res.put(list, findTransferSite(list, sites.get(startName), sites.get(endName)));
        }
        return res;
    }

    // 找出方案中的中转站
    public List<Site> findTransferSite(List<Route> routeList, Site start, Site end) {
        Set<String> siteStatus = new HashSet<>();
        siteStatus.add(start.getName());
        siteStatus.add(end.getName());
        int len = routeList.size();
        List<Site> res = new ArrayList<>(len - 1);
        for (int i = 1; i < len; i++) {
            Site site = findRouteIntersection(routeList.get(i - 1), routeList.get(i), siteStatus);
            siteStatus.add(site.getName());
            res.add(site);
        }
        return res;
    }

    // 判断线路是否合理
    private boolean judgeRoute(String[] sNames) {

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

        // 一切正常返回true
        return true;
    }

    // 创建一个线路对象
    private Route createRoute(String name, String[] sNames) {
        List<GraphNode> list = new ArrayList<>(sNames.length);
        for (int i = 0; i < sNames.length; i++) {
            list.add(i, sites.get(sNames[i]));
        }
        return new Route(name, list);
    }

    // 判断线路是否无重复
    private boolean judgeRouteRepeat(Route r) {
        for (String t : routes.keySet()) {
            if (routes.get(t).equals(r)) {
                return false;
            }
        }
        return true;
    }

    // 所有道路上的线路加一
    private void allRoadAdd(String[] sNames) {
        Site now = sites.get(sNames[0]);
        for (int i = 1; i < sNames.length; i++) {
            Site s = sites.get(sNames[i]);
            getRoadBySite(now, s).addRoute();
            now = s;
        }
    }

    // 所有道路上的线路减一
    private void allRoadSub(Route route) {
        List<GraphNode> nodes = route.getNodes();
        Site now = (Site) nodes.get(0);
        for (int i = 1; i < nodes.size(); i++) {
            Site s = (Site) nodes.get(i);
            getRoadBySite(now, s).subRoute();
            now = s;
        }
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
        List<GraphRoute> res = new LinkedList<>();
        for (String name : routes.keySet()) {
            res.add(routes.get(name));
        }
        return res;
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

    // 显示线路方案
    private void showRouteList(Map<List<Route>, Integer> map, Site start, Site end) {
        for (List<Route> routeList : map.keySet()) {
            int len = routeList.size();
            List<Site> siteList = findTransferSite(routeList, start, end);
            System.out.print(start.getName() + " ");
            for (int i = 0; i < len; i++) {
                System.out.print(routeList.get(i).getName() + " ");
                if (i != len - 1) {
                    System.out.print(siteList.get(i).getName() + " ");
                }
            }
            System.out.print(end.getName() + " ");
            System.out.println(map.get(routeList));
        }
    }

    // 获取站点
    public Site getSite(String name) {
        return sites.get(name);
    }
}
