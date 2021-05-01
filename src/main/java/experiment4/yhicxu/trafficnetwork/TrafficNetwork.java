package experiment4.yhicxu.trafficnetwork;

import experiment4.yhicxu.bean.Road;
import experiment4.yhicxu.bean.Site;
import util.graphutil.Graph;
import util.graphutil.GraphEdge;
import util.graphutil.GraphNode;
import util.graphutil.GraphRoute;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class TrafficNetwork implements Graph {

    private List<GraphNode> sites;
    private List<GraphEdge> roads;
    private List<GraphRoute> routes;
    private Map<String, Site> siteName;

    public TrafficNetwork() {
        sites = new LinkedList<>();
        roads = new LinkedList<>();
        routes = new LinkedList<>();
        siteName = new HashMap<>();
    }

    public boolean addSite(String name) {
        if (siteName.get(name) == null) {
            Site site = new Site(name);
            sites.add(site);
            siteName.put(name, site);
            return true;
        } else {
            return false;
        }
    }

    public boolean addRoad(String site1Name, String site2Name) {
        Site site1 = siteName.get(site1Name);
        Site site2 = siteName.get(site2Name);
        if (site1 != null && site2 != null) {
            Road road = new Road(site1, site2);
            for (GraphEdge r : roads) {
                if (r.equals(road)) {
                    return false;
                }
            }
            roads.add(road);
            road.getFirst().addAdjacentSite(road.getSecond());
            road.getSecond().addAdjacentSite(road.getFirst());
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int getType() {
        return 0;
    }

    @Override
    public List<GraphNode> getNodes() {
        return sites;
    }

    @Override
    public List<GraphEdge> getEdges() {
        return roads;
    }

    @Override
    public List<GraphRoute> getRoutes() {
        return null;
    }
}
