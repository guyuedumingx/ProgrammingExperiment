package util.graphutil;

import java.util.List;

public interface Graph {
    int getType();

    List<GraphNode> getNodes();

    List<GraphEdge> getEdges();

    List<GraphRoute> getRoutes();
}
