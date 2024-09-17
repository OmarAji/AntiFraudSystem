package antifraud.utility;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class IpUtility {


    public static boolean isValid(String ip) {
        if (ip == null || ip.isEmpty()) {
            return false;
        }
        try {
            InetAddress inet = InetAddress.getByName(ip);
            if (!(inet instanceof java.net.Inet4Address)) {
                return false;
            }
            String[] parts = ip.split("\\.");
            if (parts.length != 4) {
                return false;
            }
            for (String part : parts) {

                if (part.length() > 1 && part.startsWith("0")) {
                    return false;
                }
                int intPart = Integer.parseInt(part);
                if (intPart < 0 || intPart > 255) {
                    return false;
                }
            }
        } catch (UnknownHostException | NumberFormatException e) {
            return false;
        }
        return true;
    }
}
