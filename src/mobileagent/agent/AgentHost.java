package mobileagent.agent;

import mobileagent.GUI.ServerWindows;
import mobileagent.library.ImageDemo;
import com.ibm.aglet.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.Socket;
import javax.imageio.ImageIO;
import mobileagent.bean.Host;
import mobileagent.library.CreateFrame;

public class AgentHost extends Aglet implements Serializable{
    transient ServerWindows serverWindow;
    String response;
    transient AgletProxy remoteProxy = null;
        
    @Override
    public void onCreation(Object o) {
        serverWindow = new ServerWindows(this);
        serverWindow.setVisible(true);
    }
    
    @Override
    public void run() {
    }
    
    public boolean handleMessage(Message msg) {
        if (msg.sameKind("systemInfo")){
            response = (String)msg.getArg();
            new Thread(){
                @Override
                public void run() {
                    String str[] = response.split("' '");
                    Host host = new Host(str[0], str[1], str[2], str[3], str[4], 1);
                    serverWindow.mListIp.addRow(host);
                }
            }.start();
        }else if(msg.sameKind("capture")){
            try {
                File  file = new File("F:\\TestPic\\"+System.nanoTime()+".jpg");
                byte[] byteImage = (byte[]) msg.getArg();
                System.out.println("bi "+byteImage);
                InputStream inputStream = new ByteArrayInputStream(byteImage);
                BufferedImage bi = ImageIO.read(inputStream);
                ImageDemo id = new ImageDemo(bi);
                id.setVisible(true);
                ImageIO.write(bi, "png", file);
            } catch (IOException ex) {
                ex.printStackTrace();
            } 
        }else if(msg.sameKind("remote")){
            try{
                String width = "";
                String height = "";
                String ip = (String)msg.getArg();
                Socket socket = new Socket(ip,4000);
                System.out.println("Connecting to the Server");
		DataInputStream	dis = new DataInputStream(socket.getInputStream());
                String start = dis.readUTF();
                System.out.println("start: "+start);
                if(start.equals("start")){
                    width = dis.readUTF();
                    height = dis.readUTF();
                }
                CreateFrame abc= new CreateFrame(null , socket, width, height);
            } catch (Exception ex){
                ex.printStackTrace();
            }
        }else{
            return false;
        }
        return true;
    }
}