package experiment5.yhicxu.lqqqlmbj.entity;
import java.util.Date;

public class Transaction {
	public int flowNo; // 流水号
	public String cno;	// 卡号
	public Date dealTime; // 交易时间
	public double amount; // 交易额 
	public int type; // 交易类型：0--消费；1--充值
	
	public Transaction() {}
	public Transaction(int flowNo, String cno, Date dealTime, double amount, int type) {		
		this.flowNo = flowNo;
		this.cno = cno;
		this.dealTime = dealTime;
		this.amount = amount;
		this.type = type;
	}

}