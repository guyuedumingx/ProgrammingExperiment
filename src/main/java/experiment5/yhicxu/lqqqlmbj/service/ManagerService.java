package experiment5.yhicxu.lqqqlmbj.service;

import experiment5.yhicxu.lqqqlmbj.entity.User;

import java.util.ArrayList;
import java.util.Scanner;

//实现系统管理员的业务功能
public class ManagerService extends UserService{
	public boolean delete = false; // 删除用户时，需要同时删除用户的卡号和交易记录，需要单独标记
	private Scanner scanner = new Scanner(System.in);
	// 新增用户
	public void addUser() {		
		String mobile;
		int index = -1;
		while (true){
			System.out.println("手机号(11位):");
			mobile = scanner.next();
			index = userDatas.findUser(mobile);
			if(index < 0)
				break;			
			System.out.println("用户["+mobile+"]已存在，请重新输入");			
		}		
		System.out.println("登录密码:");
		String password = scanner.next();
		System.out.println("用户名:");
		String userName = scanner.next();
		System.out.println("角色(0 卡用户, 1 卡业务员, 2 系统管理员):");
		int role = scanner.nextInt();
		
		User user = new User(mobile, password, userName, role, "");
		userDatas.addUser(user);
		change = true; // 数据更改
	}
	// 删除用户
	public void removeUser() {
		//系统中只有admin用户，不进行删除		
		if(users.size() ==1) {
			return;
		}		
		String mobile;
		int index = -1;
		while (true){
			System.out.println("手机号(11位):");
			mobile = scanner.next();
			index = userDatas.findUser(mobile);
			if(index < 0)
				break;			
			System.out.println("用户["+mobile+"]不存在，请重新输入");			
		}
		User user = users.get(index);
		if(user.mobile.equals(users.get(curUserIndex).mobile)) {
			System.out.println("不能删除当前用户!");
			return;
		}
		if(index == curUserIndex) {
			System.out.println("不能删除[admin]系统管理员用户!");
			return;
		}
		userDatas.removeUser(user);
		change = true; // 数据更改
		//删除用户时，需要删除对应的卡号和交易记录
		if(user.cno != null) {
			cardDatas.removeCard(user.cno);
			transDatas.removeTransactionByCno(user.cno);
			delete = true; // 删除用户的卡号和交易记录，需要标记
		}
	}
	// 显示所有用户
	public void showUsers() {		
		System.out.println("\n手机号\t\t密码\t\t用户名\t\t身份\t\t卡号");
		for(int i=0; i<users.size(); i++) {
			User user = users.get(i);
			String roleName ;
			switch(user.role) {
				case 0:
					roleName = "卡用户";
					break;
				case 1:
					roleName = "卡业务员";
					break;
				case 2:
					roleName = "系统管理员";
					break;
				default:
					roleName = "卡用户";
			}
			System.out.println(user.mobile+"\t\t"+user.password+"\t\t"
					+user.userName+"\t\t"+roleName+"\t\t"+user.cno);
		}
	}
	@Override 
	public void saveDatas() {
		if( !change) return ;		
		System.out.println("用户信息已经被修改，是否保存？(Y/N)");
		String yn = scanner.next();
		if ( yn.equalsIgnoreCase("Y")) {
			userDatas.saveUsers();
			if( delete) { //如果是删除用户，还需要删除卡、交易记录
				cardDatas.saveCards();
				transDatas.saveTransaction();				
			}
		}
		// 恢复标记
		change = false;
		delete = false;
	}	
}