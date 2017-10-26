package mobileagent.library;

import mobileagent.render.HostTableModel;
import com.ibm.aglet.Aglet;
import com.ibm.aglet.AgletException;
import com.ibm.aglet.AgletProxy;
import java.io.IOException;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import mobileagent.bean.Host;

public class ScanHostIP {
    private final HostTableModel hostTableModel;
    private ExecutorService executor;
    private final Aglet serverAglet;
    private String ip;
    private int temp = 0;
    private final int threadsOfScan;

    public ScanHostIP(String ip, Aglet aglet, HostTableModel ipModel) {
        this.hostTableModel = ipModel;
        this.ip = ip;
        this.serverAglet = aglet;
        this.threadsOfScan = 32;
    }

    public void startScan() throws UnknownHostException, IOException{
        System.out.println(ip);
        temp = 0;
        ip = ip.substring(0, ip.lastIndexOf(".")+1);
        System.out.println(ip);
        hostTableModel.clear();
        executor = Executors.newFixedThreadPool(threadsOfScan);
        for (int i = 0; i < 255; i++) {
            executor.submit(new Runnable() {
                public void run() {
                    try {
                        pingIP();
                        Thread.sleep(500);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            });
        }
        executor.shutdown();
        new Thread(){
            @Override
            public void run() {
                while (!executor.isTerminated()) {   }  
                System.out.println("Finished all threads");
            }
        }.start();
    }
    
    public void pingIP(){
        String currentIp = "";
        synchronized (this){
            currentIp = ip+temp;
            temp++;
        }
        
        try {
            InetAddress address = InetAddress.getByName(currentIp);
            if(address.isReachable(3000)){
                String hostname = address.getHostName();
                String urlcheck = "atp://"+currentIp+":"+LibConfig.AGLET_DEFAULT_PORT;
                URL url = new URL(urlcheck);
                try{
                    Object obj[] = new Object[]{serverAglet.getProxy(), currentIp, hostname};
                    AgletProxy agletpx = serverAglet.getAgletContext().createAglet(serverAglet.getCodeBase(),"mobileagent.agent.AgentCheckPlatform" , obj);
                    agletpx.dispatch(url);
                }catch(AgletException e){
                    Host ipA = new Host(currentIp, hostname,"","","",0);
                    System.out.println(currentIp + "-" + hostname + "-" + 0);
                    hostTableModel.addRow(ipA);
                }
            }   
        } catch (Exception ex) {
        } 
    }
    
    public void stopScan(){
        executor.shutdown();
        while (!executor.isTerminated()) {   }  
        System.out.println("Finished");
        temp = 0;
        hostTableModel.clear();
    }
}
