package msg;
/**
 * 通过TCP向服务器发送信息
 */

import view.Login_Regist;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class Send {
    //发送的消息
    private String msg;
    //获取socket连接
    private Socket socket = SocketQQ.getobject().getSocket();

    public Send(String msg) {
        this.msg = msg;
    }

    public void send() {
        try {
            OutputStream os = socket.getOutputStream();
            os.write(msg.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //登录时向服务器发送的消息
    public void sendLogin() {
        try {
            OutputStream os = socket.getOutputStream();
            os.write(("~!~@@login" + Login_Regist.myUserName).getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
