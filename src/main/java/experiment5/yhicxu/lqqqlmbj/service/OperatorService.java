package experiment5.yhicxu.lqqqlmbj.service;

import experiment5.yhicxu.lqqqlmbj.entity.Card;
import experiment5.yhicxu.lqqqlmbj.entity.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

//实现卡业务员的业务功能
public class OperatorService extends UserService{
	public boolean add = false; // 开卡时，同时修改用户的卡号，需要单独标记
	private Scanner scanner = new Scanner(System.in);
	// 查看所有校园卡
	public void showCards() {
		System.out.println("\n卡号\t\t开卡时间\t\t\t余额\t\t状态");
		if(cards.isEmpty()) return;			
		for(int i=0; i<cards.size(); i++) {
			Card card = cards.get(i);
			String sStatus = (card.status == 1)?"正常":"挂失";
			System.out.println(card.cno+"\t\t"+sdf.format(card.time)+"\t"
					+df.format(card.balance)+"\t\t"+sStatus);
		}
	}
	// 开卡
	public boolean createCard() {
		// 输入有效的用户（mobile)，有效用户是指用户存在且未开卡 
		System.out.println("请输入开卡的用户手机号(11位)");
		String mobile = scanner.next();		
		int index = userDatas.findUser(mobile);
		if (index < 0){
			System.out.println("用户不存在\n");
			return false;
		}
		User user = users.get(index);
		if ( !user.cno.equals("")){
			System.out.println("用户已开卡\n");
			return false;
		}		
		// 输入有效（即未使用）的卡号 
		System.out.println("请输入新卡号(9位):");
		String cno = scanner.next();		
		if (cardDatas.findCard(cno) >= 0){
			System.out.println("卡号已被使用\n");
			return false;
		}
		// 开卡初始余额100.00
		Card card = new Card(cno, mobile, new Date(), 100.00, 1);
		cardDatas.addCard(card);		
		// 修改用户的卡号
		user.cno = cno;
		userDatas.modifyUser(user);
		// 标记状态
		change = true;
		add = true;
		return true;		
	}
	// 挂失
	public boolean frozenCard() {
		System.out.println("请输入要挂失的卡号：");
		String cno = scanner.next();		
		int index = cardDatas.findCard(cno);
		if (index < 0){
			System.out.println("卡号不存在！");
			return false;
		}		
		Card card = cards.get(index);
		if (card.status == 0){
			System.out.println("卡号已挂失！");
			return false;
		}
		card.status = 0;
		cardDatas.modifyCard(card);		
		change = true; // 数据更改
		return true;
	}
	// 解挂
	public boolean activateCard() {
		System.out.println("请输入要解挂的卡号：");
		String cno = scanner.next();		
		int index = cardDatas.findCard(cno);
		if (index < 0){
			System.out.println("卡号不存在！");
			return false;
		}		
		Card card = cards.get(index);
		if (card.status == 1){
			System.out.println("卡号已正常！");
			return false;
		}
		card.status = 1;
		cardDatas.modifyCard(card);
		change = true; // 数据更改
		return true;
	}
	
	@Override 
	public void saveDatas() {
		if( !change) return ;		
		System.out.println("卡信息已经被修改，是否保存？(Y/N)");
		String yn = scanner.next();
		if ( yn.equalsIgnoreCase("Y")) {
			cardDatas.saveCards();
			if( add) { //如果是开卡，还需要修改用户信息
				userDatas.saveUsers();;							
			}
		}
		// 恢复标记
		change = false;
		add = false;
	}
}