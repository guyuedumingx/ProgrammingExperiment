package util;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * 字符工具类，目前的用途有：
 *      用于实验3统计demo.txt中的各种字符数
 * @author yohoyes
 * @date 2021/4/17 2:13
 */
public class CharUtil {

    /**
     * 统计resource文件中的各种字符的频率
     * @param resource 文件位置 比如： experiment3/Demo.txt
     * @return
     */
    public static Map<Character, Integer> countForMap(String resource) {
        InputStream in = null;
        Map<Character, Integer> map = new HashMap<>();
        try {
            String s = Resource.get(resource);
            in = new FileInputStream(s);
            int cur;
            while ((cur = in.read()) != -1){
                char curChar = (char)cur;
                Integer nums = map.get(curChar);
                if(nums == null) {
                    nums = 0;
                }
                map.put(curChar,nums+1);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }
}
