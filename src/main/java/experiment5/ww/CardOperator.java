package experiment5.ww;

/**
 * 卡操作者接口
 * @author yohoyes
 * @date 2021/5/17 0:54
 */
public interface CardOperator extends BaseOpera {

    /**
     * 建卡
     * @param userName 用户名
     * @param pwd 用户密码
     */
    void register(String userName, String pwd);

    /**
     * 挂失冻结账户
     * @param no 卡号
     */
    void freezing(String no);

    /**
     * 解挂
     * @param no 卡号
     * @param pwd 密码
     */
    void unfreezing(String no, String pwd);
}
