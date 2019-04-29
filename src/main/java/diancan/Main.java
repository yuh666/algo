package diancan;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author yuh
 * @date 2019-04-28 10:37
 **/
public class Main {

    public static void main(String[] args) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("MM-dd");
        Date start = simpleDateFormat.parse("2019-04-01");
        Calendar instance = Calendar.getInstance();
        instance.setTime(start);
        int i =0;
        while (instance.get(Calendar.MONTH) < 4) {
            int day = instance.get(Calendar.DAY_OF_WEEK);
            if (day == 1 || day == 7) {
                instance.add(Calendar.DAY_OF_WEEK, 1);
                continue;
            }
            System.out.println(simpleDateFormat1.format(instance.getTime())+"（2人）：于昊，杨玉娟");
            instance.add(Calendar.DAY_OF_WEEK, 1);
            i++;
        }
        System.out.println("总共报销"+i*50+"元");
    }
}
