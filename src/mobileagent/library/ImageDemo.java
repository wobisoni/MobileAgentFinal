package mobileagent.library;

import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ImageDemo extends JFrame {

    public ImageDemo(BufferedImage bi) {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(700, 450);//chieu rong va chieu cao cua frame
        setVisible(true);
        this.setLocationRelativeTo(null);
        setResizable(false);//cho mo rong frame bang chuot.true= cho phep.false khoa
        JLabel label = new JLabel();
        add(label);
        label.setSize(700, 450);
        System.out.println("x : " + label.getSize().width + "y : " + label.getSize().height);
        setPicture(label, bi);
    }
    
    public  void setPicture(JLabel label ,BufferedImage image){
        int x =label.getSize().width;
        int y =label.getSize().height;
        int ix =image.getWidth();
        int iy =image.getHeight();
        int dx=0;
        int dy=0;
        if(x /y > ix /iy){
            dy=y;
            dx=dy*ix /iy;
        }else{
            dx=x;
            dy=dx*iy/ix;
        }
        ImageIcon icon = new ImageIcon(image.getScaledInstance(dx, dy, BufferedImage.SCALE_SMOOTH));
        label.setIcon(icon);

    }
}