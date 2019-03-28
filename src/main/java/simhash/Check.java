package simhash;


import java.util.*;
// welcome to mail to longcheng@58.com

public class Check {

    private final int[] bits = new int[64];
    private Set<Long> set;

    // you must rewrite this function
    public void init(Vector<String> docList) {
        set = new HashSet<>(docList.size());
        for (String s : docList) {
            long simhash = simhash(s);
            set.add(simhash);
        }
    }

    private long simhash(String doc) {
        Arrays.fill(bits, 0);
        for (int i = 0; i < doc.length(); i++) {
            long hash = hash64(doc.charAt(i) + "");
            for (int j = 63; j >= 0; j--) {
                if ((hash >> j & 1) == 1) {
                    bits[63 - j]++;
                } else {
                    bits[63 - j]--;
                }
            }
        }
        long hash = 0;
        long one = 1;
        for (int i = 0; i < 63; i++) {
            if (bits[i] > 0) {
                hash |= one;
            }
            one <<= 1;
        }
        Arrays.fill(bits, 0);
        return hash;
    }

    public int check(char[] info, int infoLen) {
        long simhash = simhash(new String(info));
        if (set.contains(simhash)) {
            return 1;
        }
        for (Long hash : set) {
            int i = hammingDistance(simhash, hash);
            System.out.println(i);
            if (i <= 16) {
                return 1;
            }
        }
        return 0;
    }

    private int hammingDistance(long hash1, long hash2) {
        return Long.bitCount(hash1 ^ hash2);
    }

    /**
     * Generates 64 bit hash from byte array of the given length and seed.
     *
     * @param data   byte array to hash
     * @param length length of the array to hash
     * @param seed   initial seed value
     * @return 64 bit hash of the given array
     */
    public static long hash64(final byte[] data, int length, int seed) {
        final long m = 0xc6a4a7935bd1e995L;
        final int r = 47;

        long h = (seed & 0xffffffffL) ^ (length * m);

        int length8 = length / 8;

        for (int i = 0; i < length8; i++) {
            final int i8 = i * 8;
            long k = ((long) data[i8 + 0] & 0xff) + (((long) data[i8 + 1] & 0xff) << 8)
                    + (((long) data[i8 + 2] & 0xff) << 16) + (((long) data[i8 + 3] & 0xff) << 24)
                    + (((long) data[i8 + 4] & 0xff) << 32) + (((long) data[i8 + 5] & 0xff) << 40)
                    + (((long) data[i8 + 6] & 0xff) << 48) + (((long) data[i8 + 7] & 0xff) << 56);

            k *= m;
            k ^= k >>> r;
            k *= m;

            h ^= k;
            h *= m;
        }

        switch (length % 8) {
            case 7:
                h ^= (long) (data[(length & ~7) + 6] & 0xff) << 48;
            case 6:
                h ^= (long) (data[(length & ~7) + 5] & 0xff) << 40;
            case 5:
                h ^= (long) (data[(length & ~7) + 4] & 0xff) << 32;
            case 4:
                h ^= (long) (data[(length & ~7) + 3] & 0xff) << 24;
            case 3:
                h ^= (long) (data[(length & ~7) + 2] & 0xff) << 16;
            case 2:
                h ^= (long) (data[(length & ~7) + 1] & 0xff) << 8;
            case 1:
                h ^= (long) (data[length & ~7] & 0xff);
                h *= m;
        }
        ;

        h ^= h >>> r;
        h *= m;
        h ^= h >>> r;

        return h;
    }

    /**
     * Generates 64 bit hash from byte array with default seed value.
     *
     * @param data   byte array to hash
     * @param length length of the array to hash
     * @return 64 bit hash of the given string
     */
    public static long hash64(final byte[] data, int length) {
        return hash64(data, length, 0xe17a1465);
    }

    /**
     * Generates 64 bit hash from a string.
     *
     * @param text string to hash
     * @return 64 bit hash of the given string
     */
    public static long hash64(final String text) {
        final byte[] bytes = text.getBytes();
        return hash64(bytes, bytes.length);
    }

    public static void main(String[] args) {
        Vector<String> vector = new Vector<>();
//        vector.add("二环博雅花园 3室2厅2卫 130㎡豪装带30平车库");
        vector.add("荣鑫花园 2室2厅1卫 1楼 带小花园的超便宜学区房");
        Check check = new Check();
        check.init(vector);
        int check1 = check.check("二环博雅花园 3室2厅2卫 130㎡高档装修免费送30平车库".toCharArray(), "二环博雅花园 3室2厅2卫 130㎡高档装修免费送30平车库".length());
//        int check2 = check.check("二环博雅花园 3室2厅2卫 130㎡高档装修免费送30平车库".toCharArray(), "二环博雅花园 3室2厅2卫 130㎡高档装修免费送30平车库".length());
        System.out.println(check1);
    }

}

