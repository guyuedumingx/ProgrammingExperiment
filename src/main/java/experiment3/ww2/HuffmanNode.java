package experiment3.ww2;

/**
 * @author yohoyes
 * @date 2021/5/31 10:21
 */
public class HuffmanNode implements Comparable<HuffmanNode> {

    private Character key;
    private Integer nums;
    private String code;
    private HuffmanNode left;
    private HuffmanNode right;

    public HuffmanNode(){}
    public HuffmanNode(HuffmanNode left, HuffmanNode right) {
        this.left = left;
        this.right = right;
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

    public HuffmanNode getLeft() {
        return left;
    }

    public void setLeft(HuffmanNode left) {
        this.left = left;
    }

    public HuffmanNode getRight() {
        return right;
    }

    public void setRight(HuffmanNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HuffmanNode{" +
                "key=" + key +
                ", nums=" + nums +
                ", code='" + code + '\'' +
                ", left=" + left +
                ", right=" + right +
                '}';
    }

    @Override
    public int compareTo(HuffmanNode node) {
        if(this.nums > node.getNums()) {
            return 1;
        }else if(this.nums == node.getNums()) {
            if(this.getKey() > node.getKey()) {
                return 1;
            }
            return -1;
        }
        return -1;
    }
}
