package mobileagent.agent;

import com.ibm.aglet.Aglet;
import com.ibm.aglet.AgletProxy;
import com.ibm.aglet.Message;
import com.ibm.aglet.event.MobilityAdapter;
import com.ibm.aglet.event.MobilityEvent;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.net.URL;
import javax.imageio.ImageIO;

public class AgentCapture extends Aglet {

    private AgletProxy ap;
    ByteArrayOutputStream outputStream;

    public void onCreation(Object o) {
        ap = (AgletProxy) o;
        addMobilityListener(new MobilityAdapter() {
            @Override
            public void onArrival(MobilityEvent me) {
                try {
                    capture();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }
        });
    }

    public void capture() {
        try {
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            Dimension screenSize = toolkit.getScreenSize();
            Rectangle screenRect = new Rectangle(screenSize);
            Robot robot = new Robot();
            BufferedImage img = robot.createScreenCapture(screenRect);
            outputStream = new ByteArrayOutputStream();
            ImageIO.write(img, "png", outputStream);
            byte[] byteImage = outputStream.toByteArray();
            System.out.println(ap.getAddress());
            ap.sendMessage(new Message("capture", byteImage));
            dispose();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public boolean handleMessage(Message msg) {
        if (msg.sameKind("dispose")) {
            System.out.println("Huy tac tu capture!");
            this.dispose();
        } else {
            return false;
        }
        return true;
    }
}
