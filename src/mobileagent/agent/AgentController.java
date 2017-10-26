package mobileagent.agent;

import com.ibm.aglet.*;
import com.ibm.aglet.event.MobilityAdapter;
import com.ibm.aglet.event.MobilityEvent;
import java.io.IOException;

public class AgentController extends Aglet{
        String cmd;
    public void onCreation(Object o) {
        cmd = (String)o.toString();
        System.out.println(cmd);
        addMobilityListener(new MobilityAdapter(){
            @Override
            public void onArrival(MobilityEvent me) {
                switch(Integer.parseInt(cmd)){
                    case 0: System.out.println("Shutdown PC");
                            shutdown();
                            break;
                    case 1: restart();
                            break;
                    case 2: logout();
                            break;
                    default:
                }
            }
        });
    }
    
    public void run() {
        System.out.println("Receive msg from Server!!!");
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
    
    public void logout(){
        try {
            Runtime.getRuntime().exec("shutdown -l");
        } catch (IOException ex) {
        }
    }
}
