package router;
// 边结点类
public class ArcNode {
	public int adjVex; // 邻居顶点的索引位置
	public ArcNode nextArc; // 下一个边结点
	
	public ArcNode() {
		this(-1);
	}
	public ArcNode(int adjVex) {		
		this.adjVex = adjVex;
		this.nextArc = null;
	}
		
	public int getAdjVex() {
		return adjVex;
	}
	public void setAdjVex(int adjVex) {
		this.adjVex = adjVex;
	}
	public ArcNode getNextArc() {
		return nextArc;
	}
	public void setNextArc(ArcNode nextArc) {
		this.nextArc = nextArc;
	}
}
