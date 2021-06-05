package experiment5.yhicxu.lqqqlmbj.service;

import experiment5.yhicxu.lqqqlmbj.datas.CardDatas;
import experiment5.yhicxu.lqqqlmbj.datas.TransactionDatas;
import experiment5.yhicxu.lqqqlmbj.datas.UserDatas;
import experiment5.yhicxu.lqqqlmbj.entity.Card;
import experiment5.yhicxu.lqqqlmbj.entity.Transaction;
import experiment5.yhicxu.lqqqlmbj.entity.User;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

//业务功能类，它是ManagerService、OperatorService和CustomerService的父类
public class UserService {	
	public static ArrayList<User> users = null;
	public static ArrayList<Card> cards = null;
	public static ArrayList<Transaction> transactions = null;
	public static int curUserIndex = -1;// 当前操作用户索引
	
	public boolean change = false; // 标记业务操作是否更改数据  false:未更改  true：更改
	private Scanner scanner = new Scanner(System.in);
	protected UserDatas userDatas = new UserDatas();
	protected CardDatas cardDatas = new CardDatas();
	protected TransactionDatas transDatas = new TransactionDatas();

	public DecimalFormat df = new DecimalFormat("#.00"); // 用于格式化金额
	public SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 用于格式化时间
		
	public void getDatas() {
		users = UserDatas.getUsers();
		cards = CardDatas.getCards();
		transactions = TransactionDatas.getTransactions();
	}

	public int getCurUserIndex() {
		return curUserIndex;
	}
	// 登录
	public boolean login() {
		System.out.println("mobile:");
		String mobile = scanner.next();
		System.out.println("password:");
		String password = scanner.next();	
		int index = userDatas.findUser(mobile);
		if(index < 0) {
			System.out.println("用户不存在！");
			return false;
		}
		User user =  users.get(index);	
		boolean pass =user.password.equals(password);
		if(!pass) {
			System.out.println("用户密码错误！");
			return false;
		}
		curUserIndex = index;
		return true;
	}
	// 修改密码
	public boolean modifyPassword() {
		User user = users.get(curUserIndex);
		System.out.println("请输入原密码：");
		String oldPwd = scanner.next();
		if(!oldPwd.equals(user.password)) {
			System.out.println("原密码不正确！");
			return false;
		}
		System.out.println("请输入新密码：");
		String newPwd = scanner.next();
		System.out.println("再次确认密码：");
		String newPwd2 = scanner.next();
		if(!newPwd.equals(newPwd2)) {
			System.out.println("两个输入的密码不同，修改密码失败\n");
			return false;
		}
		user.password = newPwd;
		users.set(curUserIndex, user);
		System.out.println("密码修修改成功！");
		change = true; // 数据更改
		return true;			
	}
	// 用户注销
	public void logout() {
		// 保存数据
		saveDatas();
		// 清空全局变量/对象数据		
		UserDatas.clear();		
		CardDatas.clear();
		TransactionDatas.clear();
		curUserIndex = -1;
	}
	// 用户退出
	public void exit() {
		// 保存数据
		saveDatas();
		// 程序终止
		System.exit(0);
	}
	/** 保存数据，由于系统管理员、卡业务员、卡用户保存数据的操作不一样
	 *  程序逻辑在各个子类中实现
	 */
	public void saveDatas() {
		
	}
}