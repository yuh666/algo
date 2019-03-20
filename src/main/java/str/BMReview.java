package str;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class BMReview {

    public static int indexOf(String mainStr, String pStr) {
        int mainLength = mainStr.length();
        int pLength = pStr.length();
        int i = 0;
        Map<Character, Integer> bcMap = generateBcMap(pStr);
        int[] suffix = new int[pLength];
        boolean[] prefix = new boolean[pLength];
        generateSuffixAndPrefix(suffix, prefix, pStr);
        while (i <= mainLength - pLength) {
            int j;
            for (j = pLength - 1; j >= 0; j--) {
                if (pStr.charAt(j) != mainStr.charAt(i + j)) {
                    break;
                }
            }
            if (j == -1) {
                return i;
            }
            int bc = j + 1;
            Integer integer = bcMap.get(mainStr.charAt(i + j));
            if (integer != null) {
                bc = j - integer;
            }
            int gs = 0;
            if (j != pLength - 1) {
                gs = moveByGS(suffix, prefix, j, pLength);
            }
            i += Math.max(bc, gs);
        }
        return -1;
    }


    public static Map<Character, Integer> generateBcMap(String pStr) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < pStr.length(); i++) {
            map.put(pStr.charAt(i), i);
        }
        return map;
    }

    public static void generateSuffixAndPrefix(int[] suffix, boolean[] prefix, String pStr) {
        Arrays.fill(suffix, -1);
        Arrays.fill(prefix, false);
        int length = pStr.length();
        for (int i = 0; i < length - 1; i++) {
            int k = 0;
            int j = i;
            while (j >= 0 && pStr.charAt(j) == pStr.charAt(length - 1 - k)) {
                k++;
                suffix[k] = j;
                j--;
            }
            if (j == -1) {
                prefix[k] = true;
            }
        }
    }

    public static int moveByGS(int[] suffix, boolean[] prefix, int j, int length) {
        int match = length - j - 1;
        int su = suffix[match];
        if (su != -1) {
            return j + 1 - su;
        }
        for (int i = j + 2; i < length; i++) {
            if (prefix[length - i]) {
                return i;
            }
        }
        return length;
    }


    public static void main(String[] args) {
        String mainStr = "abababababababababc";
        String pStr = "abc";
//        int[] su = new int[pStr.length()];
//        boolean[] pre = new boolean[pStr.length()];
//        generateGS(su, pre, pStr);
//        System.out.println(Arrays.toString(su));
//        System.out.println(Arrays.toString(pre));
        System.out.println(indexOf(mainStr, pStr));
    }
}
