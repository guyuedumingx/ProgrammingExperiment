package experiment5.yhicxu.lqqqlmbj.file;

import experiment5.yhicxu.lqqqlmbj.entity.Transaction;

import java.util.ArrayList;
import java.util.Date;

// 交易记录数据的读取与保存
public class TransactionFile extends FileHandler{
	
	public TransactionFile() {	
		super("transactions.dat");
	}
	public TransactionFile(String filename) {		
		super(filename);
	}
	// 读取文件数据保存在transactions中
	public ArrayList<Transaction> acquire() {
		datas.clear(); // 读取数据前先清除datas
		read(); // 读取数据
		if(datas == null) return null;
		ArrayList<Transaction> transactions = new ArrayList<Transaction>();
		for(int i=0; i<datas.size(); i++) {
			Transaction transaction = dataFromString(datas.get(i));			
			if(transaction != null)
				transactions.add(transaction);
		}
		return transactions;
	}
	// 将transactions中数据写入文件
	public void save(ArrayList<Transaction> transactions) {		
		if(transactions.isEmpty()) return ;
		datas.clear(); // 先清除
		for(int i=0; i<transactions.size(); i++) {
			datas.add(dataToString(transactions.get(i)));
		}
		super.write(); // 写入文件
	}
	// 将读取的字符串数据转换成Transaction对象
	public Transaction dataFromString(String line) {
		Transaction transaction = null;
		// 数据按照separator分隔符分隔
		String[] item = line.split(separator);
		if(item.length == 5) {
			Date time = null;			
			try {
				// 时间格式转换
				time = sdf.parse(item[2]);				
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			int flowNo = Integer.parseInt(item[0]);
			double amount = Double.parseDouble(item[3]);
			int type = Integer.parseInt(item[4]);
			transaction = new Transaction(flowNo, item[1], time, amount, type);			
		}
		return transaction;
	}
	// 将Transaction对象数据转换成字符串
	public String dataToString(Transaction transaction) {
		String line=null;
		if(transaction != null) {	
			// 使用separator符号连接数据项
			line = transaction.flowNo+separator+transaction.cno+separator
					+sdf.format(transaction.dealTime)+separator					
					+transaction.amount+separator+transaction.type+separator+'\n';
		}
		return line;
	}
}