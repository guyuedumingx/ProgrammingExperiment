package util;


import org.xmind.core.ISheet;
import org.xmind.core.ITopic;
import org.xmind.core.IWorkbook;
import org.xmind.core.IWorkbookBuilder;
import org.xmind.core.internal.dom.WorkbookBuilderImpl;

public class TreeUtil {
    static IWorkbookBuilder builder = new WorkbookBuilderImpl();
    static IWorkbook workbook;

    public static void buildXmind(TreeNode root, String fileName) {
        workbook = builder.createWorkbook();
        ISheet sheet = workbook.getPrimarySheet();
        ITopic rootTopic = sheet.getRootTopic();
        rootTopic.setTitleText(root.getData());
        ITopic left = workbook.createTopic();
        ITopic right = workbook.createTopic();
        buildXmindTree(root.getLeft(), left);
        buildXmindTree(root.getRight(), right);
        rootTopic.add(left);
        rootTopic.add(right);

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
}
