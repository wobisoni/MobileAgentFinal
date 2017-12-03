package mobileagent.library;

import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ImagePanel extends JPanel{

    public ImagePanel(BufferedImage bi) {
        setSize(137, 77);//chieu rong va chieu cao cua frame
        setVisible(true);
        JLabel label = new JLabel();
        add(label);
        label.setSize(700, 450);
        System.out.println("x : " + label.getSize().width + "y : " + label.getSize().height);
        setPicture(label, bi);
    }

    public void setPicture(JLabel label, BufferedImage image) {
        int x = label.getSize().width;
        int y = label.getSize().height;
        int ix = image.getWidth();
        int iy = image.getHeight();
        int dx = 0;
        int dy = 0;
        if (x / y > ix / iy) {
            dy = y;
            dx = dy * ix / iy;
        } else {
            dx = x;
            dy = dx * iy / ix;
        }
        ImageIcon icon = new ImageIcon(image.getScaledInstance(dx, dy, BufferedImage.SCALE_SMOOTH));
        label.setIcon(icon);
    }
}
