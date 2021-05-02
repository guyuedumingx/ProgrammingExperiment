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

    public boolean addRoute(String name, String[] sNames) {
        Set<String> set = new HashSet<>();
        for (String sName : sNames) {
            if (siteName.get(sName) == null) {
                return false;
            }
            set.add(sName);
        }
        if (set.size() != sNames.length) {
            return false;
        }
        Site now = siteName.get(sNames[0]);
        for (int i = 1; i < sNames.length; i++) {
            Set<Site> nowAdjacentSites = now.getAdjacentSites();
            for (Site s : nowAdjacentSites) {
                if (s.getName().equals(sNames[i])) {
                    now = s;
                    break;
                }
            }
        }
        List<GraphNode> list = new ArrayList<>(sNames.length);
        for (int i = 0; i < sNames.length; i++) {
            list.add(i, siteName.get(sNames[i]));
        }
        Route r = new Route(name, list);
        for (GraphRoute t : routes) {
            if (t.equals(r)) {
                return false;
            }
        }
        routes.add(r);
        return true;
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
        return routes;
    }
}
