package common.util;

import org.xmind.core.*;
import pojo.Node;
import pojo.Project;
import pojo.User;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 与xmind互转工具类
 * @author yohoyes
 */
public class XmindUtil {
    static IWorkbookBuilder builder = null;
    static IWorkbook workbook;
    static Project project;
    static int projectId;
    static int userId;

    static {
        builder = Core.getWorkbookBuilder();
    }

    /**
     * 读取本地文件,并转为project
     * @param path 文件路径
     * @param id 用户id
     * @return
     */
    public static int getWorkBook(String path, int id){
       userId = id;
        try {
            workbook = builder.loadFromPath(path);
        }catch (Exception e) {
            logger.error(e.getMessage());
        }
        ISheet primarySheet = workbook.getPrimarySheet();
        projectId = addProject(primarySheet);
        ITopic rootTopic = primarySheet.getRootTopic();
        int rootId = createNodes(rootTopic, 0);
        project = projectService.getProject(projectId);
        project.setHeadNodeId(rootId);
        projectService.updateProject(project);
        return projectId;
    }

    /**
     * 从输入流中读取输入并转化为project
     * @param in 输入流
     * @param user 操作者
     * @return
     */
    public static int getWorkBook(InputStream in, User user) {
        userId = user.getId();
        try {
            workbook = builder.loadFromStream(in);
        }catch (Exception e) {
            logger.error(e.getMessage());
        }
        //操作的xmind画布
        ISheet primarySheet = workbook.getPrimarySheet();
        projectId = addProject(primarySheet);
        //从画布中获取的根节点
        ITopic rootTopic = primarySheet.getRootTopic();
        //创建根节点,所有项目的根节点的父节点id均为0
        int rootId = createNodes(rootTopic, 0);
        project = projectService.getProject(projectId);
        //把该节点设置成project的根节点
        project.setHeadNodeId(rootId);
        //更新project
        projectService.updateProject(project);
        return projectId;
    }

    /**
     * 传入画布中的根节点
     * 遍历根节点的字节点并递归创建节点
     * @param topic 传入的xmind节点
     * @param parentId node的父节点
     * @return
     */
    private static int createNodes(ITopic topic, int parentId){
        int nodeId = addNode(topic, parentId);
        Iterator<ITopic> iterator = topic.getAllChildrenIterator();
        while (iterator.hasNext()){
            createNodes(iterator.next(),nodeId);
        }
        return nodeId;
    }

    /**
     * 初始化节点并创建
     * 默认从xmind中导入的节点,topic的主题对应者node的主题
     * topic的notes和labels将被合并成node的content
     * 默认创建的node节点不禁止添加子节点
     * 默认创建的node节点不匿名
     * @param topic 传入的xmind节点
     * @param parendId node节点的父节点Id
     * @return
     */
    private static int addNode(ITopic topic, int parendId) {
        Node node = new Node();
        node.setTheme(topic.getTitleText());
        node.setAuthor(userId);
        node.setLastEditTime(System.currentTimeMillis()+"");
        node.setBanAppend(false);
        node.setProjectId(projectId);
        node.setParentId(parendId);
        node.setNameless(false);
        node.setLastEditId(userId);
        node.setContent(lablesToString(topic));
        return nodeService.newNode(node);
    }

    /**
     * 将xmind节点中的所有lables合并成String
     * @param topic
     * @return
     */
    private static String lablesToString(ITopic topic){
        Set<String> labels = topic.getLabels();
        Iterator<String> iterator = labels.iterator();
        StringBuilder str = new StringBuilder();
        while (iterator.hasNext()){
            str.append(iterator.next()+"\n");
        }
        INotes notes = topic.getNotes();
        str.append(notes.toString());
        return str.toString();
    }

    /**
     * 把xmind中的项目转化成project
     * @param sheet
     * @return
     */
    private static int addProject(ISheet sheet){
        project = new Project();
        project.setAuthor(userId);
        project.setCreateTime(System.currentTimeMillis()+"");
        project.setName(sheet.getRootTopic().getTitleText());
        project.setPublic(false);
        project.setRank(1);
        project.setIntroduction("该项目还没有简介哦,快来加上吧!");
        project.setIntroduction(sheet.getRootTopic().getNotes().toString());
        project.setDeadline(System.currentTimeMillis()+"");
        return projectService.newProject(project,false,userId);
    }

    private static void createXmind(int projectId) {
        project = projectService.getProject(projectId);
        int rootId = project.getHeadNodeId();
        workbook = builder.createWorkbook();
        ISheet sheet = workbook.getPrimarySheet();
        Node rootNode = nodeService.getNode(rootId,userId);
        ITopic rootTopic = sheet.getRootTopic();
        rootTopic.setTitleText(rootNode.getTheme());
        writeITopics(rootTopic, rootNode);
    }

    public static void write(int projectId, HttpServletResponse resp){
        createXmind(projectId);
        String fileName = project.getName();
        Pattern pattern;
        pattern = Pattern.compile("[\u4E00-\u9FA5|\\！|\\,|\\。|\\（|\\）|\\《|\\》|\\“|\\”|\\？|\\：|\\；|\\【|\\】]+");
        Matcher matcher = pattern.matcher(fileName);
        while (matcher.find()) {
            String mStr = matcher.group();
            try {
                fileName = fileName.replaceFirst(mStr, URLEncoder.encode(mStr, "UTF-8"));
            } catch (Exception e) {

            }
        }
        resp.setContentType("multipart/form-data;charset=UTF-8;");
        resp.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xmind");
        try {
            workbook.save(resp.getOutputStream());
        }catch (Exception e){
            logger.error(e.getMessage());
        }
    }

    public static void write(int projectId, String path){
        createXmind(projectId);
        try {
            workbook.save(path + project.getName() + ".xmind");
        }catch (Exception e){
            logger.error(e.getMessage());
        }
    }

    public static ITopic writeITopics(ITopic root, Node node){
        int[] children = node.getChildren();
        if(children==null){return root;}
        for (int n : children) {
            Node child = nodeService.getNode(n,userId);
            ITopic topic = workbook.createTopic();
            topic.setTitleText(child.getTheme());

            IPlainNotesContent plainContent = (IPlainNotesContent) workbook.createNotesContent(INotes.PLAIN);
            plainContent.setTextContent(child.getContent());
            INotes notes = topic.getNotes();
            notes.setContent(INotes.PLAIN, plainContent);

            root.add(topic);
            writeITopics(topic,child);
        }
        return root;
    }
}
