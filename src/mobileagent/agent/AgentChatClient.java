package mobileagent.agent;

import mobileagent.library.ChatWindows;
import com.ibm.aglet.*;
import com.ibm.aglet.event.*;

public class AgentChatClient extends Aglet {

    private String name = "Unknown";
    private ChatWindows window = null;
    private AgletProxy masterProxy = null;

    public boolean handleMessage(Message msg) {
        if (msg.sameKind("dialog")) {
            window.show();
        } else if (msg.sameKind("text")) {
            String str = (String) msg.getArg();
            window.show();
            window.appendText(str);
            return true;
        } else if (msg.sameKind("dispose")) {
            window.appendText("Bye Bye..");
            dispose();
        }
        return false;
    }

    public void onCreation(Object o) {
        masterProxy = (AgletProxy) o;
        addMobilityListener(new MobilityAdapter() {
            public void onArrival(MobilityEvent ev) {
                window = new ChatWindows(AgentChatClient.this);
                window.show();
                try {
                    name = System.getProperty("user.name");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    public void onDisposing() {
        if (window != null) {
            window.dispose();
            window = null;
        }

    }

    public void sendText(String text) {
        try {
            if (masterProxy == null) {
                return;
            }
            masterProxy.sendMessage(new Message("text", name + "  :\t" + text));
        } catch (Exception ex) {

        }
    }
}
