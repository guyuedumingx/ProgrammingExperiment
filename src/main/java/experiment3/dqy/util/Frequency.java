package experiment3.dqy.util;

public class Frequency {
    int[] cnt = new int[256];
    int size;

    //x的数量增加
    void add(char x) {
        if(cnt[x] == 0) size++;
        cnt[x]++;
    }

    //返回字符x出现的数量
    int get(char x) {
        return cnt[x];
    }

    //返回整个map的信息，用二维数组存，下标0存键，下标1存值
    int[][] getAll() {
        int[][] mp = new int[size][2];
        //索引当前存到第几个值
        int idx = 0;
        for (int i = 0 ; i < 256; i++) {
            //如果存在这个键则存入
            if (cnt[i] != 0) {
                mp[idx][0] = i;
                mp[idx][1] = cnt[i];
                idx++;
            }
        }
        return mp;
    }

    //遍历整个map输出对应关系
    void showFrequency() {
        for (int i = 0 ; i < 256; i++) {
            //如果存在这个键则输出
            if (cnt[i] != 0) {
                System.out.println(i + " : " + cnt[i]);
            }
        }
    }
}
