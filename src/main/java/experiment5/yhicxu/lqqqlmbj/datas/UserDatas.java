package experiment5.yhicxu.lqqqlmbj.datas;

import experiment5.yhicxu.lqqqlmbj.entity.User;
import experiment5.yhicxu.lqqqlmbj.file.UserFile;

import java.util.ArrayList;

// 此类完成对users对象中数据进行查找、添加、修改、删除等操作
public class UserDatas {
	private static ArrayList<User> users = null; // 所有用户信息保存在users对象
	
	// 根据moblie查找用户，返回用户的索引位置
	public int findUser(String mobile) {
		if(users == null) return -1;	
		if(mobile==null) return -1;				
		int index = -1;
		for(int i=0; i<users.size(); i++) {			
			if( mobile.equals(users.get(i).mobile)) {
				index = i;
				break;
			}
		}
		return index;
	}
	// 新增一个用户
	public void addUser(User user) {
		users.add(user);
	}
	// 修改users中一个用户信息
	public void modifyUser(User user) {		
		int index = findUser(user.mobile); // 查找user在users中的索引位置
		users.set(index, user); // 修改索引位置中数据
	}
	// 删除users中用户
	public void removeUser(User user) {
		int index = findUser(user.mobile); // 查找user在users中的索引位置
		users.remove(index); // 删除	
	}
	// users对象数据保存到文件
	public void saveUsers() {		
		UserFile file = new UserFile();
		file.save(users);
	}	
	
	// 获取用户信息，首次从文件读取
	public static ArrayList<User> getUsers() {
		if(users == null) {
			UserFile file = new UserFile();			
			users = file.acquire();
		}
		return users;
	}
	// 清除数据
	public static void clear() {
		users = null;
	}
}