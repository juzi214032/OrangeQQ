package msg;
/**
 * Socket类
 * 负责连接服务器
 * 因为一个客户端只需一个TCP连接，所以采用单例模式设计
 */

import java.io.IOException;
import java.net.Socket;

public class SocketQQ {
    private static final SocketQQ sQQ = new SocketQQ();
    //socket连接
    private Socket socket;

    private SocketQQ() {
        try {
            //socket = new Socket("132.232.15.216", 6666);
            socket = new Socket("localhost", 6666);
        } catch (IOException e) {
            e.printStackTrace();
        }

        new ReceiveThread(socket).start();//启动接收消息线程
    }

    public Socket getSocket() {
        return socket;
    }

    public static SocketQQ getobject() {
        return sQQ;
    }

    public void send(String msg) {
        new Send( msg).send();
    }
}
