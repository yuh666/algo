package str;

import java.util.regex.Pattern;

/**
 * @author yuh
 * @date 2019-03-14 11:06
 **/
public class IpMatch {
    static String ip = "([1-9]|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}";
    static Pattern pattern = Pattern.compile(ip);

    public static boolean isIp(String ip){
        return pattern.matcher(ip).matches();
    }

    private static boolean mayBeIp(String imei) {
        return imei != null
                && (imei.charAt(2) == '.' || imei.charAt(3) == '.' || imei.charAt(1) == '.');
    }


    public static void main(String[] args) {
        String ip = "10.37.9.1";
        long l = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            mayBeIp(ip);
        }
        System.out.println(System.currentTimeMillis()-l);
    }
}
