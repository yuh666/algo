package dateformat;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @author yuh
 * @date 2019-04-24 11:44
 **/
public class Main {
    static int[] arr = new int[100000];
    public static void main(String[] args) {
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd EEEE HH:mm");
//        System.out.println(simpleDateFormat.format(new Date()).replaceAll("星期","周"));
        long l = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            test();
        }
        System.out.println(System.currentTimeMillis() - l);
    }

    public static void test() {
//        int[] arr = new int[100000];
        Arrays.fill(arr,0);
    }
}
