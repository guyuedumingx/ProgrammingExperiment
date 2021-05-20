package experiment5.yhicxu.lqqqlmbj.service;

import experiment5.yhicxu.lqqqlmbj.entity.Card;
import experiment5.yhicxu.lqqqlmbj.entity.Transaction;
import experiment5.yhicxu.lqqqlmbj.entity.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

// 此类实现卡用户的业务功能
public class CustomerService extends UserService {	
	// 在此创建，类中所有方法均可使用
	private Scanner scanner = new Scanner(System.in);
	
	// 给校园卡充值
	public boolean recharge() {
		// 获得当前登录用户对象
		User user = users.get(curUserIndex);
		if(user.cno==null || user.cno.equals("")){
			System.out.println("当前用户的还为开卡，不能充值!");
			return false;
		}
		// 获得当前用户的校园卡
		int cardIndex = cardDatas.findCard(user.cno); // 获得校园卡索引位置	
		Card card = cards.get(cardIndex);
		if (card.status != 1){
			System.out.println("当前用户的校园卡处于挂失状态或不正常，不能充值!");
			return false;
		}
		System.out.println("请输入充值金额：");
		double amount = scanner.nextDouble();
		card.balance += amount; //余额增加
		//Globals.getCards().set(cardIndex, card); //可不需要
		
		//保存充值记录
		int lastFlowNo = transDatas.lastCardFlowNo(user.cno); //获得cno卡的最后流水号		
		Transaction trans = new Transaction(++lastFlowNo, user.cno, new Date(), amount, 1);
		transDatas.addTransaction(trans);
		// 标记状态
		change = true;
		return true;
	}
	// 校园卡消费
	public boolean consume() {
		User user = users.get(curUserIndex);
		int cardIndex = cardDatas.findCard(user.cno);
		Card card = cards.get(cardIndex);
		if (card.status != 1){
			System.out.println("当前用户的校园卡处于挂失状态或不正常，不能消费!");
			return false;
		}
		System.out.println("请输入消费金额：");
		double amount = scanner.nextDouble();
		if(card.balance < amount) {
			System.out.println("校园卡金额不足，不能消费!");
			return false;
		}
		card.balance -= amount;
		//Globals.getCards().set(cardIndex, card);//可不需要
		cardDatas.modifyCard(card);		
		//保存消费记录
		int lastFlowNo = transDatas.lastCardFlowNo(user.cno);		
		Transaction trans = new Transaction(++lastFlowNo, user.cno, new Date(), amount, 0);
		transDatas.addTransaction(trans);
		// 标记状态
		change = true;
		return true;
	}
	// 显示用户卡信息
	public void showCard() {
		User user = users.get(curUserIndex);
		int cardIndex = cardDatas.findCard(user.cno);
		if(cardIndex < 0) return;
		Card card = cards.get(cardIndex);
		System.out.println("\n卡号\t\t开卡时间\t\t\t余额\t\t状态");
		String sStatus = (card.status == 1)?"正常":"挂失";
		System.out.println(card.cno+"\t\t"+sdf.format(card.time)+"\t"
				+df.format(card.balance)+"\t\t"+sStatus);
	}
	// 显示交易记录
	public void showTransaction() {
		User user = users.get(curUserIndex);
		ArrayList<Transaction> trans = transDatas.findCardTransaction(user.cno);
		System.out.println("\n流水号\t\t卡号\t\t交易时间\t\t\t交易额\t\t交易类型");
		if(trans == null) 
			return;
		for(int i=0; i<trans.size(); i++) {
			Transaction tran = trans.get(i);
			String sType = tran.type==0?"消费":"充值";
			System.out.println("\n"+tran.flowNo+"\t\t"+tran.cno+"\t\t"
					+sdf.format(tran.dealTime)+"\t"
					+df.format(tran.amount)+"\t\t"+sType);
		}		
	}
	@Override 
	public void saveDatas() {
		if( !change) return ;		
		System.out.println("交易信息已经被修改，是否保存？(Y/N)");
		String yn = scanner.next();
		if ( yn.equalsIgnoreCase("Y")) {
			transDatas.saveTransaction();				
			cardDatas.saveCards();	// 还需要删除卡中余额
		}
		// 恢复标记
		change = false;
	}
	
}