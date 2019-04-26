package bitmap;

/**
 * @author yuh
 * @date 2019-04-26 10:09
 **/
public class MyBitMap {

    private int nBits;
    private long[] data;

    public MyBitMap(int nBits) {
        this.nBits = nBits;
        data = new long[nBits / 64 + 1];
    }

    public void add(int a) {
        int idx1 = a / 64;
        int idx2 = a % 64;
        data[idx1] |= (1 << idx2);
    }

    public boolean exist(int a) {
        int idx1 = a / 64;
        int idx2 = a % 64;
        return (int) (data[idx1] >> idx2 & 1) == 1;
    }

    public static void main(String[] args) {
        MyBitMap myBitMap = new MyBitMap(1000);
        myBitMap.add(1);
        myBitMap.add(10);
        myBitMap.add(100);

        System.out.println(myBitMap.exist(1));
        System.out.println(myBitMap.exist(10));
        System.out.println(myBitMap.exist(100));
        System.out.println(myBitMap.exist(1000));
    }
}
