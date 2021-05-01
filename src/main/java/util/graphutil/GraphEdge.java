package util.graphutil;

public interface GraphEdge {
    int getRank();

    GraphNode getFirst();

    GraphNode getSecond();
}
