package mobileagent.agent;

import com.ibm.aglet.Aglet;
import com.ibm.aglet.AgletProxy;
import com.ibm.aglet.Message;
import java.awt.image.BufferedImage;
import mobileagent.GUI.ServerWindows;
import mobileagent.library.ShowImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import mobileagent.bean.Host;
import mobileagent.library.LibConfig;

public class AgentServer extends Aglet {

    private ServerWindows serverWindow;

    @Override
    public void onCreation(Object o) {
        serverWindow = new ServerWindows(this);
        serverWindow.setVisible(true);
    }

    public boolean handleMessage(Message msg) {
        if (msg.sameKind("systemInfo")) {
            synchronized (this) {
                Host host = (Host) msg.getArg();
                serverWindow.add(host);
            }
        } else if (msg.sameKind("capture")) {
            try {
                File file = new File(LibConfig.IMAGE_PATH + System.nanoTime() + ".png");
                byte[] byteImage = (byte[]) msg.getArg();
                System.out.println("bi " + byteImage);
                InputStream inputStream = new ByteArrayInputStream(byteImage);
                BufferedImage bi = ImageIO.read(inputStream);
                ShowImage id = new ShowImage(bi);
                id.setVisible(true);
                ImageIO.write(bi, "png", file);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else {
            return false;
        }
        return true;
    }
}
