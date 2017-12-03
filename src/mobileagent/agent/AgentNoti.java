package mobileagent.agent;

import com.ibm.aglet.Aglet;
import com.ibm.aglet.event.MobilityAdapter;
import com.ibm.aglet.event.MobilityEvent;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

public class AgentNoti extends Aglet {

    private String message;

    public void onCreation(Object o) {
        message = (String) o;
        addMobilityListener(new MobilityAdapter() {
            @Override
            public void onArrival(MobilityEvent me) {
                GUI();
            }
        });
    }

    public void GUI() {
        JFrame frame = new JFrame();
        frame.setAlwaysOnTop(true);
        frame.setResizable(false);
        frame.setLayout(new BorderLayout());
        frame.setTitle("Tin nhắn từ máy chủ");
        JTextArea taNoti = new JTextArea(5, 25);
        taNoti.setBorder(new EmptyBorder(5, 15, 0, 0));
        taNoti.setEditable(false);
        frame.add(taNoti, BorderLayout.CENTER);
        taNoti.setText(message);
        frame.pack();
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        Dimension scrSize = Toolkit.getDefaultToolkit().getScreenSize();// size of the screen
        Insets toolHeight = Toolkit.getDefaultToolkit().getScreenInsets(frame.getGraphicsConfiguration());// height of the task bar
        frame.setLocation(scrSize.width - frame.getWidth(), scrSize.height - toolHeight.bottom - frame.getHeight());
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
        frame.setVisible(true);
    }
}
