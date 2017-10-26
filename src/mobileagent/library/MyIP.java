package mobileagent.library;

import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.SocketException;
import java.util.Enumeration;

public class MyIP {
    public static String getMyIp() {
        try {
            Enumeration<NetworkInterface> NICs = NetworkInterface.getNetworkInterfaces();
            while (NICs.hasMoreElements() == true) {
                NetworkInterface NIC = NICs.nextElement();
                Enumeration<InetAddress> IPs = NIC.getInetAddresses();
                while (IPs.hasMoreElements() == true) {
                    InetAddress IP = IPs.nextElement();
                    if (IP instanceof java.net.Inet4Address) {
//                        if (!IP.toString().startsWith("/127")) {
//                            return IP.toString().substring(1);
//                        }
                        System.out.println(IP);
                    }
                }
            }
        } catch (SocketException e4) {
        }
        return null;
    }
    
    public static void main(String[] args) throws IOException {
        System.out.println(getMyIp());
        ServerSocket sc1 = new ServerSocket(4434);
        ServerSocket sc2 = new ServerSocket(4435);
    }
}
