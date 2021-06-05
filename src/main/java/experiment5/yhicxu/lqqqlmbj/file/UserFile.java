package experiment5.yhicxu.lqqqlmbj.file;
import experiment5.yhicxu.lqqqlmbj.entity.User;

import java.util.ArrayList;

// 用户文件数据的读取与保存
public class UserFile extends FileHandler{	
	
	public UserFile() {
		super("users.dat");
	}
	public UserFile(String filename) {		
		super(filename);
	}
	// 读取文件数据保存在users中
	public ArrayList<User> acquire() {
		datas.clear(); // 读取数据前先清除datas
		read(); // 读取数据
		if(datas == null) return null;
		ArrayList<User> users = new ArrayList<User>();
		for(int i=0; i<datas.size(); i++) {			
			User user = dataFromString(datas.get(i));
			if(user != null)
				users.add(user);
		}
		return users;
	}
	// 将users中数据写入文件
	public void save(ArrayList<User> users) {		
		if(users.isEmpty()) return ;
		datas.clear(); // 先清除
		for(int i=0; i<users.size(); i++) {
			datas.add(dataToString(users.get(i)));
		}
		super.write(); // 写入文件
	}
	// 将读取的字符串数据转换成用户对象
	private User dataFromString(String line) {
		User user = null;
		// 数据按照separator分隔符分隔
		String[] item = line.split(separator);		
		String cno = "";
		if(item.length == 5) cno = item[4];
		// 用户没有卡号时，数组长度为4
		if(item.length >=4 ) { 
			user = new User(item[0],item[1],item[2],Integer.parseInt(item[3]),cno);			
		}
		return user;
	}
	// 将用户对象数据转换成字符串
	private String dataToString(User user) {
		String line=null;
		if(user != null) {
			// 使用separator符号连接数据项
			line = user.mobile+separator+user.password+separator
					+user.userName+separator+user.role+separator+user.cno+separator+'\n';
		}
		return line;
	}	
	
}