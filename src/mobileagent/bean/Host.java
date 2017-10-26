package mobileagent.bean;

import java.io.Serializable;

public class Host implements Serializable{
    private String ip;
    private String name;
    private String os;
    private String arch;
    private String version;
    private int platform;

    public Host() {
    }

    public Host(String ip, String name, String os, String arch, String version, int platform) {
        this.ip = ip;
        this.name = name;
        this.os = os;
        this.arch = arch;
        this.version = version;
        this.platform = platform;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getArch() {
        return arch;
    }

    public void setArch(String arch) {
        this.arch = arch;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public int getPlatform() {
        return platform;
    }

    public void setPlatform(int platform) {
        this.platform = platform;
    }

    public Object getaProxy() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
