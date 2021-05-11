package experiment4.yhicxu.managementsystem;

import experiment4.yhicxu.bean.Route;
import experiment4.yhicxu.bean.Site;
import util.graphutil.GraphEdge;
import util.graphutil.GraphNode;
import util.graphutil.GraphRoute;

import java.util.*;

public class Monitor {

    // 显示问候语
    public void showGreetings() {
        System.out.println("欢迎使用！");
    }

    public void showEnd() {
        System.out.println("谢谢使用！");
    }

    // 显示主菜单
    public void showMainMenu() {
        showLine();
        System.out.println("1. 创建公交网络");
        System.out.println("2. 操作公交网络");
        System.out.println("3. 保存公交网络");
        System.out.println("4. 导入公交网络");
        System.out.println("0. 退出管理系统");
        showLine();
    }

    // 显示公交网络菜单
    public void showTNMenu() {
        showLine();
        System.out.println("1. 操作站点");
        System.out.println("2. 操作道路");
        System.out.println("3. 操作线路");
        System.out.println("4. 查询信息");
        System.out.println("0. 返回主菜单");
        showLine();
    }

    // 显示站点操作菜单
    public void showSiteMenu() {
        showLine();
        System.out.println("1. 添加公交站点");
        System.out.println("2. 修改站点名称");
        System.out.println("3. 删除公交站点");
        System.out.println("0. 返回上一级");
        showLine();
    }

    // 显示道路操作菜单
    public void showRoadMenu() {
        showLine();
        System.out.println("1. 新建道路");
        System.out.println("2. 修改道路长度");
        System.out.println("3. 删除道路");
        System.out.println("0. 返回上一级");
        showLine();
    }

    // 显示线路操作菜单
    public void showRouteMenu() {
        showLine();
        System.out.println("1. 新增公交线路");
        System.out.println("2. 修改公交线路名称");
        System.out.println("3. 修改公交线路");
        System.out.println("4. 删除公交线路");
        System.out.println("0. 返回上一级");
        showLine();
    }

    // 显示查询信息菜单
    public void showQueryMenu() {
        showLine();
        System.out.println("1. 查看站点信息");
        System.out.println("2. 查看站点的相邻节点");
        System.out.println("3. 查看所有站点");
        System.out.println("4. 查看所有道路");
        System.out.println("5. 查看所有线路");
        System.out.println("6. 查询经过站点的线路");
        System.out.println("7. 查询行程的所有线路方案");
        System.out.println("0. 返回上一级");
        showLine();
    }

    // 显示提示信息
    public void showTips(String tips) {
        System.out.println(tips);
    }

    // 显示提示信息
    public void showTips(String tips, boolean type) {
        if (type) {
            System.out.println(tips);
        } else {
            System.out.print(tips);
        }
    }

    // 显示站点信息
    public void showSiteMessage(Site site) {
        if (site != null) {
            showLine();
            System.out.println("站点ID：" + site.getId());
            System.out.println("站点名称：" + site.getName());
            showLine();
        } else {
            System.out.println("站点不存在！");
        }
    }

    // 显示站点的所有相邻站点
    public void showSiteAdjacentSites(Site site) {
        if (site != null) {
            showLine();
            if (site.getAdjacentSites().size() == 0) {
                System.out.println("站点" + site.getName() + "没有相邻站点！");
            } else {
                System.out.println(site.getName() + "的所有相邻站点：");
                int count = 0;
                for (Site s : site.getAdjacentSites()) {
                    System.out.print(s.getName() + "\t");
                    count++;
                    if (count == 5) {
                        System.out.println();
                        count = 0;
                    }
                }
                if (count != 0) {
                    System.out.println();
                }
            }
            showLine();
        } else {
            System.out.println("节点不存在！");
        }
    }

    // 显示所有站点
    public void showAllSite(List<GraphNode> list, String tips) {
        showLine();
        if (list.size() == 0) {
            System.out.println("当前没有站点！");
        } else {
            System.out.println(tips);
            int count = 0;
            for (GraphNode s : list) {
                System.out.print(s.getName() + "\t");
                count++;
                if (count == 5) {
                    System.out.println();
                    count = 0;
                }
            }
            if (count != 0) {
                System.out.println();
            }
            showLine();
        }
    }

    //  显示所有道路
    public void showAllRoad(List<GraphEdge> edges) {
        showLine();
        if (edges.size() == 0) {
            System.out.println("当前没有道路！");
        } else {
            System.out.println("所有道路如下：");
            int count = 0;
            for (GraphEdge e : edges) {
                System.out.print(e.getFirst().getName() + "---" + e.getSecond().getName() + "\t");
                count++;
                if (count == 5) {
                    System.out.println();
                    count = 0;
                }
            }
            if (count != 0) {
                System.out.println();
            }
            showLine();
        }
    }

    // 显示所有线路
    public void showAllRoute(List<GraphRoute> routes) {
        showLine();
        if (routes.size() == 0) {
            System.out.println("当前没有线路！");
        } else {
            System.out.println("所有线路如下：");
            for (GraphRoute r : routes) {
                System.out.print(r.getName() + ":\t");
                List<GraphNode> list = r.getNodes();
                int len = list.size();
                for (int i = 0; i < len - 1; i++) {
                    System.out.print(list.get(i) + " -> ");
                }
                System.out.println(list.get(len - 1));
            }
            showLine();
        }
    }

    // 查询经过站点的所有线路
    public void showSiteAllRoute(Site site, Set<Route> routes) {
        showLine();
        if (site == null) {
            System.out.println("该站点不存在！");
        } else {
            if (routes.size() == 0) {
                System.out.println("没有经过该站点的线路！");
            } else {
                System.out.println("经过该站点的线路如下：");
                for (Route r : routes) {
                    System.out.print(r.getName() + ":\t");
                    List<GraphNode> list = r.getNodes();
                    int len = list.size();
                    for (int i = 0; i < len - 1; i++) {
                        System.out.print(list.get(i) + " -> ");
                    }
                    System.out.println(list.get(len - 1));
                }
            }
        }
        showLine();
    }

    // 显示所有方案
    public void showAllScheme(Map<List<Route>, Integer> feasibleRoutes, Map<List<Route>, List<Site>> allTransferSite, String startName, String endName) {
        showLine();
        if (feasibleRoutes.size() == 0) {
            System.out.println("无方案！");
        } else {
            List<List<Route>> routes = new ArrayList<>();
            routes.addAll(feasibleRoutes.keySet());
            Collections.sort(routes, new Comparator<List<Route>>() {
                @Override
                public int compare(List<Route> o1, List<Route> o2) {
                    return feasibleRoutes.get(o1) > feasibleRoutes.get(o2) ? 1 : -1;
                }
            });
            System.out.println("所有方案如下：");
            for (List<Route> routeList : routes) {
                int len = routeList.size();
                List<Site> siteList = allTransferSite.get(routeList);
                System.out.print(startName + "(起点) ");
                for (int i = 0; i < len; i++) {
                    System.out.print("--" + routeList.get(i).getName() + "--> ");
                    if (i != len - 1) {
                        System.out.print(siteList.get(i).getName() + " ");
                    }
                }
                System.out.print(endName + "(终点) ");
                System.out.println("共" + feasibleRoutes.get(routeList) + "站");
            }
        }
        showLine();
    }

    // 显示分割线
    private void showLine() {
        System.out.println("===================");
    }

}
