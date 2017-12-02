package mobileagent.library;

import com.ibm.aglet.AgletProxy;
import com.ibm.aglet.Message;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyVetoException;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.Socket;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import java.io.IOException;
import javax.imageio.ImageIO;

public class RemoteWindows {

    private JFrame frame;
    private JDesktopPane desktop;
    private JInternalFrame interFrame;
    private JPanel panel;
    private AgletProxy remoteProxy = null;
    private Socket socket;
    private InputStream is;
    private Image image;
    private String width;
    private String height;
    private boolean loop;

    public RemoteWindows(AgletProxy ap, Socket socket, String width, String height) {
        this.width = width;
        this.height = height;
        this.socket = socket;
        this.remoteProxy = ap;
        this.loop = true;
    }

    public void create() {
        drawGUI();
        try {
            is = socket.getInputStream();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        Thread receiveScreen = new Thread() {
            @Override
            public void run() {
                ReceiveScreen();
            }
        };
        receiveScreen.start();
        new RemoteSendEvents(socket, panel, width, height);
    }

    public void ReceiveScreen() {
        try {
            while (loop) {
                byte[] bytes = new byte[1024 * 1024];
                int count = 0;
                do {
                    count += is.read(bytes, count, bytes.length - count);
                } while (!(count > 4 && bytes[count - 2] == (byte) -1 && bytes[count - 1] == (byte) -39));
                image = ImageIO.read(new ByteArrayInputStream(bytes));
                image = image.getScaledInstance(panel.getWidth(), panel.getHeight(), Image.SCALE_FAST);

                Graphics graphics = panel.getGraphics();
                graphics.drawImage(image, 0, 0, panel.getWidth(), panel.getHeight(), panel);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void drawGUI() {
        frame = new JFrame();
        desktop = new JDesktopPane();
        interFrame = new JInternalFrame("Remote Desktop", true, true, true);
        panel = new JPanel();

        frame.add(desktop, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        frame.setMinimumSize(new Dimension(700, 300));
        frame.setVisible(true);
        interFrame.setLayout(new BorderLayout());
        interFrame.getContentPane().add(panel, BorderLayout.CENTER);
        interFrame.setSize(100, 100);
        desktop.add(interFrame);

        try {
            interFrame.setMaximum(true);
        } catch (PropertyVetoException ex) {
            ex.printStackTrace();
        }

        panel.setFocusable(true);
        interFrame.setVisible(true);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    loop = false;
                    socket.close();
                    remoteProxy.sendMessage(new Message("dispose"));
                    System.out.println("tat cua so teamview");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
}
