package experiment5.yhicxu.lqqqlmbj.ui;

import experiment5.yhicxu.lqqqlmbj.entity.User;
import experiment5.yhicxu.lqqqlmbj.service.CustomerService;
import experiment5.yhicxu.lqqqlmbj.service.ManagerService;
import experiment5.yhicxu.lqqqlmbj.service.OperatorService;
import experiment5.yhicxu.lqqqlmbj.service.UserService;

import java.util.Scanner;

// 系统中菜单操作的逻辑代码
public class Action {
	UserService usersService = new UserService();
	ManagerService managerService = new ManagerService();
	OperatorService operatorSerivce = new OperatorService();
	CustomerService customerService = new CustomerService();
	private Scanner scanner = new Scanner(System.in);
	
	// 系统启动时，显示菜单，并提供相应操作
	public void start() {
		
		while(true) {
			Menu.preLoginMenu(); // 显示登录前菜单项
			int item = scanner.nextInt();
			if(item == 1 ) {				
				usersService.getDatas(); // 获取数据
				
				boolean pass = usersService.login(); // 用户登录
				if(! pass)
					continue;
				User user = usersService.users.get(usersService.getCurUserIndex());
				if(user.role == 0) { // 卡用户登录
					customerAction();
				}
				else if(user.role == 1){ // 卡业务员登录
					operatorAction();					
				}
				else if(user.role == 2){ // 系统管理员登录
					managerAction();
				}
			}
			else if(item ==2) {
				usersService.logout();
				break;
			}
		}		
	}
	
	// 系统管理员的菜单项及其操作逻辑
	public void managerAction() {
		boolean flag = false;
		while(true) {
			Menu.managerMenu(); // 显示系统管理员菜单项
			int item = scanner.nextInt();
			switch(item) {
				case 1:
					managerService.modifyPassword(); // 修改密码
					break;
				case 2:
					managerService.showUsers(); // 显示所有用户
					break;
				case 3:
					managerService.addUser(); // 新增用户
					break;
				case 4:
					managerService.removeUser(); // 删除用户				
					break;
				case 5:
					managerService.logout(); // 注销
					flag = true;
					break;
				case 6:
					managerService.exit(); // 退出程序
					flag = true;
					break;
			}
			if(flag)
				break;
		}
	}
	// 卡业务员菜单项及其操作逻辑
	public void operatorAction() {
		boolean flag = false;
		while(true) {
			Menu.operatorMenu(); // 卡业务员菜单项
			int item = scanner.nextInt();
			switch(item) {
			case 1:
				operatorSerivce.modifyPassword(); // 修改密码
				break;
			case 2:
				operatorSerivce.showCards(); // 显示所有卡信息
				break;
			case 3:
				operatorSerivce.createCard(); // 开卡
				break;
			case 4:
				operatorSerivce.frozenCard(); // 挂失
				break;
			case 5:
				operatorSerivce.activateCard();	// 解挂			
				break;
			case 6:
				operatorSerivce.logout(); // 注销
				flag = true;
				break;
			case 7:
				operatorSerivce.exit(); // 退出
				flag = true;
				break;
		}
		if(flag)
			break;
		}
	}
	// 卡用户菜单及其操作逻辑
	public void customerAction() {
		boolean flag = false;
		while(true) {
			Menu.customerMenu(); // 卡用户菜单
			int item = scanner.nextInt();
			switch(item) {
			case 1:
				customerService.modifyPassword(); // 修改密码
				break;
			case 2:
				customerService.recharge(); // 充值
				break;
			case 3:
				customerService.consume(); // 消费
				break;
			case 4:
				customerService.showCard(); // 显示卡信息				
				break;
			case 5:
				customerService.showTransaction(); // 显示交易记录
				break;
			case 6:
				customerService.logout(); // 注销
				flag = true;
				break;
			case 7:
				customerService.exit(); // 退出
				flag = true;
				break;
		}
		if(flag)
			break;
		}
	}
}