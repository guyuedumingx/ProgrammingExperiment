package experiment5.ww.util;

/**
 * 用来生成no字段
 * @author yohoyes
 * @date 2021/5/20 10:19
 */
public class NoUtil {
    private static int RANDOM_SIZE = 4;

    public static String build(String name) {
        char[] chars = name.toLowerCase().toCharArray();
        StringBuilder sb = new StringBuilder();
        for(char ch : chars) {
            sb.append(ch);
        }
        sb.append(rand());
        return sb.toString();
    }

    public static String rand() {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i< RANDOM_SIZE; i++) {
            int random = (int) Math.random();
            sb.append(random);
        }
        return sb.toString();
    }
}
