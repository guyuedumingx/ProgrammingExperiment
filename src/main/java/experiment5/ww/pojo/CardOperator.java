package experiment5.ww.pojo;

import experiment5.ww.pojo.BaseOpera;

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
    String register(String userName, String pwd);

    /**
     * 挂失冻结账户
     * @param no 卡号
     */
    boolean freezing(String no);

    /**
     * 解挂
     * @param no 卡号
     * @param pwd 密码
     */
    boolean unfreezing(String no, String pwd);
}
