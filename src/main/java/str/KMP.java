package str;

/**
 *
 *
 * BF RK KMP    正向匹配    固定i索引 向后移动i索引 j每次0
 * BM           反向匹配    固定i索引 向后移动i索引 j每次length-1
 * KMP          不回退i索引 调整j索引
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */
public class KMP {


    public static int indexOf(String mainStr, String pStr) {
        int ml = mainStr.length();
        int pl = pStr.length();
        int[] next = generateNext(pStr);
        int j = 0;
        for (int i = 0; i < ml; i++) {
            while (j > 0 && pStr.charAt(j) != mainStr.charAt(i)) {
                j = next[j - 1] + 1;
            }
            if (pStr.charAt(j) == mainStr.charAt(i)) {
                j++;
            }
            if (j == pl) {
                return i - pl + 1;
            }
        }
        return -1;
    }

    private static int[] generateNext(String pStr) {
        int[] arr = new int[pStr.length()];
        arr[0] = -1;
        int k = -1;
        for (int i = 1; i < pStr.length(); i++) {
            while (k != -1 && pStr.charAt(k + 1) != pStr.charAt(i)) {
                k = arr[k];
            }
            if (pStr.charAt(k + 1) == pStr.charAt(i)) {
                k++;
            }
            if (k != -1) {
                arr[i] = k;
            }
        }
        return arr;
    }

    public static void main(String[] args) {
        String mainStr = "abababababababababc";
        String pStr = "abc";
        System.out.println(indexOf(mainStr, pStr));
    }
}
