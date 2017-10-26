package mobileagent.agent;

import com.ibm.aglet.Aglet;
import com.ibm.aglet.AgletProxy;
import com.ibm.aglet.Message;
import com.ibm.aglet.event.MobilityAdapter;
import com.ibm.aglet.event.MobilityEvent;

public class AgentCheckPlatform extends Aglet{
    AgletProxy ap;
    String ip;
    String name;
    
    @Override
    public void onCreation(Object o) {
        Object obj[] = (Object[])o;
        ap = (AgletProxy)obj[0];
        ip = (String)obj[1];
        name = (String)obj[2];
        addMobilityListener(new MobilityAdapter(){
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
    
    public void sendSystemInfo(){
        try {
            String os = System.getProperty("os.name");
            if(os.toLowerCase().contains("window")){
                os = "Windows";
            }else if(os.toLowerCase().contains("linux")){
                os = "Linux";
            }else if(os.toLowerCase().contains("mac")){
                os = "Mac";
            }
            String architecture = System.getProperty("os.arch");
            String version = System.getProperty("os.version");
            String response = ip+"' '"+name+"' '"+os+"' '"+architecture+"' '"+version;
            Message msg = new Message("systemInfo", response);
            System.out.println("agen: "+msg);
            ap.sendOnewayMessage(msg);
            dispose();
        } catch (Exception ex) {
        }
    }
}
