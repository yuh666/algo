package str;

/**
 * @author yuh
 * @date 2019-03-14 09:49
 **/
public class BF {


    public static int indexOf(String mainStr, String pStr) {
        int n = mainStr.length();
        int m = pStr.length();

        for (int i = 0; i <= n - m; i++) {
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

    public static void main(String[] args) {
        String n = "abcd";
        String m = "bc";

        System.out.println(
                indexOf(n,m)
        );
    }
}
