package experiment4.yhicxu.lqqqlmbj.busrouter.router;

import util.graphutil.*;

import java.util.LinkedList;
import java.util.List;

// 用连接表描述的图/网络
public class ALGraph implements Graph {
    private static int MAXSIZE = 20; // 最大顶点数
    public VNode[] vexs; // 保存图中顶点
    public int vexNum; // 顶点数量
    public int edgeNum; // 边数量
    private Lines lines; // 公交线路网

    public ALGraph() {
        this.vexs = new VNode[MAXSIZE];
        this.vexNum = 0;
        this.edgeNum = 0;
    }

    public ALGraph(VNode[] vexs, int vexNum, int arcNum) {
        this.vexs = vexs;
        this.vexNum = vexNum;
        this.edgeNum = arcNum;
    }

    //返回顶点存放在vexs中索引位置
    public int findNode(int id) {
        int index = -1;
        for (int i = 0; i < vexNum; i++) {
            if (id == vexs[i].id) {
                index = i;
                break;
            }
        }
        return index;
    }

    /**
     * 功能：判别是否是有效相邻顶点
     * 参数：
     * g -- 图
     * u,v -- 相邻顶点id
     * 返回值：
     * false -- 不是相邻顶点
     * true -- 是相邻顶点
     */
    public boolean isAdjVertex(int u, int v) {
        int index = findNode(u); // 顶点u存放在vexs中索引位置
        if (index < 0) return false;
        boolean yn = false;
        ArcNode p = vexs[index].firstArc;
        // 遍历边表
        while (p != null) {
            if (v == vexs[p.adjVex].id) { // correct
                yn = true;
                break;
            }
            p = p.nextArc;
        }
        return yn;
    }

    /**
     * 功能：在图中增加节点
     * 参数：
     * id -- 顶点id
     * info -- 顶点信息
     * 返回值：
     * false -- 增加失败
     * true -- 增加成功
     */
    public boolean addNode(int id, String info) {
        if (findNode(id) >= 0) return false;
        VNode node = new VNode(id, info);
        vexs[vexNum++] = node;
        return true;
    }

    /**
     * 功能：在图中增加（无向）边
     * 参数：
     * u,v -- 顶点id
     * 返回值：
     * false -- 增加失败
     * true -- 增加成功
     */
    public boolean addEdge(int u, int v) {
        if (u == v) return false;
        int uIndex = findNode(u);
        int vIndex = findNode(v);
        if (uIndex < 0 || vIndex < 0) return false;
        // 添加边时，邻接表中的顶点表对应的两个顶点都需要增加相应的边结点
        ArcNode arc = new ArcNode(vIndex);
        arc.nextArc = vexs[uIndex].firstArc;
        vexs[uIndex].firstArc = arc;

//		————————————————；
        arc = new ArcNode(uIndex);
        arc.nextArc = vexs[vIndex].firstArc;
        vexs[vIndex].firstArc = arc;
//		————————————————；

        edgeNum++;
        return true;
    }

    /**
     * 功能：删除图中（无向）边
     * 参数：
     * u,v -- 顶点id
     * 返回值：
     * false -- 失败
     * true -- 成功
     */
    public boolean removeEdge(int u, int v) {
        if (u == v) return false;
        int uIndex = findNode(u);
        int vIndex = findNode(v);
        if (uIndex < 0 || vIndex < 0) return false;

        // 如果有线路经过该边，则不可删除
        for (int i = 0; i < lines.size; i++) {
            Line route = lines.routes[i];
            for (int j = 1; j < route.size; j++) {
                VNode node1 = route.vexs[j - 1];
                VNode node2 = route.vexs[j];
                if (node1.id == vexs[uIndex].id && node2.id == vexs[vIndex].id || node2.id == vexs[uIndex].id && node1.id == vexs[vIndex].id) {
                    return false;
                }
            }
        }

        ArcNode pre, p;
        // 顶点u的边表中的找到边结点v的前序结点
        pre = vexs[uIndex].firstArc;
        p = pre;
        if (p == null) return false;
        while (p != null) {
            if (p.adjVex == vIndex)
                break;
            pre = p;
            p = p.nextArc;
        }
        // 删除边结点
        if (pre == vexs[uIndex].firstArc)
            vexs[uIndex].firstArc = pre.nextArc; // correct
        else
            pre.nextArc = p.nextArc;

        // 顶点v的边表中的找到边结点u的前序结点
        pre = vexs[vIndex].firstArc;
        p = pre;
        if (p == null) return false;
        while (p != null) {
            if (p.adjVex == uIndex)
                break;
            pre = p;
            p = p.nextArc;
        }
        // 删除边结点
        if (pre == vexs[vIndex].firstArc)
            vexs[vIndex].firstArc = pre.nextArc; // correct
        else
            pre.nextArc = p.nextArc;
        edgeNum--;
        return true;
    }

    /**
     * 功能：删除图中顶点
     * 参数：
     * u -- 顶点id
     * 返回值：
     * false -- 失败
     * true -- 成功
     */
    public boolean removeNode(int u) {
        int uIndex = findNode(u);
        if (uIndex < 0) return false;

        // 如果站点上有线路则不能删
        if (lines.queryRouteAtSite(uIndex).size() != 0) {
            return false;
        }
        // 顶点表中删除顶点结点u
        for (int i = uIndex + 1; i < vexNum; i++) {
            vexs[i - 1] = vexs[i];
        }
        vexNum--;
        ArcNode pre, p;
        for (int i = 0; i < vexNum; i++) {
            pre = vexs[i].firstArc;
            p = pre;
            while (p != null) {
                if (p.adjVex == uIndex) { // 邻居顶点为顶点u，则删除
                    p = p.nextArc;
                    if (pre == vexs[i].firstArc && pre.nextArc == p) // correct
                        vexs[i].firstArc = p;
                    else
                        pre.nextArc = p;
                    edgeNum--;
                } else {
                    if (p.adjVex > uIndex) // 邻居顶点的索引>顶点u的索引，需要修改
                        p.adjVex--;
                    pre = p;
                    p = p.nextArc;
                }
            }
        }
        return true;
    }

    // 显示邻接表
    public void showGraph() {
        System.out.println("\n无向图有" + vexNum + "顶点和" + edgeNum + "边\n");
        System.out.println("\t结点\t相邻边");

        ArcNode p;
        for (int i = 0; i < vexNum; i++) {
            System.out.print("\t" + vexs[i].id + "(" + vexs[i].info + ")");
            p = vexs[i].firstArc;
            while (p != null) {
//				————————————————；
                System.out.print("\t" + vexs[p.getAdjVex()].id + "(" + vexs[p.getAdjVex()].info + ")");
                p = p.nextArc;
//				————————————————；
            }
            System.out.println();
        }
    }


    public VNode[] getVexs() {
        return vexs;
    }

    public int getVexNum() {
        return vexNum;
    }

    public int getArcNum() {
        return edgeNum;
    }

    @Override
    public int getType() {
        return 0;
    }

    @Override
    public List<GraphNode> getNodes() {
        List<GraphNode> res = new LinkedList<>();
        for (int i = 0; i < vexNum; i++) {
            int finalI = i;
            res.add(new GraphNode() {
                @Override
                public String getName() {
                    return vexs[finalI].id + "(" + vexs[finalI].info + ")";
                }
            });
        }
        return res;
    }

    @Override
    public List<GraphEdge> getEdges() {
        List<GraphEdge> res = new LinkedList<>();
        for (int i = 0; i < vexNum; i++) {
            VNode vex = vexs[i];
            ArcNode p = vex.firstArc;
            while (p != null) {
                VNode now = vexs[p.adjVex];
                if (now.id > vex.id) {
                    res.add(new GraphEdge() {
                        @Override
                        public int getRank() {
                            return 0;
                        }

                        @Override
                        public GraphNode getFirst() {
                            return new GraphNode() {
                                @Override
                                public String getName() {
                                    return vex.id + "(" + vex.info + ")";
                                }
                            };
                        }

                        @Override
                        public GraphNode getSecond() {
                            return new GraphNode() {
                                @Override
                                public String getName() {
                                    return now.id + "(" + now.info + ")";
                                }
                            };
                        }
                    });
                }
                p = p.nextArc;
            }
        }
        return res;
    }

    @Override
    public List<GraphRoute> getRoutes() {
        List<GraphRoute> res = new LinkedList<>();
        for (int i = 0; i < lines.size; i++) {
            Line line = lines.routes[i];
            res.add(new GraphRoute() {
                @Override
                public String getName() {
                    return line.lineNo;
                }

                @Override
                public List<GraphNode> getNodes() {
                    List<GraphNode> res = new LinkedList<>();
                    for (int i = 0; i < line.size; i++) {
                        int finalI = i;
                        res.add(new GraphNode() {
                            @Override
                            public String getName() {
                                return line.vexs[finalI].id + "(" + line.vexs[finalI].info + ")";
                            }
                        });
                    }
                    return res;
                }
            });
        }
        return res;
    }

    public void setLines(Lines lines) {
        this.lines = lines;
    }

    // 加载数据
    public void load(GraphData graphData) {

        // 记录源状态
        VNode[] vexs = this.vexs;
        int vexNum = this.vexNum;
        int edgeNum = this.edgeNum;
        int linesSize = this.lines.size;
        Line[] lineRoutes = this.lines.routes;

        // 开始导入
        this.vexs = new VNode[MAXSIZE];
        this.vexNum = 0;
        this.edgeNum = 0;
        this.lines.size = 0;
        this.lines.routes = new Line[Lines.MAXSIZE];

        try {
            for (String node : graphData.getNodes()) {
                int id = Integer.parseInt(node.split("\\(")[0]);
                String name = node.split("\\(")[1].split("\\)")[0];
                addNode(id, name);
            }
            for (List<String> edge : graphData.getEdges()) {
                int id1 = Integer.parseInt(edge.get(0).split("\\(")[0]);
                int id2 = Integer.parseInt(edge.get(1).split("\\(")[0]);
                addEdge(id1, id2);
            }
            for (List<String> routes : graphData.getRoutes()) {
                Line line = new Line(this);
                line.lineNo = routes.get(0);
                for (int i = 1; i < routes.size(); i++) {
                    String route = routes.get(i);
                    line.addStation(line.size, Integer.parseInt(route.split("\\(")[0]));
                }
                lines.addLine(line);
            }
        } catch (Exception e) {

            // 如果出错则恢复状态
            this.vexs = vexs;
            this.vexNum = vexNum;
            this.edgeNum = edgeNum;
            this.lines.size = linesSize;
            this.lines.routes = lineRoutes;
            throw e;
        }
    }
}