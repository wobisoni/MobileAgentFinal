package mobileagent.library;

import java.net.InetAddress;
import static java.net.InetAddress.getLocalHost;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LibConfig {

    public static final int AGLET_DEFAULT_PORT = 4434;
    public static String IMAGE_PATH = "F://TestPic";
    public static String SERVER_IP = "";

    public static String getMyIp() {
        String myIp = "";
        try {
            InetAddress inetAdd = getLocalHost();
            myIp = inetAdd.getHostAddress();
        } catch (UnknownHostException ex) {
            System.err.println(ex);
        }
        return myIp;
    }

    public static URL createAgletURL(String ip) {
        URL url = null;
        String stringURL = "atp://" + ip + ":" + LibConfig.AGLET_DEFAULT_PORT;
        try {
            url = new URL(stringURL);
        } catch (MalformedURLException ex) {
            System.err.println(ex);
        }
        return url;
    }

    public static String getCurrentTime() {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
        Date date = new Date();
        return dateFormat.format(date);
    }
}
