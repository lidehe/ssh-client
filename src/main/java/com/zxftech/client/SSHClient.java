package com.zxftech.client;

import com.trilead.ssh2.ChannelCondition;
import com.trilead.ssh2.Session;
import com.zxftech.connection.IConnection;
import com.zxftech.database.IDatabase;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * 执行远程脚本
 *
 * @author lidehe
 * Nov 12 , 2019
 */
public class SSHClient {
    static StringBuffer out = new StringBuffer();
    static StringBuffer error = new StringBuffer();
    static int result;
    private IConnection iConnection;
    private IDatabase iDatabase;

    public void setConnection(IConnection connection) {
        this.iConnection = connection;
    }

    public void setDatabase(IDatabase database) {
        this.iDatabase = database;
    }

    /**
     *
     * @param command
     * @return 0表示成功，非0表示失败
     */
    public int execCmd(String command) {

        try {
            Session mySession = iConnection.getSession();
            mySession.execCommand(command);

//      打印执行日志
//            getStdout(mySession);
//            getStderr(mySession);
//      获取执行结果，0表示成功，非0表示失败
//      执行脚本时，脚本存在且路径正确的话，即使脚本内有错误，也不会返回异常，除非在脚本内捕获异常并 exit 非0
//      因此，脚本开发中必须配合异常捕获（即每个命令执行时都要$?判断是否命令执行成功。系统自带命令是有做异常捕获的）
            mySession.waitForCondition(ChannelCondition.STDOUT_DATA, 5000);
            mySession.waitForCondition(ChannelCondition.STDERR_DATA, 5000);
            mySession.waitForCondition(ChannelCondition.EXIT_SIGNAL, 5000);
            mySession.waitForCondition(ChannelCondition.EXIT_STATUS, 5000);
            result = mySession.getExitStatus();
//          关闭连接
            mySession.close();
            iConnection.close();
//            iDatabase.close();
        } catch (IOException e) {
            e.printStackTrace();
            return 1;
        }
        return result;
    }


    public static void getStdout(Session session) throws IOException {
        System.out.println("正常日志:");
        InputStream inputStout = session.getStdout();
        BufferedReader readStdout = new BufferedReader(new InputStreamReader(inputStout));
        String stdOut;
        while ((stdOut = readStdout.readLine()) != null) {
            System.out.println(stdOut);
            out.append(stdOut);
        }
        inputStout.close();
    }


    public static void getStderr(Session session) throws IOException {
        System.out.println("异常日志:");
        InputStream inputStderr = session.getStderr();
        BufferedReader readStderr = new BufferedReader(new InputStreamReader(inputStderr));
        String stdErr;
        while ((stdErr = readStderr.readLine()) != null) {
            System.out.println(stdErr);
            error.append(stdErr);
        }
        inputStderr.close();
    }
}
