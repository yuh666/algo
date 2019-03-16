package str;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class BM {

    public static int indexOf(String mainStr, String pStr) {
        //构建坏字符回退Map
        Map<Character, Integer> bcMap = generateBC(pStr);
        //构建好后缀回退suffix 以及 部分匹配prefix
        int[] suffix = new int[pStr.length()];
        boolean[] prefix = new boolean[pStr.length()];
        generateGS(suffix, prefix, pStr);
        //匹配
        for (int i = 0; i <= mainStr.length() - pStr.length(); ) {
            int j;
            for (j = pStr.length() - 1; j >= 0; j--) {
                if (pStr.charAt(j) != mainStr.charAt(i + j)) {
                    break;
                }
            }
            if (j == -1) {
                return i;
            }
            Integer index = bcMap.get(mainStr.charAt(i + j));
            int bc = index == null ? j + 1 : j - index;
            int gs = 0;
            if (j != pStr.length() - 1) {
                gs = moveByGS(suffix, prefix, j, pStr);
            }
            i += Math.max(bc, gs);
        }
        return -1;
    }

    private static int moveByGS(int[] suffix, boolean[] prefix, int j, String pStr) {
        int suffix1 = suffix[pStr.length() - j - 1];
        if (suffix1 != -1) {
            return j + 1 - suffix1;
        }
        for (int i = j + 2; i < pStr.length(); i++) {
            if (prefix[pStr.length() - i]) {
                return i;
            }
        }
        return pStr.length();
    }

    private static void generateGS(int[] suffix, boolean[] prefix, String pStr) {
        Arrays.fill(suffix, -1);
        Arrays.fill(prefix, false);
        for (int i = 0; i < pStr.length() - 1; i++) {
            int j = i;
            //长度
            int k = 0;
            while (j >= 0 && pStr.charAt(j) == pStr.charAt(pStr.length() - 1 - k)) {
                k++;
                suffix[k] = j;
                j--;
            }
            if (j == -1) {
                prefix[k] = true;
            }
        }
    }

    private static Map<Character, Integer> generateBC(String pStr) {
        HashMap<Character, Integer> map = new HashMap<>(pStr.length());
        for (int i = 0; i < pStr.length(); i++) {
            map.put(pStr.charAt(i), i);
        }

        return map;
    }

    public static void main(String[] args) {
        String mainStr = "ababababababababa  bc";
        String pStr = "abc";
//        int[] su = new int[pStr.length()];
//        boolean[] pre = new boolean[pStr.length()];
//        generateGS(su, pre, pStr);
//        System.out.println(Arrays.toString(su));
//        System.out.println(Arrays.toString(pre));
        System.out.println(indexOf(mainStr, pStr));
    }
}
