package mobileagent.agent;

import com.ibm.aglet.Aglet;
import com.ibm.aglet.AgletProxy;
import com.ibm.aglet.Message;
import com.ibm.aglet.event.MobilityAdapter;
import com.ibm.aglet.event.MobilityEvent;
import mobileagent.bean.Host;

public class AgentCheckPlatform extends Aglet {

    private AgletProxy serverAgletProxy;
    private String localIp;
    private String localName;

    @Override
    public void onCreation(Object o) {
        Object obj[] = (Object[]) o;
        serverAgletProxy = (AgletProxy) obj[0];
        localIp = (String) obj[1];
        localName = (String) obj[2];
        addMobilityListener(new MobilityAdapter() {
            public void onArrival(MobilityEvent me) {
                sendSystemInfo();
                try {
                    Thread.sleep(5000);
                    dispose();
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    public void run() {
        try {
            Thread.sleep(5000);
            dispose();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    public void sendSystemInfo() {
        try {
            String os = System.getProperty("os.name");
            if (os.toLowerCase().contains("window")) {
                os = "Windows";
            } else if (os.toLowerCase().contains("linux")) {
                os = "Linux";
            } else if (os.toLowerCase().contains("mac")) {
                os = "Mac";
            }
            String architecture = System.getProperty("os.arch");
            String version = System.getProperty("os.version");
            Host host = new Host(localIp, localName, os, architecture, version, 1);
            Message msg = new Message("systemInfo", host);
            System.out.println("agen: " + msg);
            serverAgletProxy.sendOnewayMessage(msg);
            dispose();
        } catch (Exception ex) {
        }
    }
}
