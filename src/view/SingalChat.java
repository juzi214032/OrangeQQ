package view;
/**
 * 单聊小窗口
 */

import Bean.MyClient;
import msg.Send;
import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SingalChat {
    //聊天对象账号
    private String userName;

    //主窗口
    private JFrame frame;
    //主面板
    private JPanel panel;
    //接收消息的文本框
    private JTextArea textArea_getMsg;
    //接收消息scroll
    private JScrollPane scrollPane_getMsg;
    //发送消息文本框
    private JTextArea textArea_sendMsg;
    //发送消息按钮
    private JButton Button_send;
    //发送消息滚动窗口
    private JScrollPane scrollPane_sendMsg;

    public SingalChat(String userName) {
        this.userName = userName;
    }

    public JTextArea getTextArea_getMsg() {
        return textArea_getMsg;
    }

    public void singalchat() {
        initialize();//绘制窗口
        event();//启动监听
    }


    public void initialize() {
        //主窗口
        frame = new JFrame("与" + userName + "对话中");
        frame.setIconImage(Toolkit.getDefaultToolkit().getImage(Login_Regist.class.getResource("/img/企鹅.png")));
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setBounds(773, 157, 373, 607);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        //主面板
        panel = new JPanel();
        panel.setBounds(0, 0, 379, 585);
        frame.getContentPane().add(panel);
        panel.setLayout(null);

        //接收消息的文本域
        textArea_getMsg = new JTextArea();
        textArea_getMsg.setBounds(14, 13, 327, 376);
        textArea_getMsg.setEditable(false);
        textArea_getMsg.setBorder(null);
        textArea_getMsg.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        panel.add(textArea_getMsg);

        //接收消息滚动框
        scrollPane_getMsg = new JScrollPane(textArea_getMsg);
        scrollPane_getMsg.setBounds(14, 14, 327, 376);
        panel.add(scrollPane_getMsg);

        //发送消息文本域
        textArea_sendMsg = new JTextArea();
        textArea_sendMsg.setBounds(14, 408, 327, 93);
        textArea_sendMsg.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        textArea_sendMsg.setBorder(null);
        panel.add(textArea_sendMsg);

        //发送消息Scroll
        scrollPane_sendMsg = new JScrollPane(textArea_sendMsg);
        scrollPane_sendMsg.setBounds(14, 408, 327, 93);
        panel.add(scrollPane_sendMsg);

        //发送按钮
        Button_send = new JButton("发送");
        Button_send.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.green));
        Button_send.setBounds(228, 510, 113, 35);
        panel.add(Button_send);


    }

    public void event() {
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent arg0) {
                //关闭窗口时从集合中删除该单聊窗口
                Login_Regist.singalchat.remove(userName);
            }
        });

        Button_send.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                //文本域不为空时发送消息
                if (!"".equals(textArea_sendMsg.getText())) {
                    new Send(MyClient.myId + "~!~!@@danliao" + userName + "~!~!@@danliao" + textArea_sendMsg.getText()).send();
                    textArea_sendMsg.setText("");
                }
            }
        });

        //回车键发送消息
        textArea_sendMsg.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                //文本域不为空时发送消息
                if (!"".equals(textArea_sendMsg.getText()) && e.getKeyCode() == 10) {
                    new Send(MyClient.myId + "~!~!@@danliao" + userName + "~!~!@@danliao" + textArea_sendMsg.getText()).send();
                    textArea_sendMsg.setText("");
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == 10)
                    textArea_sendMsg.setText("");
            }
        });
    }
}
