package concurrent.counter;

import java.util.concurrent.atomic.LongAdder;

/**
 * @author yuh
 * @date 2019-03-11 16:40
 **/
public class LongCounter {

    public static void main(String[] args) {
        LongAdder adder = new LongAdder();
        for (int i = 0; i < 1000000; i++) {
            adder.add(1);
        }
        adder.add(-100L);
        System.out.println(adder.sum());
    }
}
