package util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;

public class TreeUtil {
    static IWorkbookBuilder builder = new WorkbookBuilderImpl();
    static IWorkbook workbook;

    public static void buildXmind(TreeNode root, String fileName) {
        workbook = builder.createWorkbook();
        ISheet sheet = workbook.getPrimarySheet();
        ITopic rootTopic = sheet.getRootTopic();
        rootTopic.setTitleText(root.getData());
        buildXmindTree(root.getLeft(), rootTopic);
        buildXmindTree(root.getRight(), rootTopic);

        try {
            workbook.save( fileName + ".xmind");
        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void buildXmindTree(TreeNode node, ITopic parentTopic) {
        if(node == null) {
            return;
        }
        ITopic topic = workbook.createTopic();
        topic.setTitleText(node.getData());
        parentTopic.add(topic);
        buildXmindTree(node.getLeft(), topic);
        buildXmindTree(node.getRight(), topic);
    }

    public void printTree(TreeNode root) {
        LinkedList<TreeNode> allNode = new LinkedList<>();
        HashMap<TreeNode, Integer> nodeHeight = new HashMap<>();
        LinkedList<TreeNode> leafNode = new LinkedList<>();
        showTreeDfs(allNode, nodeHeight, leafNode, 0, root);
        HashMap<TreeNode, Integer> nodeX = new HashMap<>();
        HashMap<TreeNode, Integer> nodeY = new HashMap<>();
        int maxHeight = 0;
        for (TreeNode node : nodeHeight.keySet()) {
            maxHeight = Math.max(nodeHeight.get(node), maxHeight);
        }
        int width = maxHeight + maxHeight / 2;
        char[][] chart = new char[maxHeight * 2 + 2][width * (leafNode.size() + 1)];
        for (char[] chars : chart) {
            Arrays.fill(chars, ' ');
        }
        int x = width;
        for (TreeNode node : leafNode) {
            nodeX.put(node, x);
            x += width;
        }
        for (int y = maxHeight; y >= 0; y--) {
            for (TreeNode node : allNode) {
                if (nodeHeight.get(node) == y) {
                    nodeY.put(node, 2 * y);
                    if (node.getLeft() != null || node.getLeft() != null) {
                        nodeX.put(node, (nodeX.get(node.getLeft()) + nodeX.get(node.getRight())) / 2);
                    }
                }
            }
        }
        for (TreeNode node : allNode) {
            String text = node.getData().replaceAll("\r", "\\r").replaceAll("\n", "\\n").replaceAll(" ", "凵");
            setChar(chart, text, nodeX.get(node), nodeY.get(node));
            chart[(nodeY.get(node) + nodeY.get(node.getLeft())) / 2][(nodeX.get(node) + nodeX.get(node.getLeft())) / 2] = '/';
            chart[(nodeY.get(node) + nodeY.get(node.getRight())) / 2][(nodeX.get(node) + nodeX.get(node.getRight())) / 2 + 1] = '\\';
        }
        for (int i = 0; i < chart.length; i++) {
            for (int j = 0; j < chart[i].length; j++) {
                System.out.print(chart[i][j]);
            }
            System.out.println();
        }
    }

    private void setChar(char[][] chart, String str, int x, int y) {
        char[] chars = str.toCharArray();
        int len = chars.length;
        int start = x - len / 2;
        int index = 0;
        for (int i = start; index < chars.length; i++) {
            chart[y][i] = chars[index++];
        }
    }

    private void showTreeDfs(LinkedList<TreeNode> allNode, HashMap<TreeNode, Integer> nodeHeight, LinkedList<TreeNode> leafNode, int nowHeight, TreeNode node) {
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
