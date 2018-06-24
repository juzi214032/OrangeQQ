package msg;
/**
 * 接收消息
 * 该线程一直在后台运行
 */

import Bean.MyClient;
import view.GroupChat;
import view.Login_Regist;
import view.SingalChat;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;

public class ReceiveThread extends Thread {
    private Socket s;
    private int len;

    public ReceiveThread(Socket s) {
        this.s = s;
    }

    @Override
    public void run() {
        try {
            while (true) {

                SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");//设置日期格式
                String time = df.format(new Date());

                //处理字符串
                byte[] msg = new byte[1024];
                InputStream is = s.getInputStream();
                len = is.read(msg);
                String str = new String(msg, 0, len);
                //判断消息是群聊还是单聊
                if (str.contains("~!~!@@danliao")) {
                    String[] str1 = str.split("~!~!@@danliao");
                    String userId = str1[0];
                    String myUserId = str1[1];
                    String msg1 = str1[2];
                    //如果单聊窗口没打开，则新开一个单聊窗口
                    if (!Login_Regist.singalchat.containsKey(userId) && !userId.equals(MyClient.myId)) {
                        SingalChat sc = new SingalChat(userId);
                        sc.singalchat();
                        Login_Regist.singalchat.put(userId, sc);
                    }
                    //获取系统当前日期

                    try {
                        Login_Regist.singalchat.get(userId).getTextArea_getMsg().append(time + "——" + userId + "\n" + msg1 + "\n");
                        Login_Regist.singalchat.get(userId).getTextArea_getMsg().setCaretPosition(Login_Regist.singalchat.get(userId).getTextArea_getMsg().getText().length());
                    } catch (Exception e) {
                        Login_Regist.singalchat.get(myUserId).getTextArea_getMsg().append(time + "——" + userId + "\n" + msg1 + "\n");
                        Login_Regist.singalchat.get(myUserId).getTextArea_getMsg().setCaretPosition(Login_Regist.singalchat.get(myUserId).getTextArea_getMsg().getText().length());
                    }

                } else {
                    StringTokenizer st1 = new StringTokenizer(str, "~!~!@@qunliao●●");
                    String[] str1 =  str.split("~!~!@@qunliao●●");
                    //往聊天窗口中添加字符串
                    GroupChat.textArea_getMsg.append(time + "——" + str1[0] + "\n" + str1[1] + "\n");
                    GroupChat.textArea_getMsg.setCaretPosition(GroupChat.textArea_getMsg.getText().length());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
