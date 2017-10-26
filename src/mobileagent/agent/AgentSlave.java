package mobileagent.agent;

import mobileagent.bean.Agent;
import com.ibm.aglet.*;
import com.ibm.aglet.event.MobilityAdapter;
import com.ibm.aglet.event.MobilityEvent;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.net.InetAddress;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.imageio.ImageIO;

public class AgentSlave extends Aglet implements Serializable{
    AgletProxy ap = null;
    transient String name = "Unknown";
    
    public void onCreation(Object o) {
        ap = (AgletProxy)o;
        addMobilityListener(new MobilityAdapter(){
            @Override
            public void onArrival(MobilityEvent me) {
                sendSystemInfo();
            }
        });
    }
    
    public void run() {
         System.out.println("Hello :)");
    }

    public boolean handleMessage(Message msg) {
        if (msg.sameKind("remote")){
        } else if(msg.sameKind("shutdown")){
            shutdown();
        } else if(msg.sameKind("restart")){
            restart();
        } else if(msg.sameKind("logout")){
            
        } else if(msg.sameKind("dispose")){
            dispose();
        }else if(msg.sameKind("capture")){
            capture();
        }else{
            return false;
        }
        return true;
    }
    
    public void sendSystemInfo(){
         try {
            name = System.getProperty("user.name");
            String os = System.getProperty("os.name");
            String architecture = System.getProperty("os.arch");
            String version = System.getProperty("os.version");
            String ip = "";
            InetAddress  myIP = InetAddress.getLocalHost();
            ip = myIP.getHostAddress();
            int port = getAgletContext().getHostingURL().getPort();
            AgletProxy agletProxy = getAgletContext().getAgletProxy(getAgletID());
            System.out.println("port ="+port);
            Agent agent = new Agent(getAgletID(), agletProxy, "" , getProxy().isActive()? "Active":"Inactive", name, ip);
            ap.sendOnewayMessage(new Message("systemInfo", agent));
         } catch (Exception ex) {
         }
    }
    
    public void shutdown(){
         try {
            Runtime.getRuntime().exec("shutdown -s -t 30 -c \"Your PC will shut down in 30s\"");
         } catch (IOException ex) {
         }
    }
    
    public void restart(){
        try {
            Runtime.getRuntime().exec("shutdown -r -t 30 -c \"Your PC will restart in 30s\"");
        } catch (IOException ex) {
        }
    }
    
    public void capture(){
        try {
             Toolkit toolkit = Toolkit.getDefaultToolkit();
             Dimension screenSize = toolkit.getScreenSize();
             Rectangle screenRect = new Rectangle(screenSize);
             Robot  robot = new Robot();
             BufferedImage img = robot.createScreenCapture(screenRect);
             ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
             ImageIO.write(img, "png", outputStream);
             byte[] byteImage = outputStream.toByteArray();
             ap.sendMessage(new Message("capture", byteImage));
        } catch (Exception ex) {  
            System.out.println(ex);
        }
    }
    
    public void sendText(String text) {
        try {
                if (ap == null) {
                        return;
                } 
                ap.sendMessage(new Message("text", name + " : " + text));
        } catch (Exception ex) {
                ex.printStackTrace();
        } 
    }
    
    public String getTime(){
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
        Date date = new Date();
        return dateFormat.format(date);
    }
}
