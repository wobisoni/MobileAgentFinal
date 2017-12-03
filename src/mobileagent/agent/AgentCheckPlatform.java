package mobileagent.agent;

import com.ibm.aglet.Aglet;
import com.ibm.aglet.AgletProxy;
import com.ibm.aglet.Message;
import com.ibm.aglet.event.MobilityAdapter;
import com.ibm.aglet.event.MobilityEvent;
import java.net.InetAddress;
import mobileagent.bean.Host;

public class AgentCheckPlatform extends Aglet {

    private AgletProxy serverAgletProxy;

    @Override
    public void onCreation(Object o) {
        serverAgletProxy = (AgletProxy)o;
        
        addMobilityListener(new MobilityAdapter() {
            public void onArrival(MobilityEvent me) {
                System.out.println("Hello");
                sendSystemInfo();
            }
        });
    }

    public void sendSystemInfo() {
        try {
            String os = System.getProperty("os.name");
            if (os.toLowerCase().contains("window")) {
                os = "Windows";
            } else if (os.toLowerCase().contains("linux")) {
                os = "Linux";
            } else if (os.toLowerCase().contains("mac")) {
                os = "MacOS";
            }
            InetAddress addr = InetAddress.getLocalHost();
            String localName = addr.getHostName();
            String architecture = System.getProperty("os.arch");
            String version = System.getProperty("os.version");
            int vers = (int)(Float.parseFloat(version)*10);
            switch (vers){
                case 40: version = "Windows 95"; break;
                case 41: version = "Windows 98"; break;
                case 51: version = "Windows XP"; break;
                case 60: version = "Windows vista"; break;
                case 61: version = "Windows 7"; break;
                case 62: version = "Windows 8/8.1/10"; break;
            }
            
            Host host = new Host(addr.getHostAddress(), localName, os, architecture, version, 1);
            Message msg = new Message("systemInfo", host);
            
            serverAgletProxy.sendOnewayMessage(msg);
            dispose();
        } catch (Exception ex) {
        }
    }
}
