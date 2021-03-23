package util;

import java.util.Random;

/**
 * 车牌工具类
 * 随机生成一个车牌
 * @author yohoyes
 * @date 2021/3/23 21:38
 */
public class CarNoUtil {
    private static String[] citys = {"津", "京", "宁", "鄂", "粤", "沪"};

    /**
     * 生成五位的车牌
     * @return
     */
    public static String build() {
        return build(5);
    }

    /**
     * @param numbers 多少位的数字
     * @return
     */
    public static String build(int numbers) {
        String city = citys[(new Random().nextInt(citys.length))];
        //下面开始：拼接车牌号，并随机产生0到9的6位车牌号
        StringBuilder plateNumStr = new StringBuilder(city);
        for(int i=0; i<numbers; i++) {
            plateNumStr.append(new Random().nextInt(10));
        }
        return plateNumStr.toString();
    }
}
