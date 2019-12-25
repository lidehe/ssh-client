package com.zxftech;

import com.zxftech.client.SSHClient;
import com.zxftech.connection.impl.ConnectionByPassword;
import com.zxftech.utils.PropertyUtils;

import java.io.IOException;

/**
 * 入口
 * @author lidehe
 * Nov 13 , 2019
 */
public class Test {
    static String ssh_host = PropertyUtils.getProperty("ssh_host");
    static int ssh_port = Integer.parseInt(PropertyUtils.getProperty("ssh_port"));
    static String ssh_user = PropertyUtils.getProperty("ssh_user");
    static String ssh_password = PropertyUtils.getProperty("ssh_password");

    public static void main(String[] args) {
        SSHClient sshClient=new SSHClient();


        // 初始化ssh连接，执行命令
        ConnectionByPassword connectionByPassword=new ConnectionByPassword();
        try {
            if (!connectionByPassword.connect(ssh_host, ssh_port, ssh_user, ssh_password)) {
                System.out.println("连接失败");
                System.exit(666);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        sshClient.setConnection(connectionByPassword);


//        sshClient.execCmd("sh -l kitchen.sh /home/kettle/greeting.kjb");
//        sshClient.execCmd("source .bash_profile && kitchen.sh -file /home/kettle/greeting.kjb");
//        sshClient.execCmd("source .bash_profile && kitchen.sh -file /home/kettle/greeting.kjb");
        System.out.println(sshClient.execCmd("lls -al"));
    }
}
