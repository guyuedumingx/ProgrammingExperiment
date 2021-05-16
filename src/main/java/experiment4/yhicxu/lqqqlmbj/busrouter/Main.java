package experiment4.yhicxu.lqqqlmbj.busrouter;

import java.io.PrintStream;
import java.util.Scanner;

import experiment4.yhicxu.lqqqlmbj.busrouter.router.ALGraph;
import experiment4.yhicxu.lqqqlmbj.busrouter.router.Lines;

public class Main {
    public static Scanner scanner = new Scanner(System.in);
    public static PrintStream printStream = System.out;


    public static void showMenu() {
        printStream.println();
        printStream.println("==================");
        printStream.println("1. 添加结点");
        printStream.println("2. 添加边");
        printStream.println("3. 删除结点");
        printStream.println("4. 删除边");
        printStream.println("5. 显示邻接表");
        printStream.println("6. 添加线路");
        printStream.println("7. 修改线路");
        printStream.println("8. 删除线路");
        printStream.println("9. 浏览所有线路");
        printStream.println("10. 线路查询");
        printStream.println("11. 保存");
        printStream.println("12. 导入");
        printStream.println("13. 退出");
        printStream.println("==================");
        printStream.println();
        printStream.println("请选择操作类型(1~11)：");
    }

    public static void main(String[] args) {
        ALGraph graph = new ALGraph();
        Lines lines = new Lines(graph);
        graph.setLines(lines);
        Action action = new Action(graph, lines);
        printStream.println("欢迎使用公交管理系统！");
        boolean flag = true;
        while (flag) {
            showMenu();
            int menu = scanner.nextInt();
            switch (menu) {
                case 1:
                    action.addNode();
                    break;
                case 2:
                    action.addEdge();
                    break;
                case 3:
                    action.removeNode();
                    break;
                case 4:
                    action.removeEdge();
                    break;
                case 5:
                    action.showGraph();
                    break;
                case 6:
                    action.addLine();
                    break;
                case 7:
                    action.modifyLine();
                    break;
                case 8:
                    action.removeLine();
                    break;
                case 9:
                    action.showLines();
                    break;
                case 10:
                    action.queryLine();
                    break;
                case 11:
                    action.save();
                    break;
                case 12:
                    action.load();
                    break;
                case 13:
                    action.close();
                    flag = false;
                    break;
                default:
                    break;

            }
        }

    }
}

	