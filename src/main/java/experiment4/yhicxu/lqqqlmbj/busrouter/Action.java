package experiment4.yhicxu.lqqqlmbj.busrouter;
import experiment4.yhicxu.lqqqlmbj.busrouter.router.ALGraph;
import experiment4.yhicxu.lqqqlmbj.busrouter.router.Lines;

import java.util.Scanner;

public class Action {
	private ALGraph graph;
	private Lines lines;
	private Scanner scanner = new Scanner(System.in);

	public Action(ALGraph graph, Lines lines) {
		this.graph = graph;
		this.lines = lines;
	}

	// 1. 添加结点
	public void addNode() {
		// 添加结点 
		System.out.println("请输入结点编号：");
		int id = scanner.nextInt();
		System.out.println("请输入结点附加信息：");
		String info = scanner.next();
		if (graph.addNode(id, info))
			System.out.println("添加结点成功！");
		else
			System.out.println("添加结点失败！");
	}
	// 2. 添加边
	public void addEdge() {
		// 添加结点 
		System.out.println("请输入第1个结点编号：");
		int u = scanner.nextInt();
		System.out.println("请输入第2个结点编号：");
		int v = scanner.nextInt();
		if (graph.addEdge(u, v))
			System.out.println("添加边成功！");
		else
			System.out.println("添加边失败！");
	}
	// 3. 删除结点
	public void removeNode() {
		System.out.println("请输入要删除结点编号：");
		int u = scanner.nextInt();
		if (graph.removeNode(u))
			System.out.println("删除节点成功！");
		else
			System.out.println("删除节点失败！");		
	}
	// 4. 删除边
	public void removeEdge() {
		System.out.println("请输入要删除边的第1个结点编号：");
		int u = scanner.nextInt();
		System.out.println("请输入要删除边的第1个结点编号：");
		int v = scanner.nextInt();
		if (graph.removeEdge(u, v))
			System.out.println("删除边成功！");
		else
			System.out.println("删除边失败！");	
	}
	// 5. 显示邻接表
	public void showGraph() {
		graph.showGraph();
	}
	// 6. 添加线路
	public void addLine() {
		
		if (lines.addLine())
			System.out.println("线路添加成功！");
		else
			System.out.println("线路添加失败！");	
	}
	// 7. 修改线路
	public void modifyLine() {
		// lines.modifyLine();
		
		int index = -1;
		while (index < 0) {
			System.out.println("请输入需要修改的线路编号(-1表示终止修改操作)：");
			String lineNo = scanner.next();
			if(lineNo==null || lineNo.equals(""))
				continue;
			if ("-1".equals(lineNo)) {
				return;
			}
			index = lines.findLineNo(lineNo);
			if(index < 0)
				System.out.println("线路编号无效！");
		}
		
		// 循环实现多个站点的改动
		while(true) {
			// 先获得需要调整的站点
			System.out.println("请输入需要调整的站点编号(-1表示终止修改站点操作)：");
			int oldId = scanner.nextInt();			
			if(oldId == -1)	break;
			
			System.out.println("请输入新站点编号：");
			int newId = scanner.nextInt();
			int result = lines.modifyLine(index, oldId, newId);
			if(result == 1) 
				System.out.println("站点修改成功!");
			else if(result == 0) 
				System.out.println("站点修改失败!");
			else if(result == -1)
				System.out.println("线路编号无效，请重新输入!");
			else if(result == -2)
				System.out.println("站点编号无效，请重新输入!");
			else if(result == -3)
				System.out.println("新站点编号无效，请重新输入!");			
		}
		
	}
	// 8. 删除线路
	public void removeLine() {
		System.out.println("请输入线路编号：");
		Scanner scanner = new Scanner(System.in);
		String lineNo = scanner.next();	
		int index = lines.findLineNo(lineNo);
		if( index < 0) {
			System.out.println("无效的线路编号！");
			return;
		}
		System.out.println("确认要删除线路"+lineNo+"吗？");
		System.out.println("\t1. 确认\n\t2. 放弃");
		int flag = scanner.nextInt();
		if (flag == 2) return ;
		
		if (lines.removeLine(index))
			System.out.println("线路删除成功！");
		else
			System.out.println("线路删除失败！");		
	}
	// 9. 浏览所有线路
	public void showLines() {
		lines.showLines();
	}
	// 10. 线路查询
	public void queryLine() {
		int begin, end;
		System.out.println("请输入起始站点编号：");
		begin = scanner.nextInt();
		System.out.println("请输入目的站点编号：");
		end = scanner.nextInt();
		if( !lines.queryLine(begin, end)) {
			System.out.println("没有从["+begin+"]站点到["+end+"]站点的线路");
		}
	}
}