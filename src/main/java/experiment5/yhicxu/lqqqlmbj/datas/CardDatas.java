package experiment5.yhicxu.lqqqlmbj.datas;

import experiment5.yhicxu.lqqqlmbj.entity.Card;
import experiment5.yhicxu.lqqqlmbj.file.CardFile;

import java.util.ArrayList;

//此类完成对cards对象中数据进行查找、添加、修改等操作
public class CardDatas {	
	private static ArrayList<Card> cards = null; // 所有卡信息保存在cards对象

	// 根据cno查找校园卡，返回索引位置
	public int findCard(String cno) {
		if(cno==null) return -1;		
		if(cards == null) return -1;		
		int index = -1;
		for(int i=0; i<cards.size(); i++) {			
			if( cno.equals(cards.get(i).cno)) {
				index = i;
				break;
			}
		}
		return index;
	}
	// 新增一张校园卡
	public void addCard(Card card) {
		if(cards == null ) cards = new ArrayList<Card>();
		cards.add(card);
	}
	// 修改cards中信息
	public void modifyCard(Card card) {		
		int index = findCard(card.cno); // 查找users中的索引位置
		cards.set(index, card); // 修改索引位置中数据
	}
	//  删除cno卡，用于删除用户时的级联删除
	public void removeCard(String cno) {		
		int index = findCard(cno); // 查找users中的索引位置
		cards.remove(index); // 修改索引位置中数据
	}
	
	// cards对象数据保存到文件
	public void saveCards() {		
		CardFile file = new CardFile();
		file.save(cards);
	}
	
	// 获取校园卡信息，首次从文件读取
	public static ArrayList<Card> getCards(){
		if(cards == null) {
			CardFile file = new CardFile();
			cards = file.acquire();
		}		
		return cards;
	}
	// 清除数据
	public static void clear() {
		cards = null;
	}
}