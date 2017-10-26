package mobileagent.bean;

import com.ibm.aglet.*;
import java.io.Serializable;

public class Agent implements Serializable{
    private AgletID aId;
    private AgletProxy aProxy;
    private String aName;
    private String aTime;
    private String aStatus;
    private String aIp;
    
    public Agent() {
        
    }

    public Agent(AgletID aId, AgletProxy aProxy, String aName, String aTime, String aStatus, String aIp) {
        this.aId = aId;
        this.aProxy = aProxy;
        this.aName = aName;
        this.aTime = aTime;
        this.aStatus = aStatus;
        this.aIp = aIp;
    }

    public AgletID getaId() {
        return aId;
    }

    public void setaId(AgletID aId) {
        this.aId = aId;
    }

    public AgletProxy getaProxy() {
        return aProxy;
    }

    public void setaProxy(AgletProxy aProxy) {
        this.aProxy = aProxy;
    }

    public String getaName() {
        return aName;
    }

    public void setaName(String aName) {
        this.aName = aName;
    }

    public String getaTime() {
        return aTime;
    }

    public void setaTime(String aTime) {
        this.aTime = aTime;
    }

    public String getaStatus() {
        return aStatus;
    }

    public void setaStatus(String aStatus) {
        this.aStatus = aStatus;
    }

    public String getaIp() {
        return aIp;
    }

    public void setaIp(String aIp) {
        this.aIp = aIp;
    }
}
