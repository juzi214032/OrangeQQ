package view;
/**
 * 好友列表刷新线程
 * 实时刷新在线好友（每5秒一次）
 */

import Dao.FriendList;

import javax.swing.*;

public class FriendsListThread extends Thread {
    @Override
    public void run() {
        //无限循环刷新好友列表
        while (true) {
            GroupChat.list.setCellRenderer(new MyCellRenderer(geticon()));//使用自己覆写的MyCellRenderer
            String[] str = new FriendList().list();//从数据库中获取在线好友
            GroupChat.listModel.removeAllElements();
            /**
             * 循环设置好友到好友列表面板
             * 当Jlist当前位置存在选项时，使用set方法；当前位置不存在选项时，使用get方法
             */
            for (int i = 0; i < str.length; i++) {
                try {
                    GroupChat.listModel.set(i, str[i]);//
                } catch (Exception e) {
                    GroupChat.listModel.add(i, str[i]);
                }

            }

            try {
                sleep(5000);//线程暂停5秒，避免CPU占用率过高
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //将默认头像加入Icon数组
    private Icon[] geticon() {
        ClassLoader cl1 = this.getClass().getClassLoader();
        //URL iconURL1 = cl1.getResource("img/桔子.png");
        //label_img_juzi.setIcon(new ImageIcon(iconURL1));
        Icon icon1 = new ImageIcon(cl1.getResource("icon/1.png"));
        Icon icon2 = new ImageIcon(cl1.getResource("icon/2.png"));
        Icon icon3 = new ImageIcon(cl1.getResource("icon/3.png"));
        Icon icon4 = new ImageIcon(cl1.getResource("icon/4.png"));
        Icon icon5 = new ImageIcon(cl1.getResource("icon/5.png"));
        Icon icon6 = new ImageIcon(cl1.getResource("icon/6.png"));
        Icon icon7 = new ImageIcon(cl1.getResource("icon/7.png"));
        Icon icon8 = new ImageIcon(cl1.getResource("icon/8.png"));
        Icon[] icons = {icon1, icon2, icon3, icon4, icon5, icon6, icon7, icon8};
        return icons;
    }
}
