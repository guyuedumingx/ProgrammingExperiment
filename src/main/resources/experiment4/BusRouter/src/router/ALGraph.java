package router;
// 用连接表描述的图/网络
public class ALGraph {
	private static int MAXSIZE = 20; // 最大顶点数
	public VNode[] vexs; // 保存图中顶点
	public int vexNum; // 顶点数量
	public int edgeNum; // 边数量
	
	public ALGraph() {
		this.vexs  = new VNode[MAXSIZE];		
		this.vexNum = 0;	
		this.edgeNum = 0;
	}
	public ALGraph(VNode[] vexs, int vexNum, int arcNum) {	
		this.vexs = vexs;	
		this.vexNum = vexNum;
		this.edgeNum = arcNum;		
	}
	//返回顶点存放在vexs中索引位置
	public int findNode(int id) {
		int index = -1;
		for(int i=0; i<vexNum; i++) {
			if(id == vexs[i].id) {
				index = i;
				break;
			}
		}
		return index;
	}
	
	/**
	 * 功能：判别是否是有效相邻顶点
	 *  参数：
	 * g -- 图
	 * u,v -- 相邻顶点id
	 * 返回值：	
	 * false -- 不是相邻顶点
	 * true -- 是相邻顶点 	
	*/ 
	public boolean isAdjVertex( int u, int v) {		
		int index = findNode(u); // 顶点u存放在vexs中索引位置
		if( index<0 ) return false;	
		boolean yn = false;
		ArcNode p = vexs[index].firstArc;
		// 遍历边表
		while ( p != null) {
			if(v == p.adjVex) {
				yn = true;
				break;
			}
			p = p.nextArc;
		}
		return yn;
	}
	/**
	 * 功能：在图中增加节点
	 *  参数：
	 * id -- 顶点id
	 * info -- 顶点信息
	 * 返回值：	
	 * false -- 增加失败
	 * true -- 增加成功 	
	*/ 
	public boolean addNode(int id,String info) {
		if(findNode(id) >= 0) return false;
		VNode node = new VNode(id,info);
		vexs[vexNum++] = node;
		return true;
	}
	
	/**
	 * 功能：在图中增加（无向）边
	 *  参数：
	 * u,v -- 顶点id
	 * 返回值：	
	 * false -- 增加失败
	 * true -- 增加成功 	
	*/ 
	public boolean addEdge(int u,int v) {
		if(u == v) return false;
		int uIndex = findNode(u);
		int vIndex = findNode(v);
		if(uIndex<0 || vIndex<0) return false;
		// 添加边时，邻接表中的顶点表对应的两个顶点都需要增加相应的边结点
		ArcNode arc = new ArcNode(vIndex);
		arc.nextArc=vexs[uIndex].firstArc;
		vexs[uIndex].firstArc=arc;		
		arc = new ArcNode(uIndex);
		arc.nextArc=vexs[vIndex].firstArc;
		vexs[vIndex].firstArc=arc;
		
		edgeNum ++;
		return true;
	}
	/**
	 * 功能：删除图中（无向）边
	 *  参数：
	 * u,v -- 顶点id
	 * 返回值：	
	 * false -- 失败
	 * true -- 成功 	
	*/ 
	public boolean removeEdge(int u,int v) {
		if(u == v) return false;
		int uIndex = findNode(u);
		int vIndex = findNode(v);
		if(uIndex<0 || vIndex<0) return false;
		
		ArcNode pre,p;
		// 顶点u的边表中的找到边结点v的前序结点
		pre = vexs[uIndex].firstArc;
		p = pre;
		while(p != null) {
			if(p.adjVex == v) 
				break;
			pre = p;
			p = p.nextArc;
		}
		if(p == null) return false;
		// 删除边结点
		if(pre == vexs[uIndex].firstArc)
			vexs[uIndex].firstArc = p.nextArc; 
		else
			pre.nextArc = p.nextArc;
		// 顶点v的边表中的找到边结点u的前序结点
		pre = vexs[vIndex].firstArc;
		p = pre;
		while(p != null) {
			if(p.adjVex == v) 
				break;
			pre = p;
			p = p.nextArc;
		}
		if(p == null) return false;	
		// 删除边结点
		if(pre == vexs[vIndex].firstArc)
			vexs[vIndex].firstArc = p.nextArc;
		else
			pre.nextArc = p.nextArc;
		edgeNum --;
		
		return true;
	}
	/**
	 * 功能：删除图中顶点
	 *  参数：
	 * u -- 顶点id
	 * 返回值：	
	 * false -- 失败
	 * true -- 成功 	
	*/ 
	public boolean removeNode(int u) {
		int uIndex = findNode(u); 
		if(uIndex < 0) return false;
		// 顶点表中删除顶点结点u
		for(int i=uIndex+1; i<vexNum; i++) {
			vexs[i-1] = vexs[i];
		}
		vexNum -- ;
		ArcNode pre,p;
		for(int i=0; i<vexNum; i++) {			
			pre = vexs[i].firstArc;
			p = pre;
			while(p != null) {				
				if(p.adjVex == uIndex) { // 邻居顶点为顶点u，则删除
					p = p.nextArc;
					if(pre == vexs[i].firstArc)
						vexs[i].firstArc = p;
					else
						pre.nextArc = p;
					edgeNum -- ;
				}
				else {
					if(p.adjVex > uIndex) // 邻居顶点的索引>顶点u的索引，需要修改
						p.adjVex -- ;
					pre = p;
					p = p.nextArc;
				}				
			}			
		}		
		return true;
	}
	// 显示邻接表
	public void showGraph() {
		
	}
	
	
	public VNode[] getVexs() {
		return vexs;
	}	
	public int getVexNum() {
		return vexNum;
	}
	public int getArcNum() {
		return edgeNum;
	}
}
