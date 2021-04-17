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
那么怎么依次取出最小的两个值呢？排序？当然可以，当时有一种结构叫优先队列(`PriorityQueue`)
这玩意可以自动帮我们把传进去的对象进行排序，只要我们传进去的对象实现了`Comparable`方法，它就能自
动工作了  
那么首先我们先要有树的节点类  

```java
class Node implements Comparable<Node> {
    private Character key;
    private Integer nums;
    private String code;
    private Node left;
    private Node right;

    public Node(Node left, Node right) {
        this.left = left;
        this.right = right;
        this.nums = left.nums + right.nums;
    }

    public Node(Character key, Integer nums) {
        this.key = key;
        this.nums = nums;
    }

    public Character getKey() {
        return key;
    }

    public void setKey(Character key) {
        this.key = key;
    }

    public Integer getNums() {
        return nums;
    }

    public void setNums(Integer nums) {
        this.nums = nums;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    /**
     * 用来给优先队列比较的
     * @param node
     * @return
     */
    @Override
    public int compareTo(Node node) {
        if (nums > node.nums) {
            return 1;
        } else if (nums == node.nums) {
            return 0;
        }
        return -1;
    }

    @Override
    public String toString() {
        return "Node{" +
                "key=" + key +
                ", nums=" + nums +
                ", code='" + code + '\'' +
                ", left=" + left +
                ", right=" + right +
                '}';
    }
}
```

有了节点类之后，我们就要根据之前统计出来的字符频率生成优先队列了  
```java
Map<Character, Integer> map = CharUtil.countForMap("experiment3/Demo.txt");
Set<Character> characters = map.keySet();
PriorityQueue<Node> queue = new PriorityQueue<>();

for(Character character : characters) {
    Node node = new Node(character, map.get(character));
    queue.add(node);
}
```
现在`queue`就是我们的优先队列，有了它，我们就可以每次取出最小的两个值，进行组装，形成我们想要的`huffman树`了  

```java
while (queue.size() > 1) {
    Node min1 = queue.poll();
    Node min2 = queue.poll();
    Node parent = new Node(min1, min2);
    queue.add(parent);
}
Node root = queue.poll();
```
`huffman树`建好之后，`root`节点就是我们想要的根节点，有了根节点。接下来就是为每个节点设置`huffman编码`  

```java
public static void buildCode(Node node, String code) {
    if (node == null) {
        return;
    }
    node.setCode(code);
    buildCode(node.getLeft(), code+"0");
    buildCode(node.getRight(), code+"1");
}
```
只要在主方法中简单的调用一下以上的方法，就能帮我们自动编码了  
```java
Node root = queue.poll();
buildCode(root, "");
```