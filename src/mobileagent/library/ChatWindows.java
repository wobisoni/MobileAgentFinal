package mobileagent.library;

import com.ibm.aglet.AgletProxy;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DefaultCaret;
import mobileagent.agent.AgentChatServer;
import mobileagent.agent.AgentChatClient;

public class ChatWindows extends JFrame implements ActionListener {
    JTextArea text = new JTextArea();
    JTextField input = new JTextField();
    JScrollPane scroll;
    AgentChatServer master = null;
    AgentChatClient slave = null;
    AgletProxy apClient;

    public ChatWindows(AgentChatServer master) {
        super("Chat");
        this.master = master;
        GUI();
        setLocationRelativeTo(null);
    }

    public ChatWindows(AgentChatClient slave) {
        super("ADMIN");
        this.slave = slave;
        GUI();
        Dimension scrSize = Toolkit.getDefaultToolkit().getScreenSize();// size of the screen
        Insets toolHeight = Toolkit.getDefaultToolkit().getScreenInsets(getGraphicsConfiguration());// height of the task bar
        setLocation(scrSize.width - getWidth(), scrSize.height - toolHeight.bottom - getHeight());
    }

    public void GUI(){
        this.setLayout(new BorderLayout());
        this.setSize(400, 250);
        scroll = new JScrollPane(text,
            JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        text.setBorder(new EmptyBorder(5, 10, 0, 0));
        Font font = new Font("Arial", Font.BOLD,12);
        text.setFont(font);
        text.setEditable(false);
        DefaultCaret caret = (DefaultCaret) text.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        add("Center", scroll);
        add("South", input);
        input.addActionListener(this);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                setVisible(false);
            } 
        });
    }
    
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == input) {
            String t = input.getText();
            appendText("Me :\t"+t);
            if (master != null) {
                if(apClient==null){
                    apClient = master.dispatchSlave();
                }
                master.sendText(t);
            } else if (slave != null) {
                slave.sendText(t);
            } 
            input.setText("");
        } 
    }

    public void appendText(String str) {
        int n = (int) Math.ceil((double)str.length()/50);
        String st = "";
        for(int i=0;i<n;i++){
            if(str.length()<=(i+1)*50){
                st+= str.substring(i*50, str.length())+"\r\n";
            }else{
                st+= str.substring(i*50, (i+1)*50)+"\r\n\t";
            }
        }
        text.append(st + "\r\n");
    }
}