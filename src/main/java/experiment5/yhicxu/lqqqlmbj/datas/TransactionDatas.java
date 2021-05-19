package experiment5.yhicxu.lqqqlmbj.datas;

import experiment5.yhicxu.lqqqlmbj.entity.Transaction;
import experiment5.yhicxu.lqqqlmbj.file.TransactionFile;

import java.util.ArrayList;

//此类完成对transactions对象中数据进行查找、添加等操作
public class TransactionDatas {
	public static ArrayList<Transaction> transactions = null; // 所有交易记录信息
	
	// 根据flowNo、cno查找交易记录，返回交易记录的索引位置
	public int findTransaction(int flowNo, String cno) {
		if(flowNo<0 || cno==null) return -1;
		if(transactions == null) return -1;		
		int index = -1;
		for(int i=0; i<transactions.size(); i++) {			
			if( flowNo==transactions.get(i).flowNo && 
					cno.equals(transactions.get(i).cno)) {
				index = i;
				break;
			}
		}
		return index;
	}
	// 查询cno的所有交易记录
	public ArrayList<Transaction> findCardTransaction(String cno){		
		if(transactions == null) return null;
		if(cno==null) return null;
		
		ArrayList<Transaction> trans = new ArrayList<Transaction>();
		for(int i=0; i<transactions.size(); i++) {			
			if( cno.equals(transactions.get(i).cno)) {
				trans.add(transactions.get(i));				
			}
		}
		return trans;
	}
	
	// 获得cno卡最后一次交易的流水号
	public int lastCardFlowNo(String cno){
		if(cno==null) return -1;		
		if(transactions == null) return 0;
		int last = 0; // 最后一次交易的流水号
		for(int i=0; i<transactions.size(); i++) {			
			if( cno.equals(transactions.get(i).cno)) {
				if(last < transactions.get(i).flowNo)
					last = transactions.get(i).flowNo;
			}
		}
		return last;
	}
	// 新增一条交易记录
	public void addTransaction(Transaction transaction) {
		if(transactions == null) transactions = new ArrayList<Transaction>();
		transactions.add(transaction);
	}
	// 删除cno卡的所有交易记录，用于删除用户时的级联删除
	public void removeTransactionByCno(String cno) {
		for(int i=transactions.size()-1; i>=0; i--) {
			if( cno.equals(transactions.get(i).cno) )
				transactions.remove(i);
		}
	}	
	// transactions对象数据保存到文件
	public void saveTransaction() {		
		TransactionFile file = new TransactionFile();
		file.save(transactions);
	}
	
	// 获取交易记录信息，首次从文件读取
	public static ArrayList<Transaction> getTransactions(){
		if(transactions == null) {
			TransactionFile file = new TransactionFile();			
			transactions = file.acquire();
		}
		return transactions;
	}
	// 清除数据
	public static void clear() {
		transactions = null;
	}
}