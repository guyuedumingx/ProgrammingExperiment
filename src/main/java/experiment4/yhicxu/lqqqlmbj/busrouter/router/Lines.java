package experiment4.yhicxu.lqqqlmbj.busrouter.router;

import experiment4.yhicxu.bean.Route;
import experiment4.yhicxu.bean.Site;
import util.graphutil.GraphNode;

import java.util.*;

// 公交线路网，由多条线路组成
public class Lines {
    public static int MAXSIZE = 10; // 公交线路最大值
    public ALGraph g;
    public Line[] routes; // 存放公交线路的数组
    public int size; // 当前公交线路数量
    private Scanner scanner = new Scanner(System.in);

    public Lines() {
        this(null);
    }

    public Lines(ALGraph g) {
        this.routes = new Line[MAXSIZE];
        this.size = 0;
        this.g = g;
    }

    // 查找线路
    public int findLineNo(String lineNo) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (lineNo.equals(routes[i].lineNo)) {
                index = i;
                break;
            }
        }
        return index;
    }

    // 显示所有公交线路信息
    public void showLines() {
        System.out.println("所有公交线路信息：");
        for (int i = 0; i < size; i++) {
            System.out.print("   " + (i + 1) + "   ");
            routes[i].show();
        }
    }

    /**
     * 功能：添加一条新线路
     * 参数：
     * <p>
     * 返回值：
     * Line -- 线路
     */
    private Line createLine() {
        Line line = new Line(g);
        System.out.println("请输入新线路编号：");
        while (true) {
            String lineNo = scanner.next();
            if (findLineNo(lineNo) < 0) {
                line.lineNo = lineNo;
                break;
            }
            System.out.println("线路编号已存在，请重新输入新线路编号：");
        }
        int id;
        // 循环添加站点
        while (true) {
            if (line.size > 0) {
                // 当前站点的可加入线路的候选站点
                ArrayList<VNode> cands = line.candidateStation(line.vexs[line.size - 1].id);
                System.out.println("可选站点列表：");
                for (int i = 0; i < cands.size(); i++) {
                    System.out.print(cands.get(i).id + "\t");
                }
                System.out.println();
            }
            // 输入下一个站点，也可以输入-1:终止
            System.out.println("请输入站点编号(-1表示终止在线路中增加新站点)：");
            id = scanner.nextInt();
            if (id == -1) break; // 终止在线路中增加新站点
            boolean yn = line.addStation(line.size, id);
            if (!yn)
                System.out.println("添加站点失败！");
            else // 成功加入一个站点
                line.show();
        }
        return line;
    }

    /**
     * 功能：添加公交线路，线路站点数量不少于5个
     * 参数：
     * <p>
     * 返回值：
     * false -- 失败
     * true -- 成功
     */
    public boolean addLine() {
        if (size == MAXSIZE) return false;
        Line line = createLine();
//		if(line.size < 5) {
//			System.out.println("线路站点数少于5个！");
//			return false;
//		}
        routes[size++] = line;
        //line.show();
        return true;
    }

    /**
     * 功能：修改公交线路
     * 参数：
     * index -- 线路索引
     * oldId -- 旧站点Id
     * newId -- 新站点Id
     * 返回值：
     */
    public int modifyLine(int index, int oldId, int newId) {
        if (index < 0 || index >= size) return -1;
        if (routes[index].inLine(oldId) < 0) return -2;
        if (g.findNode(newId) < 0) return -3;
        if (routes[index].modifyStation(oldId, newId))
            return 1;
        else
            return 0;
    }

    /**
     * 功能：删除公交线路
     * 参数：
     * index -- 线路位置索引
     * 返回值：
     * false -- 失败
     * true -- 成功
     */
    public boolean removeLine(int index) {
        // 判断是否已经没有线路了
        if (size == 0) return false;
        // 判断线路索引是否合法
        if (index < 0 || index >= size) return false;
        // 将线路索引处之后的所有线路往前移一位
        for (int j = index; j < size - 1; j++)
            routes[j] = routes[j + 1];
        // 线路总数减一
        size--;
        return true;
    }

    /**
     * 功能：查询线路，查询从起始站点-->目的站点的线路
     * 参数：
     * <p>
     * 返回值：
     * false -- 失败
     * true -- 成功
     */
    public boolean queryLine(int begin, int end) {
        if (begin == end) return false; // 起始和目的站点时同一站点
        boolean yn = false;
        // 遍历所有线路
        for (int i = 0; i < size; i++) {
            Line line = routes[i];
            // 获取起点和终点在线路中的索引
            int beginIndex = line.inLine(begin);
            int endIndex = line.inLine(end);
            // 如果起点和终点都在此线路中
            if (beginIndex >= 0 && endIndex >= 0) {
                // 起始站点索引位置>目的站点索引位置，进行交换
                if (beginIndex > endIndex) {
                    int temp = beginIndex;
                    beginIndex = endIndex;
                    endIndex = temp;
                }
                // 输出从起始站点-->目的站点的线路
                System.out.println("公交线路为：");
                // 从起点开始遍历到终点
                for (int j = beginIndex; j <= endIndex; j++) {
                    // 输出节点的id
                    System.out.print(line.vexs[j].id);
                    if (j != endIndex)
                        System.out.print("-");
                }
                System.out.println();
                yn = true;
            }
        }
        // 返回结果
        return yn;
    }

    // 查询可达线路
    public Map<List<Line>, Integer> findFeasibleRoutes(int begin, int end) {
        int startIndex = g.findNode(begin);
        int endIndex = g.findNode(end);
        if (startIndex < 0) {
            throw new RuntimeException("起点不存在！");
        }
        if (endIndex < 0) {
            throw new RuntimeException("终点不存在！");
        }
        Map<List<Line>, Integer> res = new HashMap<>();
        findFeasibleRoutesDFS(res, new HashSet<>(), new HashSet<>(), new LinkedList<>(), startIndex, endIndex);
        return res;
    }

    // 深度优先搜索寻找可达路线
    private void findFeasibleRoutesDFS(Map<List<Line>, Integer> res, Set<String> routeStatus, Set<Integer> siteStatus, LinkedList<Line> list, int startIndex, int endIndex) {
        VNode start = g.vexs[startIndex];
        VNode end = g.vexs[endIndex];
        if (list.size() == 0) {
            for (Line route : queryRouteAtSite(startIndex)) {

                // 如果该线路经过终点则记录
                if (judgeWhetherSiteIsInRoute(endIndex, route)) {
                    List<Line> l = new ArrayList<>(list.size() + 1);
                    l.addAll(list);
                    l.add(route);
                    res.put(l, calculateSiteNumber(l, startIndex, endIndex));
                }

                // 递归
                siteStatus.add(g.findNode(end.id));
                siteStatus.add(g.findNode(start.id));
                routeStatus.add(route.lineNo);
                list.offerLast(route);
                findFeasibleRoutesDFS(res, routeStatus, siteStatus, list, startIndex, endIndex);
                list.pollLast();
                routeStatus.remove(route.lineNo);
                siteStatus.remove(g.findNode(start.id));
                siteStatus.remove(g.findNode(end.id));
            }
        } else {
            for (Line route : queryRouteAtRoute(list.getLast())) {

                // 判断该线路是否走过
                if (routeStatus.contains(route.lineNo)) {
                    continue;
                }

                String routeName = route.lineNo;
                int siteIndex;

                siteIndex = findRouteIntersection(route, list.getLast(), siteStatus);
                if (siteIndex < 0) {
                    // debug
                    // System.out.println("!!!" + route.getName() + "!!!");
                    // System.out.println("!!!" + list.getLast().getName() + "!!!");
                    // System.out.println("!!!" + Arrays.toString(siteStatus.toArray(new String[0])));
                    continue;
                }

//                String siteName = findRouteIntersection(route, list.getLast(), siteStatus).getName();

                // 判断交点是否已走过
                if (siteStatus.contains(siteIndex)) {
                    continue;
                }

                // 如果交点为终点则跳过
//                if (site.equals(end)) {
//                    continue;
//                }

                siteStatus.add(siteIndex);
                routeStatus.add(routeName);

                // 如果该线路经过终点则记录
                if (judgeWhetherSiteIsInRoute(endIndex, route)) {
                    List<Line> l = new ArrayList<>(list.size());
                    l.addAll(list);
                    l.add(route);
                    res.put(l, calculateSiteNumber(l, startIndex, endIndex));
                }

                // 递归
                list.offerLast(route);
                findFeasibleRoutesDFS(res, routeStatus, siteStatus, list, startIndex, endIndex);
                list.pollLast();
                routeStatus.remove(routeName);
                siteStatus.remove(siteIndex);
            }
        }

    }

    // 查询某个站点上所有线路
    public Set<Line> queryRouteAtSite(int siteIndex) {
        Set<Line> set = new HashSet<>();
        for (int routeIndex = 0; routeIndex < size; routeIndex++) {
            Line route = routes[routeIndex];
            for (int nodeIndex = 0; nodeIndex < route.size; nodeIndex++) {
                VNode node = route.vexs[nodeIndex];
                if (node.id == g.vexs[siteIndex].id) {
                    set.add(route);
                    break;
                }
            }
        }
        return set;
    }

    // 查询某条线路上的所有线路
    private Set<Line> queryRouteAtRoute(Line route) {
        Set<Line> res = new HashSet<>();
        for (int siteIndex = 0; siteIndex < route.size; siteIndex++) {
            VNode site = route.vexs[siteIndex];
            Set<Line> set = queryRouteAtSite(g.findNode(site.id));
            res.addAll(set);
        }
        return res;
    }

    // 判断站点是否在线路中
    private boolean judgeWhetherSiteIsInRoute(int siteIndex, Line route) {
        for (int nodeIndex = 0; nodeIndex < route.size; nodeIndex++) {
            VNode node = route.vexs[nodeIndex];
            if (node.id == g.vexs[siteIndex].id) {
                return true;
            }
        }
        return false;
    }

    // 计算方案的总站数
    private int calculateSiteNumber(List<Line> routeList, int startIndex, int endIndex) {
        int len = routeList.size();
        List<VNode> siteList = findTransferSite(routeList, startIndex, endIndex);
        VNode start = g.vexs[startIndex];
        VNode end = g.vexs[endIndex];
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

    // 找出方案中的中转站
    public List<VNode> findTransferSite(List<Line> routeList, int startIndex, int endIndex) {
        Set<Integer> siteStatus = new HashSet<>();
        siteStatus.add(startIndex);
        siteStatus.add(endIndex);
        int len = routeList.size();
        List<VNode> res = new ArrayList<>(len - 1);
        for (int i = 1; i < len; i++) {
            int siteIndex = findRouteIntersection(routeList.get(i - 1), routeList.get(i), siteStatus);
            siteStatus.add(siteIndex);
            res.add(g.vexs[siteIndex]);
        }
        return res;
    }

    // 找出所有方案的中转站
    public Map<List<Line>, List<VNode>> findAllTransferSite(Set<List<Line>> set, int startIndex, int endIndex) {
        Map<List<Line>, List<VNode>> res = new HashMap<>();
        for (List<Line> list : set) {
            res.put(list, findTransferSite(list, startIndex, endIndex));
        }
        return res;
    }

    // 寻找两个线路的交点
    private int findRouteIntersection(Line route1, Line route2, Set<Integer> siteStatus) {
        for (int nodeIndex1 = 0; nodeIndex1 < route1.size; nodeIndex1++) {
            VNode node1 = route1.vexs[nodeIndex1];
            for (int nodeIndex2 = 0; nodeIndex2 < route2.size; nodeIndex2++) {
                VNode node2 = route2.vexs[nodeIndex2];
                if (node1.id == node2.id) {
                    int nodeIndex = g.findNode(node1.id);
                    if (siteStatus.contains(nodeIndex)) {
                        continue;
                    }
                    return nodeIndex;
                }
            }
        }
        return -1;
    }

    // 显示所有方案
    public void showAllScheme(Map<List<Line>, Integer> feasibleRoutes, Map<List<Line>, List<VNode>> allTransferSite, int startIndex, int endIndex) {
        System.out.println();
        if (feasibleRoutes.size() == 0) {
            System.out.println("无方案！");
        } else {
            List<List<Line>> routes = new ArrayList<>();
            routes.addAll(feasibleRoutes.keySet());
            Collections.sort(routes, new Comparator<List<Line>>() {
                @Override
                public int compare(List<Line> o1, List<Line> o2) {
                    if (feasibleRoutes.get(o1) == feasibleRoutes.get(o2)) {
                        return o1.size() > o2.size() ? 1 : -1;
                    }
                    return feasibleRoutes.get(o1) > feasibleRoutes.get(o2) ? 1 : -1;
                }
            });
            System.out.println("所有方案如下：");
            for (List<Line> routeList : routes) {
                int len = routeList.size();
                List<VNode> siteList = allTransferSite.get(routeList);
                System.out.print(g.vexs[startIndex].info + "(起点) ");
                for (int i = 0; i < len; i++) {
                    System.out.print("--" + routeList.get(i).lineNo + "--> ");
                    if (i != len - 1) {
                        System.out.print(siteList.get(i).info + " ");
                    }
                }
                System.out.print(g.vexs[endIndex].info + "(终点) ");
                System.out.println("共" + feasibleRoutes.get(routeList) + "站");
            }
        }
        System.out.println();
    }

    // 添加公交线路
    public void addLine(Line line) {
        routes[size++] = line;
    }
}