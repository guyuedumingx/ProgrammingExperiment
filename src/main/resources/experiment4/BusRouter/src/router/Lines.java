package router;

import java.util.ArrayList;
import java.util.Scanner;
// 公交线路网，由多条线路组成
public class Lines {
	private static int MAXSIZE = 10; // 公交线路最大值
	public ALGraph g; 
	public Line[] routes; // 存放公交线路的数组
	public int size; // 当前公交线路数量
	private Scanner scanner = new Scanner(System.in);
	
	public Lines() {
		this(null);
	}
	public Lines(ALGraph g) {		
		this.routes = new Line[MAXSIZE];
		this.size = 0;
		this.g = g;
	}
	// 查找线路
	public int findLineNo(String lineNo) {
		int index = -1;
		for(int i=0; i<size; i++) {
			if(lineNo.equals(routes[i].lineNo)) {
				index = i;
				break;
			}
		}
		return index;
	}
	// 显示所有公交线路信息
	public void showLines() {
		System.out.println("所有公交线路信息：");
		for (int i = 0; i < size; i ++) {
			System.out.print("   "+(i+1)+"   " );
			routes[i].show();
		}
	}
	/**
	 * 功能：添加一条新线路
	 * 参数：
	 * 	 
	 * 返回值：
	 * Line -- 线路
	*/ 
	private Line createLine() {
		Line line = new Line(g);
		System.out.println("请输入新线路编号：");
		while( true) {
			String lineNo = scanner.next();
			if( findLineNo(lineNo) < 0) {
				line.lineNo = lineNo;
				break;
			}
			System.out.println("线路编号已存在，请重新输入新线路编号：");			
		}		
		int id;		
		// 循环添加站点
		while(true) {
			if( line.size > 0) {
				// 当前站点的可加入线路的候选站点
				ArrayList<VNode> cands = line.candidateStation(line.vexs[line.size-1].id);			
				System.out.println("可选站点列表：");
				for(int i=0; i<cands.size(); i++) {
					System.out.print(cands.get(i).id+"\t");
				}
				System.out.println();
			}
			// 输入下一个站点，也可以输入-1:终止 
			System.out.println("请输入站点编号(-1表示终止在线路中增加新站点)："); 
			id = scanner.nextInt();
			if(id == -1) break; // 终止在线路中增加新站点	
			boolean yn = line.addStation(line.size, id);			
			if ( !yn)
				System.out.println("添加站点失败！");			
			else // 成功加入一个站点 
				line.show();
		}
		return line;
	}
	/**
	 * 功能：添加公交线路，线路站点数量不少于5个
	 * 参数：
	 * 	 
	 * 返回值：
	 * false -- 失败
	 * true -- 成功 
	 */ 
	public boolean addLine() {
		if(size == MAXSIZE) return false;		
		Line line = createLine();
		if(line.size < 5) {
			System.out.println("线路站点数少于5个！");
			return false;
		}
		routes[size++] = line;
		//line.show();
		return true;		
	}
	/**
	 * 功能：修改公交线路
	 * 参数：
	 *  index -- 线路索引	
	 *  oldId -- 旧站点Id
	 *  newId -- 新站点Id
	 * 返回值：	
	 */ 
	public int modifyLine(int index, int oldId, int newId) {
		if(index<0 || index>=size) return -1;
		if(routes[index].inLine(oldId) < 0) return -2;
		if(g.findNode(newId) < 0 ) return -3;
		if( routes[index].modifyStation(oldId, newId)) 
			return 1;
		else
			return 0;
	}
	/**
	 * 功能：删除公交线路
	 * 参数：
	 *  index -- 线路位置索引
	 * 返回值：
	 * false -- 失败
	 * true -- 成功 
	 */ 
	public boolean removeLine(int index) {
		if(size == 0) return false;
		if(index < 0 || index >= size) return false;
		for (int j = index; j < size - 1; j ++)
			routes[j] = routes[j + 1];
		size --;

		return true;
	}
	/**
	 * 功能：查询线路，查询从起始站点-->目的站点的线路
	 * 参数：
	 * 	 
	 * 返回值：
	 * false -- 失败
	 * true -- 成功 
	 */ 
	public boolean queryLine(int begin, int end) {
		if (begin == end) return false; // 起始和目的站点时同一站点
		boolean yn = false;
		for(int i =0; i<size; i++) {
			Line line = routes[i];
			int beginIndex = line.inLine(begin);
			int endIndex = line.inLine(end);
			if(beginIndex >=0 && endIndex >=0) {
				// 起始站点索引位置>目的站点索引位置，进行交换
				if(beginIndex > endIndex) {
					int temp = beginIndex;
					beginIndex = endIndex;
					endIndex = temp;
				}
				// 输出从起始站点-->目的站点的线路
				System.out.println("公交线路为：");
				for(int j=beginIndex; j<=endIndex; j++) {
					System.out.print(line.vexs[j].id);
					if(j != endIndex)
						System.out.print("-");
				}
				System.out.println();
				yn = true;
			}
		}		
		return yn;
	}
}
