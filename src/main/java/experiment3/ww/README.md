### 哈夫曼树  

首先需要统计`Demo.txt`中各个字符的出现频率  
`util`包下的`CharUtil`包含了一个`countForMap`的方法用来统计文件字符的频率  

##### countForMap

```java
/**
 * 统计resource文件中的各种字符的频率
 * @param resource 文件位置 比如： experiment3/Demo.txt
 * @return
 */
public static Map<Character, Integer> countForMap(String resource) {
        InputStream in = null;
        //结果集
        Map<Character, Integer> map = new HashMap<>();
        try {
            String s = Resource.get(resource);
            in = new FileInputStream(s);
            int cur;
            //每次读取一个字符直到文件末尾
            while ((cur = in.read()) != -1){
                //因为读入的是int类型，所以转成char类型
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
```

##### 详解  
```java
Integer nums = map.get(curChar);
if(nums == null) {
    nums = 0;
}
map.put(curChar,nums+1);
```
这段代码用来读取`map`中是否已经遍历过这个字符了，
也就是看之前的字符串中有没用出现过这个字符，如果没有的话，
初始化计数(`nums = 0`)  

```java
in = new FileInputStream(s);
```
不仅仅可以用`FileInputStream`来作为输入流，如果你会用`Scanner`也是可以的  

------------------ 

统计出字符串的频率之后，接下来需要我们依次取出最小的两个值用来生成子树  