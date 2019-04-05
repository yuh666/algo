package bitset;

import java.util.BitSet;

public class Main {

    public static void main(String[] args) {
        BitSet bitSet = new BitSet();
        BitSet bitSet1 = new BitSet();
        for (int i = 0; i < 100; i++) {
            bitSet.set(i);
            bitSet1.set(i%50);
        }


        long l = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
//            int c = 0;
//            for (int j = 0; j < 50; j++) {
//                if(bitSet.get(j)){
//                     c++;
//                }
//            }
            bitSet.and(bitSet1);
            bitSet.cardinality();
        }
        System.out.println(System.nanoTime()-l);
    }
}
