package mobileagent.library;

import mobileagent.render.HostTableModel;
import com.ibm.aglet.Aglet;
import com.ibm.aglet.AgletProxy;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import mobileagent.bean.Host;

public class SendNoti {

    private HostTableModel ipModel;
    private ExecutorService executor;
    private Aglet aglet;
    private int temp = 0;
    private final int threads;
    private final int port;
    private String noti;

    public SendNoti(Aglet aglet, HostTableModel ipModel) {
        this.ipModel = ipModel;
        this.aglet = aglet;
        this.threads = 64;
        this.port = 4434;
    }

    public void startSend(String noti) {
        this.noti = noti;
        executor = Executors.newFixedThreadPool(threads);
        for (int i = 0; i < 255; i++) {
            executor.submit(new Runnable() {
                public void run() {
                    send();
                }
            });
        }
    }

    public void send() {
        Host host = null;
        synchronized (this) {
            host = ipModel.getObject(temp);
            temp++;
        }
        System.out.println(host.getIp() + ":" + host.getPlatform());
        try {
            if (host.getPlatform() == 1) {
                URL url = new URL("atp://" + host.getIp() + ":" + port);
                AgletProxy ap = aglet.getAgletContext().createAglet(aglet.getCodeBase(), "mobileagent.agent.AgentNoti", noti);
                ap.dispatch(url);
            }
        } catch (Exception ex) {
        }
    }

    public void stopSend() {
        executor.shutdown();
        temp = 0;
        ipModel.clear();
    }
}
