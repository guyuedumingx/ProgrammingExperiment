package util.treeUtil;

import org.xmind.core.ISheet;
import org.xmind.core.ITopic;
import org.xmind.core.IWorkbook;
import org.xmind.core.IWorkbookBuilder;
import org.xmind.core.internal.dom.WorkbookBuilderImpl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * <p><b>类名：</b>{@code TreeUtil}</p>
 * <p><b>功能：</b></p><br>树工具类
 * <p><b>方法：</b></p>
 * <br> {@link #buildXmind(HuffmanNode, String)}生成Xmind文件
 * <br> {@link #printTree(HuffmanNode)}打印树
 *
 * @author 60rzvvbj
 * @date 2021/4/21
 */
public class TreeUtil {
    static IWorkbookBuilder builder = new WorkbookBuilderImpl();
    static IWorkbook workbook;

    public static void buildXmind(HuffmanNode root, String fileName) {
        workbook = builder.createWorkbook();
        ISheet sheet = workbook.getPrimarySheet();
        ITopic rootTopic = sheet.getRootTopic();
        rootTopic.setTitleText(root.getData());
        buildXmindTree(root.getLeft(), rootTopic);
        buildXmindTree(root.getRight(), rootTopic);

        try {
            workbook.save(fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void buildXmindTree(HuffmanNode node, ITopic parentTopic) {
        if (node == null) {
            return;
        }
        ITopic topic = workbook.createTopic();
        topic.setTitleText(node.getData());
        parentTopic.add(topic);
        buildXmindTree(node.getLeft(), topic);
        buildXmindTree(node.getRight(), topic);
    }

    public static void printTree(HuffmanNode root) {
        LinkedList<HuffmanNode> allNode = new LinkedList<>();
        HashMap<HuffmanNode, Integer> nodeHeight = new HashMap<>();
        LinkedList<HuffmanNode> leafNode = new LinkedList<>();
        showTreeDfs(allNode, nodeHeight, leafNode, 0, root);
        HashMap<HuffmanNode, Integer> nodeX = new HashMap<>();
        HashMap<HuffmanNode, Integer> nodeY = new HashMap<>();
        int maxHeight = 0;
        for (HuffmanNode node : nodeHeight.keySet()) {
            maxHeight = Math.max(nodeHeight.get(node), maxHeight);
        }
        int width = maxHeight + maxHeight / 2;
        char[][] chart = new char[maxHeight * 2 + 2][width * (leafNode.size() + 1)];
        for (char[] chars : chart) {
            Arrays.fill(chars, ' ');
        }
        int x = width;
        for (HuffmanNode node : leafNode) {
            nodeX.put(node, x);
            x += width;
        }
        for (int y = maxHeight; y >= 0; y--) {
            for (HuffmanNode node : allNode) {
                if (nodeHeight.get(node) == y) {
                    nodeY.put(node, 2 * y);
                    if (node.getLeft() != null || node.getLeft() != null) {
                        nodeX.put(node, (nodeX.get(node.getLeft()) + nodeX.get(node.getRight())) / 2);
                    }
                }
            }
        }
        for (HuffmanNode node : allNode) {
            setChar(chart, node.getData(), nodeX.get(node), nodeY.get(node));
            if (node.getLeft() != null && node.getRight() != null) {
                chart[(nodeY.get(node) + nodeY.get(node.getLeft())) / 2][(nodeX.get(node) + nodeX.get(node.getLeft())) / 2] = '/';
                chart[(nodeY.get(node) + nodeY.get(node.getRight())) / 2][(nodeX.get(node) + nodeX.get(node.getRight())) / 2 + 1] = '\\';
            }
        }
        for (int i = 0; i < chart.length; i++) {
            for (int j = 0; j < chart[i].length; j++) {
                System.out.print(chart[i][j]);
            }
            System.out.println();
        }
    }

    private static void setChar(char[][] chart, String str, int x, int y) {
        String[] strArr = str.split(" ");
        for (int i = 0; i < strArr.length; i++) {
            char[] chars = strArr[i].toCharArray();
            int len = chars.length;
            int start = x - len / 2;
            int index = 0;
            for (int j = start; index < chars.length; j++) {
                chart[y + i][j] = chars[index++];
            }
        }
    }

    private static void showTreeDfs(LinkedList<HuffmanNode> allNode, HashMap<HuffmanNode, Integer> nodeHeight, LinkedList<HuffmanNode> leafNode, int nowHeight, HuffmanNode node) {
        allNode.add(node);
        nodeHeight.put(node, nowHeight);
        if (node.getLeft() == null && node.getRight() == null) {
            leafNode.add(node);
        } else {
            showTreeDfs(allNode, nodeHeight, leafNode, nowHeight + 1, node.getLeft());
            showTreeDfs(allNode, nodeHeight, leafNode, nowHeight + 1, node.getRight());
        }
    }
}
