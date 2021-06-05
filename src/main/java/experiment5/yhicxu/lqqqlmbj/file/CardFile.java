package experiment5.yhicxu.lqqqlmbj.file;

import experiment5.yhicxu.lqqqlmbj.entity.Card;

import java.util.ArrayList;
import java.util.Date;
// 校园卡数据的读取与保存
public class CardFile extends FileHandler {
	
	public CardFile() {	
		super("cards.dat");
	}
	public CardFile(String filename) {		
		super(filename);
	}
	// 读取文件数据保存在cards中
	public ArrayList<Card> acquire() {
		datas.clear(); // 读取数据前先清除datas
		read(); // 读取数据
		if(datas == null) return null;
		ArrayList<Card> cards = new ArrayList<Card>();
		for(int i=0; i<datas.size(); i++) {			
			Card card = dataFromString(datas.get(i));
			if(card != null)
				cards.add(card);
		}
		return cards;
	}
	// 将cards中数据写入文件
	public void save(ArrayList<Card> cards) {		
		if(cards.isEmpty()) return ;
		datas.clear(); // 先清除
		for(int i=0; i<cards.size(); i++) {
			datas.add(dataToString(cards.get(i)));
		}
		super.write(); // 写入文件
	}
	// 将读取的字符串数据转换成Card对象
	public Card dataFromString(String line) {
		Card card = null;
		// 数据按照separator分隔符分隔
		String[] item = line.split(separator);
		if(item.length == 5) {
			Date date = null;
			try {
				// 时间格式转换
				date = sdf.parse(item[2]);				
			}
			catch(Exception e) {
				e.printStackTrace();
			}			
			card = new Card(item[0], item[1], date, Double.parseDouble(item[3]),
					Integer.parseInt(item[4]));			
		}
		return card;
	}
	// 将Card对象数据转换成字符串
	public String dataToString(Card card) {
		String line=null;
		if(card != null) {	
			// 使用separator符号连接数据项
			line = card.cno+separator+card.mobile+separator
					+sdf.format(card.time)+separator					
					+card.balance+separator+card.status+separator+'\n';
		}
		return line;
	}
}