package experiment5.yhicxu.lqqqlmbj.entity;

import java.util.Date;

public class Card {
	public String cno; // 开号
	public String mobile; // 用户账户
	public Date time; // 开卡时间
	public double balance; // 余额
	public int status; // 状态 1:正常   0：挂失   
	
	public Card() {}
	public Card(String cno, String mobile, Date time, double balance, int status) {
		this.cno = cno;
		this.mobile = mobile;
		this.time = time;
		this.balance = balance;
		this.status = status;
	}	
}