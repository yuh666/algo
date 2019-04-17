package dp;

/**
 * @author yuh
 * @date 2019-04-17 14:14
 **/
public class LCS {


    public static int lcs(String str1, String str2) {
        int n = str1.length();
        int m = str2.length();

        int[][] state = new int[n][m];
        char c = str1.charAt(0);
        for (int i = 0; i < m; i++) {
            if (c == str2.charAt(i)) {
                state[0][i] = 1;
            } else if (i != 0) {
                state[0][i] = state[0][i-1];
            } else {
                state[0][i] = 0;
            }
        }

        char c1 = str2.charAt(0);
        for (int i = 0; i < n; i++) {
            if (c1 == str1.charAt(i)) {
                state[i][0] = 1;
            } else if (i != 0) {
                state[i][0] = state[i-1][0];
            } else {
                state[i][0] = 0;
            }
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                char c11 = str1.charAt(i);
                char c22 = str2.charAt(j);
                if (c11 == c22) {
                    state[i][j] = max(state[i - 1][j], state[i][j - 1] , state[i - 1][j - 1]+1);
                } else {
                    state[i][j] = max(state[i - 1][j], state[i][j - 1], state[i - 1][j - 1]);
                }
            }
        }
        return state[n - 1][m - 1];
    }

    public static int max(int a, int b, int c) {
        if (a > b) {
            return a > c ? a : c;
        }
        return b > c ? b : c;
    }

    public static void main(String[] args) {
        System.out.println(lcs("abcc","accc"));
    }

}
