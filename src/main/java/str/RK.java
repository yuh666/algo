package str;

import java.util.Arrays;

/**
 * @author yuh
 * @date 2019-03-14 09:49
 **/
public class RK {


    public static int indexOf(String mainStr, String pStr) {
        int[] base = new int[pStr.length()];
        for (int i = 0; i < base.length; i++) {
            base[i] = (int) Math.pow(26, base.length - i - 1);
        }


        int n = mainStr.length();
        int m = pStr.length();

        int mHash = hash(pStr, 0, m - 1, base);
        int nHash = 0;

        for (int i = 0; i <= n - m; i++) {
            if (i == 0) {
                nHash = hash(mainStr, 0, m - 1, base);
            } else {
                nHash = (nHash - mainStr.charAt(i - 1) * base[0]) * 26 + mainStr.charAt(i + m - 1);
            }
            if (mHash != nHash) {
                continue;
            }
            int j;
            for (j = 0; j < m; j++) {
                if (mainStr.charAt(i + j) != pStr.charAt(j)) {
                    break;
                }
            }
            if (j == m) {
                return i;
            }
        }
        return -1;
    }

    public static int[] indexOf(char[][] main, char[][] p) {


        int n1 = main.length;
        int n2 = main[0].length;

        int m1 = p.length;
        int m2 = p[0].length;


        StringBuilder pSB = new StringBuilder();

        for (int i = 0; i < p.length; i++) {
            for (int j = 0; j < p[0].length; j++) {
                pSB.append(p[i][j]);
            }
        }

        int[] base = new int[pSB.length()];
        for (int i = 0; i < base.length; i++) {
            base[i] = (int) Math.pow(26, base.length - i - 1);
        }

        int pHash = hash(pSB.toString(), 0, pSB.length() - 1, base);

        StringBuilder mainSB = new StringBuilder();

        for (int i = 0; i <= n1 - m1; i++) {
            for (int j = 0; j <= n2 - m2; j++) {
                mainSB.delete(0, mainSB.length());
                for (int k = i; k < i + m1; k++) {
                    for (int u = j; u < j + m2; u++) {
                        mainSB.append(main[k][u]);
                    }
                }
                if (hash(mainSB.toString(), 0, mainSB.length() - 1, base) != pHash) {
                    continue;
                }
                int k;
                for (k = 0; k < mainSB.length(); k++) {
                    if (mainSB.charAt(k) != pSB.charAt(k)) {
                        break;
                    }
                }
                if (k == mainSB.length()) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{-1, -1};
    }

    private static int hash(String str, int i, int j, int[] base) {
        int sum = 0;
        for (int k = i; k <= j; k++) {
            sum += str.charAt(k) * base[k - i];
        }
        return sum;
    }

    public static void main(String[] args) {
//        String n = "abcd";
//        String m = "ccd";
//
//        System.out.println(
//                indexOf(n, m)
//        );


        char[][] nn = {
                {'a','b','c'},
                {'a','b','c'},
                {'a','b','c'},
                {'a','d','c'}};
        char[][] mm = {
                {'b','c'},
                {'d','c'}};

        System.out.println(Arrays.toString(indexOf(nn,mm)));
    }
}
