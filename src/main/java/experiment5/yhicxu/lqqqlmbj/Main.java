package experiment5.yhicxu.lqqqlmbj;

import experiment5.yhicxu.lqqqlmbj.entity.User;
import experiment5.yhicxu.lqqqlmbj.file.UserFile;
import experiment5.yhicxu.lqqqlmbj.ui.Action;

import java.util.ArrayList;

public class Main {
	
	public static void main(String[] args) {
		//init();//首次运行初始化数据
		Action action = new Action();
		action.start();
	}
	
	// 首次使用系统是初始化数据
	public static void init() {
		User user = new User("admin", "admin", "admin", 2, " ");
		ArrayList<User> users = new ArrayList<User>();
		users.add(user);
		UserFile userDatas = new UserFile();
		userDatas.save(users);
	}

}