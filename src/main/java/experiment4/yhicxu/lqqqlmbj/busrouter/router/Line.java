package experiment4.yhicxu.lqqqlmbj.busrouter.router;

import java.util.ArrayList;
import java.util.Scanner;

// 公交车线路类
public class Line {
    private static int MAXSIZE = 10; // 线路中最大站点数量
    public ALGraph g = null;
    public VNode[] vexs; // 线路中的站点
    public String lineNo; // 线路号
    public int size; // 线路中站点数量

    public Line(ALGraph g) {
        this.g = g;
        this.vexs = new VNode[MAXSIZE];
        this.lineNo = null;
        this.size = 0;
    }

    /**
     * 功能：站点在线路中的位置
     * 参数：
     * staId -- 站点id
     * 返回值：
     * int -- 位置索引
     */
    public int inLine(int staId) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (staId == vexs[i].id) {
                index = i;
                break;
            }
        }
        return index;
    }

    /**
     * 功能：可加入线路的候选站点
     * 参数：
     * staId -- 站点id
     * 返回值：
     * ArrayList<VNode> -- 候选站点
     */
    public ArrayList<VNode> candidateStation(int staId) {
        int index = g.findNode(staId);
        if (index < 0) return null;
        ArrayList<VNode> cands = new ArrayList<VNode>();
        ArcNode p = g.vexs[index].firstArc;
        while (p != null) {
            if (inLine(g.vexs[p.adjVex].id) < 0) // correct
                cands.add(g.vexs[p.adjVex]);
            p = p.nextArc;
        }
        return cands;
    }

    /**
     * 功能：添加一个站点，只能在线路末尾加入
     * 参数：
     * site -- 新增站点的位置
     * staId -- 站点id
     * 返回值：
     */
    public boolean addStation(int site, int staId) {
        if (size == MAXSIZE) {
            //System.out.println("线路已达到最大站点数量，无法再增加站点！");
            return false;
        }
        if (site < 0 || site > size) {
            //System.out.println("站点位置非法，无法增加站点！");
            return false;
        }
        if (g.findNode(staId) < 0) {
            //System.out.println("站点非法，无法增加！");
            return false;
        }
        // 线路还没有站点
        if (size == 0) {
            vexs[size++] = g.vexs[g.findNode(staId)];
            return true;
        }
        boolean yn = true;
        // 判断新增站点与site-1位置站点是否为邻居关系
        if (site > 0)
            yn = yn && g.isAdjVertex(vexs[site - 1].id, staId);
        // 判断新增站点与site位置站点是否为邻居关系
        if (site < size)
            yn = yn && g.isAdjVertex(staId, vexs[site].id);
        if (!yn) {
            //System.out.println("站点无效，无法增加！");
            return false;
        } else {
            // site后面的站点后移
            for (int i = size; i < site; i--)
                vexs[i] = vexs[i - 1];
            // 新增站点
            vexs[size++] = g.vexs[g.findNode(staId)];
            return true;
        }
    }

    /**
     * 功能：修改线路中站点
     * 参数：
     * oldId -- 旧站点Id
     * newId -- 新站点Id
     * 返回值：
     * false -- 失败
     * true -- 成功
     */
    public boolean modifyStation(int oldId, int newId) {
        int index = inLine(oldId);
        if (index < 0) return false; // 旧站点不在线路中
        if (inLine(newId) >= 0) return false; // 新站点已在线路中
        boolean yn = true;
        // 新、旧站点需要有一致的前驱与后继站点
        if (index > 0)
            yn = yn && g.isAdjVertex(vexs[index - 1].id, newId);
        if (index < size)
            yn = yn && g.isAdjVertex(newId, vexs[index + 1].id);
        if (yn) { // 站点替换
            vexs[index] = g.vexs[g.findNode(newId)];
        }
        return yn;
    }

    // 显示线路站点
    public void show() {
        System.out.print(lineNo + "线路的站点列表：");
        for (int i = 0; i < size; i++) {
            if (i == 0)
                System.out.print(vexs[i].id);
            else
                System.out.print("->" + vexs[i].id);
        }
        System.out.println();
    }


    // 计算两个站点的距离
    public int calculateDistance(VNode site1, VNode site2) {
        int s1 = 0, s2 = 0;
        for (int i = 0; i < size; i++) {
            if (site1 == vexs[i]) {
                s1 = i;
            }
            if (site2 == vexs[i]) {
                s2 = i;
            }
        }
        return Math.abs(s1 - s2);

    }
}