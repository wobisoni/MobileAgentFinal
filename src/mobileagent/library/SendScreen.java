package mobileagent.library;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;


public class SendScreen extends Thread{
    Socket socket = null;
    Robot robot = null;
    Rectangle rectangle = null;
    boolean continueLoop = true;

    OutputStream os = null;

    public SendScreen(Socket sc,Robot robot,Rectangle rect) {
        this.socket = sc;
        this.robot = robot;
        rectangle = rect;
        start();
    }

    public void run(){
        try{
            os = socket.getOutputStream();
        }catch(IOException ex){
            ex.printStackTrace();
        }

        while(continueLoop){
            BufferedImage bi = robot.createScreenCapture(rectangle);
            try{
                ImageIO.write(bi,"jpeg",os);
                Thread.sleep(10);
            }catch(Exception ex){
                continueLoop = false;
                try {
                    socket.close();
                } catch (IOException ex1) {
                    ex1.printStackTrace();
                }
            }
        }
    }
}

