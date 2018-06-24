package view;
/**
 * 程序起始窗口
 * 含登录与注册界面
 */

import controller.Login_c;
import controller.Register_c;
import msg.Send;
import msg.SocketQQ;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;
import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Login_Regist {
    //储存单聊窗口的集合
    public static Map<String, SingalChat> singalchat = new HashMap<>();
    //标志变量
    static boolean flag = true;
    //该客户端登录的账号
    public static String myUserName;

    /**
     * 测试本地历史功能
     */

    //主窗体
    private JFrame frame;
    //登陆面板
    private JPanel panel_login;
    //注册面板
    private JPanel panel_regist;
    //登陆账号
    private JTextField textField_login_user;
    //登陆密码
    private JPasswordField passwordField_login_pass;
    //注册账号
    private JTextField textField_regist_pass;
    //注册密码
    private JPasswordField passwordField_regist_pass;
    //注册密码重复
    private JPasswordField passwordField_regist_repeatPass;
    //登陆
    private JButton button_login;
    //注册
    private JButton button_regist;
    //立即注册
    private JButton button_immediate_regist;
    //OrangeQQ文字
    private JLabel label_text_orangeqq;
    //欢迎文字
    private JLabel label_text_welcome;
    //桔子图片
    private JLabel label_img_juzi;
    //企鹅图片
    private JLabel label_img_qie;


    public static void main(String[] args) {

        //加载第三方皮肤
        try {
            UIManager.put("RootPane.setupButtonVisible", false);
            BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.translucencySmallShadow;
            org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
        } catch (Exception e) {
        }

        //启动窗口
        Login_Regist window = new Login_Regist();
        window.frame.setVisible(true);
    }

    public Login_Regist() {
        initialize();
        event();
    }

    private void initialize() {

        //主窗口
        frame = new JFrame("OrangeQQ——你的灵魂伴侣");
        frame.setIconImage(Toolkit.getDefaultToolkit().getImage(Login_Regist.class.getResource("/img/企鹅.png")));
        frame.getContentPane().setBackground(UIManager.getColor("CheckBox.background"));
        frame.getContentPane().setLayout(null);
        frame.setResizable(false);
        frame.setBounds(679, 248, 562, 335);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //第一层panel
        panel_login = new JPanel();
        panel_login.setFocusable(true);
        panel_login.setBounds(0, 0, 553, 610);
        frame.getContentPane().add(panel_login);
        panel_login.setLayout(null);

        //第二层panel，注册框的显示，叠加在第一层panel上
        panel_regist = new JPanel();
        panel_regist.setBorder(new TitledBorder(null, "注册账号", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel_regist.setBounds(25, 304, 499, 282);
        panel_login.add(panel_regist);
        panel_regist.setLayout(null);

        //注册_账号_文本框
        textField_regist_pass = new JTextField();
        textField_regist_pass.setText("账号（2-6位中英文或数字）");
        textField_regist_pass.setForeground(Color.LIGHT_GRAY);
        textField_regist_pass.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        textField_regist_pass.setBounds(30, 45, 220, 34);
        panel_regist.add(textField_regist_pass);
        textField_regist_pass.setColumns(10);

        //立即注册按钮
        button_immediate_regist = new JButton("立即注册");
        button_immediate_regist.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.blue));
        button_immediate_regist.setBackground(SystemColor.activeCaption);
        button_immediate_regist.setForeground(Color.WHITE);
        button_immediate_regist.setFont(new Font("微软雅黑", Font.BOLD, 18));
        button_immediate_regist.setBounds(30, 202, 216, 35);
        panel_regist.add(button_immediate_regist);

        //欢迎文字
        label_text_welcome = new JLabel("欢迎使用Orang QQ ！");
        label_text_welcome.setFont(new Font("微软雅黑", Font.BOLD, 18));
        label_text_welcome.setBounds(281, 47, 191, 35);
        panel_regist.add(label_text_welcome);

        //注册密码框
        passwordField_regist_pass = new JPasswordField();
        passwordField_regist_pass.setForeground(Color.LIGHT_GRAY);
        passwordField_regist_pass.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        char c = 0;
        passwordField_regist_pass.setEchoChar(c);
        passwordField_regist_pass.setText(" 输入密码（不少于10位）");
        passwordField_regist_pass.setBounds(30, 96, 220, 34);
        panel_regist.add(passwordField_regist_pass);

        //注册确认密码框
        passwordField_regist_repeatPass = new JPasswordField();
        passwordField_regist_repeatPass.setForeground(Color.LIGHT_GRAY);
        passwordField_regist_repeatPass.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        char c2 = 0;
        passwordField_regist_repeatPass.setEchoChar(c2);
        passwordField_regist_repeatPass.setText(" 确认密码");
        passwordField_regist_repeatPass.setBounds(30, 146, 220, 34);
        panel_regist.add(passwordField_regist_repeatPass);

        //企鹅图片
        label_img_qie = new JLabel("");
        ClassLoader cl2 = this.getClass().getClassLoader();
        URL iconURL2 = cl2.getResource("img/企鹅.png");
        label_img_qie.setIcon(new ImageIcon(iconURL2));
        label_img_qie.setBounds(321, 92, 112, 130);
        panel_regist.add(label_img_qie);

        //桔子图片
        label_img_juzi = new JLabel("");
        ClassLoader cl1 = this.getClass().getClassLoader();
        URL iconURL1 = cl1.getResource("img/桔子.png");
        label_img_juzi.setIcon(new ImageIcon(iconURL1));
        label_img_juzi.setBounds(87, 0, 112, 130);
        panel_login.add(label_img_juzi);

        //Orange QQ文字
        label_text_orangeqq = new JLabel("Orange QQ");
        label_text_orangeqq.setHorizontalAlignment(SwingConstants.CENTER);
        label_text_orangeqq.setFont(new Font("微软雅黑", Font.PLAIN, 35));
        label_text_orangeqq.setBounds(206, 37, 229, 72);
        panel_login.add(label_text_orangeqq);

        //登录_账号_文本框
        textField_login_user = new JTextField();
        textField_login_user.setText(" 账号/邮箱/手机号");
        textField_login_user.setForeground(Color.LIGHT_GRAY);
        textField_login_user.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        textField_login_user.setBounds(162, 141, 220, 34);
        textField_login_user.setColumns(10);
        panel_login.add(textField_login_user);

        //登录按钮
        button_login = new JButton("登录");
        button_login.setForeground(Color.WHITE);
        button_login.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.green));
        button_login.setFont(new Font("微软雅黑", Font.BOLD, 18));
        button_login.setBounds(282, 238, 100, 34);
        panel_login.add(button_login);

        //注册按钮
        button_regist = new JButton("注册");
        button_regist.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.lightBlue));
        button_regist.setForeground(Color.WHITE);
        button_regist.setFont(new Font("微软雅黑", Font.BOLD, 18));
        button_regist.setBounds(162, 238, 100, 34);
        panel_login.add(button_regist);

        //登录_密码_文本框
        passwordField_login_pass = new JPasswordField();
        passwordField_login_pass.setForeground(Color.LIGHT_GRAY);
        passwordField_login_pass.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        char c1 = 0;
        passwordField_login_pass.setEchoChar(c1);
        passwordField_login_pass.setText(" 密码");
        passwordField_login_pass.setBounds(162, 184, 220, 34);
        panel_login.add(passwordField_login_pass);
    }

    private void event() {
        //鼠标单击时获取焦点
        panel_login.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                panel_login.grabFocus();
            }
        });

        textField_regist_pass.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                textField_regist_pass.setForeground(Color.black);
                textField_regist_pass.setFont(new Font("微软雅黑", Font.PLAIN, 16));
            }
        });

        //焦点监听
        textField_regist_pass.addFocusListener(new FocusAdapter() {
            @Override
            //失去焦点时，恢复提示信息
            public void focusLost(FocusEvent e) {
                if (textField_regist_pass.getText().isEmpty()) {
                    textField_regist_pass.setForeground(Color.LIGHT_GRAY);
                    textField_regist_pass.setText("账号（2-6位中英文或数字）");
                } else {
                    //if (!textField_regist_pass.getText().matches("^[a-zA-Z0-9]{8,15}$")) {
                    if (!textField_regist_pass.getText().matches("[\\u4e00-\\u9fa5_a-zA-Z0-9_]{2,6}")) {
                        textField_regist_pass.setForeground(Color.red);
                        textField_login_user.setFont(new Font("微软雅黑", Font.PLAIN, 16));
                        textField_regist_pass.setText(" 格式错误！");
                    }
                }
            }

            @Override
            //获得焦点时，清空提示信息
            public void focusGained(FocusEvent e) {
                textField_regist_pass.setForeground(Color.BLACK);
                if ("账号（2-6位中英文或数字）".equals(textField_regist_pass.getText()) || " 格式错误！".equals(textField_regist_pass.getText())) {
                    textField_regist_pass.setText("");
                }
            }
        });

        //动作监听
        button_immediate_regist.addActionListener(new ActionListener() {
            //鼠标点击
            public void actionPerformed(ActionEvent e) {
                //调用注册方法获取返回值，根据不同返回值弹出不同提示
                int i = Register_c.regist(textField_regist_pass.getText(), String.valueOf(passwordField_regist_pass.getPassword()), String.valueOf(passwordField_regist_repeatPass.getPassword()));
                if (i == 0) {
                    JOptionPane.showMessageDialog(frame, "注册成功，请登录！", "提示", JOptionPane.INFORMATION_MESSAGE);
                    frame.setSize(562, 335);
                } else if (i == 1) {
                    JOptionPane.showMessageDialog(frame, "账号已存在！", "警告", JOptionPane.ERROR_MESSAGE);
                } else if (i == 2) {
                    JOptionPane.showMessageDialog(frame, "两次密码不同，请重新输入！", "警告", JOptionPane.ERROR_MESSAGE);
                } else if (i == 3) {
                    JOptionPane.showMessageDialog(frame, "账号为空！", "警告", JOptionPane.ERROR_MESSAGE);
                } else if (i == 4) {
                    JOptionPane.showMessageDialog(frame, "密码为空！", "警告", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        //焦点监听
        passwordField_regist_pass.addFocusListener(new FocusAdapter() {
            @Override
            //获取焦点时，将密码框清空，回显字符设为●
            public void focusGained(FocusEvent e) {
                passwordField_regist_pass.setForeground(Color.black);
                if (" 输入密码（不少于10位）".equals(new String(passwordField_regist_pass.getPassword())) ||
                        " 格式错误！".equals(new String(passwordField_regist_pass.getPassword()))) {
                    passwordField_regist_pass.setText("");
                    passwordField_regist_pass.setEchoChar('●');
                }
            }

            @Override
            //失去焦点时，内容设为提示内容
            public void focusLost(FocusEvent e) {
                if (new String(passwordField_regist_pass.getPassword()).isEmpty()) {
                    passwordField_regist_pass.setForeground(Color.LIGHT_GRAY);
                    char c = 0;
                    passwordField_regist_pass.setEchoChar(c);//因为是密码框，设置回显字符为0，会显示明文
                    passwordField_regist_pass.setText(" 输入密码（不少于10位）");
                } else if (!new String(passwordField_regist_pass.getPassword()).matches("^[a-zA-Z0-9]{0,20}$")) {
                    char c = 0;
                    passwordField_regist_pass.setEchoChar(c);
                    passwordField_regist_pass.setForeground(Color.red);
                    passwordField_regist_pass.setText(" 格式错误！");
                }
            }
        });

        //焦点监听
        passwordField_regist_repeatPass.addFocusListener(new FocusAdapter() {
            @Override
            //获取焦点时，将密码框清空，回显字符设为●
            public void focusGained(FocusEvent e) {
                passwordField_regist_repeatPass.setForeground(Color.black);
                if (" 确认密码".equals(new String(passwordField_regist_repeatPass.getPassword())))
                    passwordField_regist_repeatPass.setText("");
                passwordField_regist_repeatPass.setEchoChar('●');
            }

            @Override
            //失去焦点时，内容设为提示内容
            public void focusLost(FocusEvent e) {
                if ("".equals(new String(passwordField_regist_repeatPass.getPassword()))) {
                    passwordField_regist_repeatPass.setForeground(Color.LIGHT_GRAY);
                    char c = 0;
                    passwordField_regist_repeatPass.setEchoChar(c);//因为是密码框，设置回显字符为0，会显示明文
                    passwordField_regist_repeatPass.setText(" 确认密码");
                }
            }
        });

        //键盘监听
        textField_login_user.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                textField_login_user.setForeground(Color.black);
                textField_login_user.setFont(new Font("微软雅黑", Font.PLAIN, 16));
            }
        });
        textField_login_user.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if (textField_login_user.getText().isEmpty()) {
                    textField_login_user.setForeground(Color.LIGHT_GRAY);
                    textField_login_user.setText(" 账号/邮箱/手机号");
                }
            }

            @Override
            public void focusGained(FocusEvent e) {
                if (" 账号/邮箱/手机号".equals(textField_login_user.getText())) {
                    textField_login_user.setText("");
                }
            }
        });

        //动作监听
        button_login.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (Login_c.login(textField_login_user.getText(), new String(passwordField_login_pass.getPassword()))) {
                    myUserName = textField_login_user.getText();
                    JOptionPane.showMessageDialog(frame, "登录成功！", "提示", JOptionPane.INFORMATION_MESSAGE);
                    //启动Socket
                    SocketQQ.getobject();

                    //发送登陆信息
                    new Send("").sendLogin();
                    frame.setVisible(false);
                    new GroupChat().chatroom();
                } else {
                    JOptionPane.showMessageDialog(frame, "登录失败，请检查账号、密码！", "警告", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        //动作监听
        button_regist.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (flag) {
                    frame.setSize(562, 644);
                } else {
                    frame.setSize(562, 335);
                }
                flag = !flag;
            }
        });

        //焦点监听
        passwordField_login_pass.addFocusListener(new FocusAdapter() {
            @Override
            //获取焦点
            public void focusGained(FocusEvent e) {
                passwordField_login_pass.setForeground(Color.black);
                if (" 密码".equals(new String(passwordField_login_pass.getPassword())))
                    passwordField_login_pass.setText("");
                passwordField_login_pass.setEchoChar('●');
            }

            @Override
            //失去焦点
            public void focusLost(FocusEvent e) {
                if ("".equals(new String(passwordField_login_pass.getPassword()))) {
                    passwordField_login_pass.setForeground(Color.LIGHT_GRAY);
                    char c = 0;
                    passwordField_login_pass.setEchoChar(c);
                    passwordField_login_pass.setText(" 密码");
                }
            }
        });
    }
}
