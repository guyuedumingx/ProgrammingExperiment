package experiment4.yhicxu.managementsystem;

import experiment4.yhicxu.bean.Route;
import experiment4.yhicxu.bean.Site;
import experiment4.yhicxu.trafficnetwork.TrafficNetwork;
import util.graphutil.GraphNode;
import util.graphutil.GraphUtil;

import java.util.*;

public class Controller {
    private InputDevice inputDevice;
    private Monitor monitor;
    private TrafficNetwork tn;

    public Controller(InputDevice inputDevice, Monitor monitor) {
        this.inputDevice = inputDevice;
        this.monitor = monitor;
    }

    // 读入主菜单选项
    public int inputMainMenuOption() {
        return inputDevice.readRangeInt("请输入：", 0, 4);
    }

    // 新建
    public void createTrafficNetwork() {
        if (tn != null) {
            cover();
        }
        tn = new TrafficNetwork(0);
        monitor.showTips("创建成功！");
    }

    // 覆盖
    private void cover() {
        String option = inputDevice.readString("未保存的数据将被覆盖掉，是否保存？(Y/N)");
        if (option.equals("Y") || option.equals("y")) {
            save();
        }
    }

    // 操作公交网络
    public void operationTN() {
        while (true) {
            monitor.showTNMenu();
            int type = inputDevice.readRangeInt("请输入：", 0, 4);
            if (type == 1) {
                operationSite();
            } else if (type == 2) {
                operationRoad();
            } else if (type == 3) {
                operationRoute();
            } else if (type == 4) {
                queryMessage();
            } else {
                break;
            }
        }
    }

    // 操作站点
    private void operationSite() {
        while (true) {
            monitor.showSiteMenu();
            int type = inputDevice.readRangeInt("请输入：", 0, 3);
            if (type == 1) {
                String name = inputDevice.readString("请输入站点名：");
                if (tn.addSite(name)) {
                    monitor.showTips("添加成功");
                } else {
                    monitor.showTips("添加失败");
                }
            } else if (type == 2) {
                String oldName = inputDevice.readString("请输入原站点名：");
                String newName = inputDevice.readString("请输入新站点名：");
                if (tn.renameSite(oldName, newName)) {
                    monitor.showTips("修改成功");
                } else {
                    monitor.showTips("修改失败");
                }
            } else if (type == 3) {
                String name = inputDevice.readString("请输入站点名：");
                if (tn.removeSite(name)) {
                    monitor.showTips("删除成功");
                } else {
                    monitor.showTips("删除失败");
                }
            } else {
                break;
            }
        }
    }

    // 操作道路
    private void operationRoad() {
        while (true) {
            monitor.showRoadMenu();
            int type = inputDevice.readRangeInt("请输入：", 0, 3);
            if (type == 1) {
                String site1Name = inputDevice.readString("请输入第一个站点名：");
                String site2Name = inputDevice.readString("请输入第二个站点名：");
                int length = inputDevice.readInt("请输入道路长度：");
                if (tn.addRoad(site1Name, site2Name, length)) {
                    monitor.showTips("新建成功");
                } else {
                    monitor.showTips("新建失败");
                }
            } else if (type == 2) {
                String site1Name = inputDevice.readString("请输入第一个站点名：");
                String site2Name = inputDevice.readString("请输入第二个站点名：");
                int length = inputDevice.readInt("请输入道路长度：");
                if (tn.modifyRoadLength(site1Name, site2Name, length)) {
                    monitor.showTips("修改成功");
                } else {
                    monitor.showTips("修改失败");
                }
            } else if (type == 3) {
                String site1Name = inputDevice.readString("请输入第一个站点名：");
                String site2Name = inputDevice.readString("请输入第二个站点名：");
                if (tn.removeRoad(site1Name, site2Name)) {
                    monitor.showTips("删除成功");
                } else {
                    monitor.showTips("删除失败");
                }
            } else {
                break;
            }
        }
    }

    // 操作线路
    private void operationRoute() {
        while (true) {
            monitor.showRouteMenu();
            int type = inputDevice.readRangeInt("请输入：", 0, 4);
            if (type == 1) {
                String routeName = inputDevice.readString("请输入线路名称：");
                String[] sites = inputRoute();
                if (tn.addRoute(routeName, sites)) {
                    monitor.showTips("新增成功");
                } else {
                    monitor.showTips("新增失败");
                }
            } else if (type == 2) {
                String oldName = inputDevice.readString("请输入原线路名：");
                String newName = inputDevice.readString("请输入新线路名：");
                if (tn.renameRoute(oldName, newName)) {
                    monitor.showTips("修改成功");
                } else {
                    monitor.showTips("修改失败");
                }
            } else if (type == 3) {
                String name = inputDevice.readString("请输入线路名：");
                String[] sites = inputRoute();
                if (tn.modifyRoute(name, sites)) {
                    monitor.showTips("修改成功");
                } else {
                    monitor.showTips("修改失败");
                }
            } else if (type == 4) {
                String name = inputDevice.readString("请输入线路名：");
                if (tn.removeRoute(name)) {
                    monitor.showTips("删除成功");
                } else {
                    monitor.showTips("删除失败");
                }
            } else {
                break;
            }
        }
    }

    // 显示信息
    private void queryMessage() {
        while (true) {
            monitor.showQueryMenu();
            int type = inputDevice.readRangeInt("请输入：", 0, 7);
            if (type == 1) {
                String name = inputDevice.readString("请输入站点名称：");
                monitor.showSiteMessage(tn.getSite(name));
            } else if (type == 2) {
                String name = inputDevice.readString("请输入站点名称：");
                monitor.showSiteAdjacentSites(tn.getSite(name));
            } else if (type == 3) {
                monitor.showAllSite(tn.getNodes(), "所有站点如下：");
            } else if (type == 4) {
                monitor.showAllRoad(tn.getEdges());
            } else if (type == 5) {
                monitor.showAllRoute(tn.getRoutes());
            } else if (type == 6) {
                String name = inputDevice.readString("请输入站点名称：");
                Site site = tn.getSite(name);
                if (site == null) {
                    monitor.showTips("站点不存在！");
                } else {
                    monitor.showSiteAllRoute(site, tn.queryRouteAtSite(site));
                }
            } else if (type == 7) {
                String startName = inputDevice.readString("请输入起点站名称：");
                String endName = inputDevice.readString("请输入终点站名称：");
                try {
                    Map<List<Route>, Integer> feasibleRoutes = tn.findFeasibleRoutes(startName, endName);
                    Map<List<Route>, List<Site>> allTransferSite = tn.findAllTransferSite(feasibleRoutes.keySet(), startName, endName);
                    monitor.showAllScheme(feasibleRoutes, allTransferSite, startName, endName);
                } catch (Exception e) {
                    monitor.showTips(e.getMessage());
                }
            } else {
                break;
            }
        }
    }

    // 导入
    public void importTN() {
        if (tn != null) {
            cover();
        }
        String path = inputDevice.readString("请输入文件路径：");
        try {
            tn = new TrafficNetwork(GraphUtil.importGraph(path));
            monitor.showTips("导入成功");
        } catch (Exception e) {
            monitor.showTips("导入失败");
        }
    }

    // 保存
    public void save() {
        String path = inputDevice.readString("请输入要保存的文件路径：");
        try {
            GraphUtil.exportGraph(tn, path);
            monitor.showTips("保存成功！");
        } catch (Exception e) {
            monitor.showTips("保存失败！");
        }
    }

    // 输入线路
    private String[] inputRoute() {
        Set<String> status = new HashSet<>();
        LinkedList<String> sites = new LinkedList<>();
        Site now;

        List<GraphNode> list = tn.getNodes();
        int len = list.size();
        if (len != 0) {
            monitor.showTips("当前可选择的站点：");
            for (int i = 1; i <= len; i++) {
                monitor.showTips(i + ". " + list.get(i - 1).getName() + "    ", false);
            }
            System.out.println();
        }
        while (true) {
            int index;
            if (list.size() == 0) {
                index = inputDevice.readRangeInt("无可选择站点，输入0结束输入：", 0, len);
            } else {
                index = inputDevice.readRangeInt("请输入一个站点编号，输入0结束输入：", 0, len);
            }
            if (index != 0) {
                now = (Site) list.get(index - 1);
                status.add(now.getName());
                sites.add(now.getName());
                Set<Site> adjacentSites = now.getAdjacentSites();
                list.clear();
                for (Site site : adjacentSites) {
                    if (!status.contains(site.getName())) {
                        list.add(site);
                    }
                }
                len = list.size();
                if (len != 0) {
                    monitor.showTips("当前可选择的站点：");
                    for (int i = 1; i <= len; i++) {
                        monitor.showTips(i + ". " + list.get(i - 1).getName() + "    ", false);
                    }
                    System.out.println();
                }
            } else {
                break;
            }
        }
        return sites.toArray(new String[0]);
    }
}
