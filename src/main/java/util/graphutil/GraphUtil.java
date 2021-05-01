package util.graphutil;

import java.io.FileNotFoundException;
import java.io.PrintStream;

public class GraphUtil {
    public static boolean exportGraph(Graph graph, String path) {
        PrintStream printStream = null;
        try {

            // start
            printStream = new PrintStream(path);
            printStream.println("{");

            // type
            printStream.println("\t\"type\": " + graph.getType() + ",");

            // nodes
            printStream.print("\t\"nodes\": [");
            int len = graph.getNodes().size();
            int count = 0;
            for (GraphNode node : graph.getNodes()) {
                if (count == len - 1) {
                    printStream.println("\"" + node.getName() + "\"],");
                } else {
                    printStream.print("\"" + node.getName() + "\", ");
                }
                count++;
            }

            // edges
            printStream.println("\t\"edges\": [");
            len = graph.getEdges().size();
            count = 0;
            for (GraphEdge edge : graph.getEdges()) {
                if (count == len - 1) {
                    printStream.println("\t\t[\"" + edge.getFirst().getName() + "\", \"" + edge.getSecond().getName() + "\"]");
                } else {
                    printStream.println("\t\t[\"" + edge.getFirst().getName() + "\", \"" + edge.getSecond().getName() + "\"],");
                }
                count++;
            }
            printStream.println("\t]");

            // end
            printStream.println("}");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (printStream != null) {
                printStream.close();
            }
        }
        return false;
    }
}
