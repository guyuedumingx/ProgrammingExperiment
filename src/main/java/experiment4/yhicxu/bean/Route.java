package experiment4.yhicxu.bean;

import util.graphutil.GraphNode;
import util.graphutil.GraphRoute;

import java.util.List;

public class Route implements GraphRoute {

    private String name;
    private List<GraphNode> sites;

    public Route(String name, List<GraphNode> sites) {
        this.name = name;
        this.sites = sites;
    }

    public void setName(String name) {
        this.name = name;
    }

    // 计算两个站点的距离
    public int calculateDistance(Site site1, Site site2) {
        int s1 = sites.indexOf(site1);
        int s2 = sites.indexOf(site2);
        return Math.abs(s1 - s2);
    }

    @Override
    public boolean equals(Object o) {
        Route that = (Route) o;
        if (this.sites.size() != that.sites.size()) {
            return false;
        }
        int len = this.sites.size();
        for (int i = 0; i < len; i++) {
            if (!this.sites.get(i).equals(that.sites.get(i))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<GraphNode> getNodes() {
        return sites;
    }

    @Override
    public String toString() {
        return "Route{" +
                "name='" + name + '\'' +
                ", sites=" + sites +
                '}';
    }
}
