package router;
// �߽����
public class ArcNode {
	public int adjVex; // �ھӶ��������λ��
	public ArcNode nextArc; // ��һ���߽��
	
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