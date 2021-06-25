package experiment5.ww;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import experiment5.ww.controller.MainController;
import experiment5.ww.db.Database;
import experiment5.ww.db.impl.DatabaseImpl;
import experiment5.ww.pojo.Card;
import experiment5.ww.util.DbUtil;
import experiment5.ww.util.NoUtil;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Scanner;

/**
 * 程序入口
 * @author yohoyes
 * @date 2021/5/20 18:51
 */
public class Main {

    private static Scanner in = new Scanner(System.in);
    private static Database<Card> db = DbUtil.getCardDB();
    private static String[] nameList = {"aa", "bb", "cc"};

    public static void main(String[] args) {
        read();
        List<Card> list = db.selectAll();
        for(Card card : list){
            System.out.println(card);
        }
        MainController controller = new MainController(in);
        while(!controller.login()){}
        controller.showOpera();

    }

    private static ObjectMapper mapper = new ObjectMapper();

    public static void read() {
        StringBuilder sb = new StringBuilder();
        Scanner reader = null;
        try {
            reader = new Scanner(new FileReader(DbUtil.FILE));
            while (reader.hasNext()){
                sb.append(reader.next());
            }
            if(!"".equals(sb.toString())){
                JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class, Card.class);
                List<Card> list = mapper.readValue(sb.toString(),javaType);
                for(Card card : list) {
                    db.insert(card);
                }
            }
        }catch (IOException e) {
            e.printStackTrace();
            reader.close();
        }
    }
}
