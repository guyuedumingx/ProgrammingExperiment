

import java.util.Scanner;

import router.ALGraph;
import router.ArcNode;
import router.Line;
import router.Lines;
import router.VNode;

public class Main {
	public static Scanner scanner = new Scanner(System.in);
	public static void showMenu() {
		System.out.println("\n===========测试邻接表表示的图===========");
		System.out.println("\t1. 添加结点");
		System.out.println("\t2. 添加边");
		System.out.println("\t3. 删除结点");
		System.out.println("\t4. 删除边");
		System.out.println("\t5. 显示邻接表");
		System.out.println("     ----------------------");
		System.out.println("\t6. 添加线路");
		System.out.println("\t7. 修改线路");
		System.out.println("\t8. 删除线路");
		System.out.println("\t9. 浏览所有线路");
		System.out.println("\t10. 线路查询");
		System.out.println("\t11. 退出");
		
		System.out.println("\n\t请选择操作类型(1~11)：");	
		
	}
	
	public static void main(String[] args) {		
		ALGraph graph = new ALGraph();
		Lines lines = new Lines(graph);
		Action action = new Action(graph,lines);
		while(true) {
			showMenu();
			int menu = scanner.nextInt();
			switch(menu) {
				case 1:action.addNode();break;
				case 2:action.addEdge();break;
				case 3:action.removeNode();break;
				case 4:action.removeEdge();break;
				case 5:action.showGraph();break;
				case 6:action.addLine();break;
				case 7:action.modifyLine();break;
				case 8:action.removeLine();break;
				case 9:action.showLines();break;
				case 10:action.queryLine();break;
				case 11:System.exit(0);
				default:break;
			}
		}

	}

	