package experiment5.ww.view;

import experiment5.ww.controller.UserType;

import java.util.Scanner;

/**
 * @author yohoyes
 * @date 2021/5/24 21:44
 */
public class UI {
    public static void UserChoices(){
        System.out.println();
        System.out.println("1. 数据库管理员");
        System.out.println("2. 卡业务员");
        System.out.println("3. 卡用户");
    }

    public static void systemManagerChoices(){
        System.out.println();
        System.out.println("1. 登录");
        System.out.println("2. 修改密码");
        System.out.println("3. 创建账户");
        System.out.println("4. 查询账户");
        System.out.println("5. 删除账户");
        System.out.println("6. 显示系统信息");
        System.out.println("7. 退出");
    }

    public static void cardUserChoices() {
        System.out.println();
        System.out.println("1. 登录");
        System.out.println("2. 修改密码");
        System.out.println("3. 查询卡余额");
        System.out.println("4. 充值");
        System.out.println("5. 消费");
        System.out.println("6. 显示系统信息");
        System.out.println("7. 退出");
    }

    public static void cardOperatorChoices() {
        System.out.println();
        System.out.println("1. 登录");
        System.out.println("2. 修改密码");
        System.out.println("3. 查询卡信息");
        System.out.println("4. 开卡");
        System.out.println("5. 挂失");
        System.out.println("6. 解挂");
        System.out.println("7. 显示系统信息");
        System.out.println("8. 退出");
    }

    public static void noPromt() {
        System.out.print("您的账号是: ");
    }

    public static void pwdPromt() {
        System.out.print("您的密码是: ");
    }

    public static void chPwdPromt() {
        System.out.print("您的新密码是: ");
    }

    public static void namePromt() {
        System.out.print("请输入您的账户名: ");
    }

    public static void topUpPromt(){
        System.out.print("请输入您要充值的金额: ");
    }

    public static void consumptionPromt() {
        System.out.print("请输入要消费的金额: ");
    }
}

