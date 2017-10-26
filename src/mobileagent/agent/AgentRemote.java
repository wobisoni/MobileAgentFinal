package mobileagent.agent;

import com.ibm.aglet.*;
import com.ibm.aglet.event.*;
import java.awt.*;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;
import mobileagent.library.ReceiveEvents;
import mobileagent.library.SendScreen;

public class AgentRemote extends Aglet{
    AgletProxy ap;
    String ip;
    Rectangle rectangle;
    Robot robot = null;
    ServerSocket ssocket;
    int defaultPort = 4000;
    boolean loop;
    String width;
    String height;
    
    @Override
    public void onCreation(Object o) {
        Object obj[] = (Object[])o;
        ap = (AgletProxy)obj[0];
        ip = (String)obj[1];
        addMobilityListener(new MobilityAdapter(){
            @Override
            public void onArrival(MobilityEvent me) {
                try {
                    loop = true;
                    ssocket = new ServerSocket(defaultPort);
                    ap.sendOnewayMessage(new Message("remote", ip));
                    remote();
                } catch (Exception ex) {
                    ex.printStackTrace();
                } 
                
            }
        });
    }

    @Override
    public boolean handleMessage(Message msg) {
        if(msg.sameKind("dispose")){
            System.out.println("Huy tac tu remote!");
            dispose();
        }else{
            return false;
        }
        return true;
    }

    public void remote() {
        Robot robot = null;
        Rectangle rectangle = null;
        try{
            GraphicsEnvironment gEnv = GraphicsEnvironment.getLocalGraphicsEnvironment();
            GraphicsDevice gDev = gEnv.getDefaultScreenDevice();
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            width=""+dim.getWidth();
            height=""+dim.getHeight();
            rectangle = new Rectangle(dim);
            robot=new Robot(gDev);
            
            System.out.println("Awaiting Connection from AgletHost:");
            
            while(loop){
                System.out.print(".");
                Socket socket = ssocket.accept();
                DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
                dos.writeUTF("start");
                dos.writeUTF(width);
                dos.writeUTF(height);
                new SendScreen(socket,robot,rectangle);
                new ReceiveEvents(socket,robot);
            }
        }catch (Exception ex){
            loop = false;
            System.out.println("Aglet da dung lai");
            try {
                ssocket.close();
            } catch (IOException ex1) {
                ex1.printStackTrace();
            }
            dispose();
        }
    }
}
