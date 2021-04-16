package experiment3.ww;

import util.CharUtil;

import java.util.Map;

/**
 * @author yohoyes
 * @date 2021/4/17 2:20
 */
public class Huffman {

    public static void main(String[] args) {
        Map<Character, Integer> characterIntegerMap = CharUtil.countForMap("experiment3/Demo.txt");
    }
}
