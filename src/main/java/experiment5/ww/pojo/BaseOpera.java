package experiment5.ww.pojo;

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
    Card query(String no);

    /**
     * 登录接口
     * @param no 学号
     * @param pwd 用户密码
     */
    boolean login(String no, String pwd);

    /**
     * 修改密码接口
     * @param no 学号
     * @param prePwd 原用户密码
     * @param afterPwd 现用户密码
     */
    boolean chPwd(String no, String prePwd, String afterPwd);
}
