package mobileagent.agent;

import mobileagent.library.ChatWindows;
import com.ibm.aglet.*;
import java.net.URL;

public class AgentChatServer extends Aglet {
	transient AgletProxy remoteProxy = null;
	String name = "ADMIN";
        URL url;
	ChatWindows window = null;

	public AgletProxy dispatchSlave() {
            try {
                AgletContext context = getAgletContext();
                AgletProxy proxy = context.createAglet(null,  "mobileagent.agent.AgentChatClient",  getProxy());
                remoteProxy = proxy.dispatch(url);
            }catch (Exception ex) {
                ex.printStackTrace();
            } 
            return remoteProxy;
        }
        
	public boolean handleMessage(Message msg) {
            if (msg.sameKind("dialog")) {
                window.show();
            } else if (msg.sameKind("text")) {
                String str = (String)msg.getArg();
                window.show();
                window.appendText(str);
                return true;
            } 
            return false;
        }
        
	public void onCreation(Object o) {
            url = (URL)o;
            window = new ChatWindows(AgentChatServer.this);
//            window.pack();
            window.show();
        }
        
	public void onDisposing() {
            if (window != null) {
                window.dispose();
                window = null;
            } 
                
            if (remoteProxy != null) {
                try {
                    remoteProxy.sendMessage(new Message("bye"));
                } catch (AgletException ex) {
                    ex.printStackTrace();
                } 
            } 
        }
        
	public void sendText(String text) {
            try {
                if (remoteProxy != null) {
                    remoteProxy.sendMessage(new Message("text", name + "  :\t" + text));
                } 
            } catch (Exception ex) {
                ex.printStackTrace();
            } 
        }
}