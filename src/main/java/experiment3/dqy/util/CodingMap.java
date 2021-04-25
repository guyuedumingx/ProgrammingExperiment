package experiment3.dqy.util;

public class CodingMap {
    String[] map = new String[256];
    int size;

    //x的数量增加
    void add(char x, String s) {
        if(map[x] == null) size++;
        map[x] = s;
    }

    //返回字符x出现的数量
    String get(char x) {
        return map[x];
    }

//    int[][] getAll() {
//        int[][] mp = new int[size][2];
//        int idx = 0;
//        for (int i = 0 ; i < 256; i++) {
//            //如果存在这个键则输出
//            if (cnt[i] != 0) {
//                mp[idx][0] = i;
//                mp[idx][1] = cnt[i];
//                idx++;
//
//            }
//        }
//        return mp;
//    }

    //遍历整个map输出对应关系
    void showFrequency() {
        for (int i = 0 ; i < 256; i++) {
            //如果存在这个键则输出
            if (map[i] != null) {
                System.out.println(i + " : " + map[i]);
            }
        }
    }
}
