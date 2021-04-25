package experiment3.dqy.util;

public class CodingMap {
    String[] map = new String[256];
    int size;

    //x的数量增加
    public void add(char x, String s) {
        if(map[x] == null) size++;
        map[x] = s;
    }

    //返回字符x出现的数量
    public String get(char x) {
        return map[x];
    }

    //遍历整个map输出对应关系
    public void showCodingMap() {
        for (int i = 0 ; i < 256; i++) {
            //如果存在这个键则输出
            if (map[i] != null) {
                System.out.println((char)i + " : " + map[i]);
            }
        }
    }
}
