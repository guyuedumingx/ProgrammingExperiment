package experiment5.yhicxu.lqqqlmbj.entity;

public class User {	
	public String mobile; // 用户账号
	public String userName; // 用户名
	public String password;	
	public int role ;// 0 卡用户, 1 卡业务员, 2 系统管理员
	public String cno; //校园卡号
	
	public User() {}
	public User(String mobile, String password, String userName, int role, String cno) {		
		this.mobile = mobile;
		this.password = password;	
		this.userName = userName;			
		this.role = role;
		this.cno = cno;
	}
}