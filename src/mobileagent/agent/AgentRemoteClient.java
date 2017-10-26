package mobileagent.agent;

import com.ibm.aglet.*;
import com.ibm.aglet.event.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.DataOutputStream;
import java.io.OutputStream;
import java.net.*;
import java.util.Scanner;
import javax.imageio.ImageIO;

public class AgentRemoteClient extends Aglet{
    private AgletProxy ap;
    private Rectangle rectangle;
    private Robot robot;
    private ServerSocket serverSocket;
    private Socket socket;
    private OutputStream os;
    private DataOutputStream dos;
    private boolean continueLoop;
    private int port;
    private String width;
    private String height;
    private Thread sendScreen;
    private Thread receiveEvents;
    
    @Override
    public void onCreation(Object o) {
        Object object[] = (Object[])o;
        ap = (AgletProxy)object[0];
        port = Integer.parseInt(object[1].toString());
        
        addMobilityListener(new MobilityAdapter(){
            @Override
            public void onArrival(MobilityEvent me) {
                try {
                    continueLoop = true;
                    serverSocket = new ServerSocket(port);
                    ap.sendOnewayMessage(new Message("remote", null));
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
            System.out.println("Huy tac tu remote client!");
            continueLoop = false;
            try {
                dos.close();
                os.close();
                sendScreen.stop();
                receiveEvents.stop();
                socket.close();
                serverSocket.close();
                dispose();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }else{
            return false;
        }
        return true;
    }

    public void remote() {
        try{
            GraphicsEnvironment gEnv = GraphicsEnvironment.getLocalGraphicsEnvironment();
            GraphicsDevice gDev = gEnv.getDefaultScreenDevice();
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            width = String.valueOf(dim.getWidth());
            height = String.valueOf(dim.getHeight());
            rectangle = new Rectangle(dim);
            robot = new Robot(gDev);
            
            System.out.println("Awaiting Connection from AgletHost:");
            socket = serverSocket.accept();
            
            dos = new DataOutputStream(socket.getOutputStream());
            dos.writeUTF(width);
            dos.writeUTF(height);
            
            sendScreen = new Thread("sendScreen"){
                public void run() {
                    sendScreen();
                }
            };
            sendScreen.start();
            
            receiveEvents = new Thread("receiveEvents"){
                @Override
                public void run() {
                    receiveEvents();
                }
            };
            receiveEvents.start();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    
    public void sendScreen(){
        while(continueLoop){
            try{
                os = socket.getOutputStream();
                BufferedImage bi = robot.createScreenCapture(rectangle);
                ImageIO.write(bi,"jpeg",os);
                Thread.sleep(10);
            }catch(Exception ex){
                continueLoop = false;
                ex.printStackTrace();
            }
        }
    }
    
    public void receiveEvents(){
        try {
            Scanner scanner = new Scanner(socket.getInputStream());
            while(continueLoop){
                int command = scanner.nextInt();
                switch(command){
                    case -1:
                        robot.mousePress(scanner.nextInt());
                        break;
                    case -2:
                        robot.mouseRelease(scanner.nextInt());
                        break;
                    case -3:
                        robot.keyPress(scanner.nextInt());
                        break;
                    case -4:
                        robot.keyRelease(scanner.nextInt());
                        break;
                    case -5:
                        robot.mouseMove(scanner.nextInt(),scanner.nextInt());
                        break;
                }
            }
        }catch(Exception ex){
            continueLoop = false;
            ex.printStackTrace();
        }
    }
}
