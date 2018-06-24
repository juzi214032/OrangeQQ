package view;
/**
 * 自定义CellRenderer
 * 实现Jlist每一项可同时显示头像和文字的功能
 */

import javax.swing.*;
import java.awt.*;

public class MyCellRenderer extends JLabel implements ListCellRenderer {
    private Icon[] icons;

    public MyCellRenderer(Icon[] icons) {
        this.icons = icons;
    }

    @Override
    public Component getListCellRendererComponent(JList list, Object value,
                                                  int index, boolean isSelected, boolean cellHasFocus) {
        String s = value.toString();
        setText("   "+s);//设置选项
        setBorder(BorderFactory.createEmptyBorder(1, 5, 1, 5));//加入宽度为5的空白边框

        // TODO: 2018/6/23 可以修改选项选中前后的颜色
        if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        } else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }

        try {
            setIcon(icons[index]);//设置图片
        }catch (Exception e){}
        setEnabled(list.isEnabled());
        setFont(list.getFont());
        setOpaque(true);
        return this;
    }

}
