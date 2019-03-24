package str;

public class KMPReview {


    private static int[] generateNext(String pStr) {
        int[] next = new int[pStr.length()];
        next[0] = -1;
        int k = -1;
        for (int i = 1; i < pStr.length(); i++) {
            while (k != -1 && pStr.charAt(i) != pStr.charAt(k + 1)) {
                k = next[k];
            }
            if (pStr.charAt(i) != pStr.charAt(k + 1)) {
                k++;
            }
            next[i] = k;
        }
        return next;
    }


    public static int indexOf(String mainStr, String pStr) {
        int j = 0;
        int[] next = generateNext(pStr);
        for (int i = 0; i < mainStr.length(); i++) {
            while (j >= 0 && pStr.charAt(j) != mainStr.charAt(i)) {
                j = next[j - 1] + 1;
            }
            if (pStr.charAt(j) == mainStr.charAt(i)) {
                j++;
            }
            if (j == pStr.length()) {
                return i - pStr.length() + 1;
            }
        }
        return -1;
    }


}
