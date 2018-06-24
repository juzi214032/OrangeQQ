package view;
/**
 * 主聊天窗口
 * 主要负责显示群聊界面以及显示在线好友列表
 */

import Bean.MyClient;
import Dao.Offline;
import msg.SocketQQ;
import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.*;

public class GroupChat {
    public static JTextArea textArea_getMsg;
    public static DefaultListModel listModel;
    public static JList<String> list;

    //主窗体
    private JFrame frame;
    //底层面板
    private JPanel panel_under;
    //好友列表面板
    private JPanel panel_friendsList;
    //好友列表滚动窗口
    private JScrollPane scrollPane_friendsList;
    //发送消息滚动窗口
    private JScrollPane scrollPane_sendMsg;
    //发送消息文本框
    private JTextArea textArea_sendMsg;
    //接收消息Scroll
    private JScrollPane scrollPane_receiveMsg;
    //发送按钮
    private JButton button_send;


    public void chatroom() {
        window();//绘制窗口
        event();//设置事件监听
    }

    private void window() {
        //主窗体
        frame = new JFrame("欢迎使用OrangeQQ，当前登录用户为: " + MyClient.myId);
        frame.setIconImage(Toolkit.getDefaultToolkit().getImage(Login_Regist.class.getResource("/img/企鹅.png")));
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setBounds(443, 144, 1034, 648);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        //底层面板
        panel_under = new JPanel();
        panel_under.setBackground(Color.WHITE);
        panel_under.setBounds(0, 0, 1030, 611);
        frame.getContentPane().add(panel_under);
        panel_under.setLayout(null);

        //好友列表pane
        panel_friendsList = new JPanel();
        panel_friendsList.setBackground(new Color(245, 245, 245));
        panel_friendsList.setBounds(0, 0, 259, 621);
        panel_under.add(panel_friendsList);
        panel_friendsList.setLayout(null);

        //好友列表Scroll
        scrollPane_friendsList = new JScrollPane(panel_friendsList);
        scrollPane_friendsList.setBounds(0, 0, 259, 621);
        panel_under.add(scrollPane_friendsList);
        scrollPane_friendsList.setBorder(null);

        //好友列表List
        listModel = new DefaultListModel();
        list = new JList(listModel);
        list.setBackground(new Color(250, 250, 250));
        list.setVisibleRowCount(15);
        list.setPreferredSize(new Dimension(200, 300));
        list.setFixedCellHeight(-1);
        list.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);//设置为单选模式
        list.setFont(new Font("微软雅黑", Font.BOLD, 18));
        list.setBounds(0, 0, 259, 621);
        panel_friendsList.add(list);
        DefaultListCellRenderer renderer = new DefaultListCellRenderer();//列表设置为居中
        renderer.setHorizontalAlignment(SwingConstants.CENTER);
        list.setCellRenderer(renderer);

        new Thread(new FriendsListThread()).start();//启动好友面板实时刷新线程

        //接收消息文本框
        textArea_getMsg = new JTextArea();
        textArea_getMsg.setFont(new Font("微软雅黑", Font.PLAIN, 17));
        textArea_getMsg.setEditable(false);//不可编辑
        textArea_getMsg.setBounds(269, 10, 736, 419);
        textArea_getMsg.setBorder(null);
        panel_under.add(textArea_getMsg);

        //接收消息Scroll
        scrollPane_receiveMsg = new JScrollPane(textArea_getMsg);
        scrollPane_receiveMsg.setBounds(269, 10, 736, 419);
        panel_under.add(scrollPane_receiveMsg);

        //发送消息文本框
        textArea_sendMsg = new JTextArea();
        textArea_sendMsg.setLineWrap(true);
        textArea_sendMsg.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        textArea_sendMsg.setBounds(269, 452, 736, 117);
        textArea_sendMsg.setBorder(null);
        panel_under.add(textArea_sendMsg);

        //发送消息Scroll
        scrollPane_sendMsg = new JScrollPane(textArea_sendMsg);
        scrollPane_sendMsg.setBounds(269, 452, 736, 93);
        panel_under.add(scrollPane_sendMsg);
        //scrollPane_sendMsg.setBorder(null);

        //发送消息button
        button_send = new JButton("发送");
        button_send.setForeground(Color.WHITE);
        button_send.setFont(new Font("微软雅黑", Font.BOLD, 16));
        button_send.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.green));
        button_send.setBounds(888, 555, 109, 34);
        panel_under.add(button_send);
    }

    private void event() {
        //主窗体关闭时调用离线方法
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent arg0) {
                new Offline().offline();
            }
        });

        // 添加选项选中状态被改变的监听器
        list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    if ("群聊".equals(list.getSelectedValue())) {
                    } else if (list.getSelectedValue() == null) {
                    } else if (!Login_Regist.singalchat.containsKey(list.getSelectedValue()) && !list.getSelectedValue().equals(MyClient.myId)) {
                        SingalChat sc = new SingalChat(list.getSelectedValue());
                        sc.singalchat();
                        Login_Regist.singalchat.put(list.getSelectedValue(), sc);

                    }
                }
            }
        });

        //【发送】按钮发送群聊消息
        button_send.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if (!"".equals(textArea_sendMsg.getText())) {
                    SocketQQ.getobject().send(MyClient.myId+"~!~!@@qunliao●●" + textArea_sendMsg.getText());
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
                    SocketQQ.getobject().send(MyClient.myId+"~!~!@@qunliao●●" + textArea_sendMsg.getText());
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


