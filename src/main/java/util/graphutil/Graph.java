package util.graphutil;

import experiment4.yhicxu.bean.Road;
import experiment4.yhicxu.bean.Site;

import java.util.List;

public interface Graph {
    int getType();

    List<GraphNode> getNodes();

    List<GraphEdge> getEdges();

    List<GraphRoute> getRoutes();
}
