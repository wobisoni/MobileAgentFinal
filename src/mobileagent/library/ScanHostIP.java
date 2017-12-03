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
import mobileagent.GUI.ServerWindows;
import mobileagent.bean.Host;

public class ScanHostIP {

    private final HostTableModel hostTableModel;
    private final int threadsOfScan;
    private final Aglet serverAglet;
    private final ServerWindows serverWindows;
    private ExecutorService executor;
    private String ip;
    private int temp = 0;

    public ScanHostIP(String ip, Aglet aglet, HostTableModel ipModel, ServerWindows sw) {
        this.hostTableModel = ipModel;
        this.ip = ip;
        this.serverAglet = aglet;
        this.threadsOfScan = 64;
        this.serverWindows = sw;
    }

    public void startScan() throws UnknownHostException, IOException {
        temp = 0;
        ip = ip.substring(0, ip.lastIndexOf(".") + 1);
        hostTableModel.clear();
        serverWindows.setProcessBarValue(0);
        serverWindows.setProcessBarStatus(true);
        executor = Executors.newFixedThreadPool(threadsOfScan);
        for (int i = 0; i < 255; i++) {
            executor.submit(new Runnable() {
                public void run() {
                    pingIP();
                }
            });
        }
        executor.shutdown();
        new Thread() {
            @Override
            public void run() {
                while (!executor.isTerminated()) {
                }
                serverWindows.setProcessBarValue(300);
                serverWindows.setProcessBarStatus(false);
            }
        }.start();
    }

    public void pingIP() {
        String currentIp = "";
        synchronized (this) {
            currentIp = ip + temp;
            temp++;
        }

        try {
            InetAddress address = InetAddress.getByName(currentIp);
            if (address.isReachable(3000)) {
                String hostname = address.getHostName();
                AgletProxy agletpx = serverAglet.getAgletContext().createAglet(serverAglet.getCodeBase(), "mobileagent.agent.AgentCheckPlatform", serverAglet.getProxy());

                String urlcheck = "atp://" + currentIp + ":" + LibConfig.AGLET_DEFAULT_PORT;
                URL url = new URL(urlcheck);

                try {
                    agletpx.dispatch(url);
                } catch (Exception e) {
                    serverWindows.setProcessBarValue(temp);
                    Host host = new Host(currentIp, hostname, "", "", "", 0);
                    hostTableModel.addRow(host);
                    agletpx.dispose();
                }
            }
        } catch (UnknownHostException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (AgletException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (InstantiationException ex) {
            ex.printStackTrace();
        }
    }

    public void stopScan() {
        executor.shutdown();
        while (!executor.isTerminated()) {
        }
        System.out.println("Finished");
        temp = 0;
        hostTableModel.clear();
    }
}
