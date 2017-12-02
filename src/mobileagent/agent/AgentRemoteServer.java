package mobileagent.agent;

import com.ibm.aglet.*;
import java.io.DataInputStream;
import java.io.Serializable;
import java.net.Socket;
import java.net.URL;
import mobileagent.library.RemoteWindows;

public class AgentRemoteServer extends Aglet implements Serializable {

    private AgletProxy ap;
    private AgletProxy apClient;
    private Socket socket;
    private DataInputStream dis;
    private int defaultPort;
    private int port;
    private String ip;
    private String width;
    private String height;

    @Override
    public void onCreation(Object o) {
        Object object[] = (Object[]) o;
        ap = (AgletProxy) object[0];
        ip = (String) object[1];
        port = Integer.parseInt(object[2].toString());
        defaultPort = 4434;
        try {
            Object obj[] = new Object[]{getProxy(), port};
            apClient = getAgletContext().createAglet(getCodeBase(), "mobileagent.agent.AgentRemoteClient", obj);
            URL url = new URL("atp://" + ip + ":" + defaultPort);
            apClient = apClient.dispatch(url);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public boolean handleMessage(Message msg) {
        if (msg.sameKind("remote")) {
            try {
                socket = new Socket(ip, port);
                System.out.println("Connecting to the Client");
                dis = new DataInputStream(socket.getInputStream());
                width = dis.readUTF();
                height = dis.readUTF();
                RemoteWindows frame = new RemoteWindows(null, socket, width, height);
                frame.create();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (msg.sameKind("dispose")) {
            dispose();
        } else {
            return false;
        }
        return true;
    }

    @Override
    public void onDisposing() {
        try {
            socket.close();
            apClient.sendOnewayMessage(new Message("dispose"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
