package experiment5.ww;

/**
 * 公共接口
 * @author yohoyes
 * @date 2021/5/17 8:59
 */
public interface BaseOpera {

    /**
     * 查询卡信息
     * @param no 卡号
     */
    void query(String no);

    /**
     * 登录接口
     * @param name 用户名
     * @param pwd 用户密码
     */
    void login(String name, String pwd);

    /**
     * 修改密码接口
     * @param name 用于名
     * @param prePwd 原用户密码
     * @param afterPwd 现用户密码
     */
    void chPwd(String name, String prePwd, String afterPwd);
}
