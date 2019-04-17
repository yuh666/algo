package dp;

/**
 * @author yuh
 * @date 2019-04-17 14:14
 **/
public class EditDistance {


    public static int distance(String str1, String str2) {
        int n = str1.length();
        int m = str2.length();

        int[][] state = new int[n][m];
        char c = str1.charAt(0);
        for (int i = 0; i < m; i++) {
            if (c == str2.charAt(i)) {
                state[0][i] = i;
            } else if (i != 0) {
                state[0][i] = state[0][i-1] + 1;
            } else {
                state[0][i] = 1;
            }
        }

        char c1 = str2.charAt(0);
        for (int i = 0; i < n; i++) {
            if (c1 == str1.charAt(i)) {
                state[i][0] = i;
            } else if (i != 0) {
                state[i][0] = state[i-1][0] + 1;
            } else {
                state[i][0] = 1;
            }
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                char c11 = str1.charAt(i);
                char c22 = str2.charAt(j);
                if (c11 == c22) {
                    state[i][j] = min(state[i - 1][j] + 1, state[i][j - 1] + 1, state[i - 1][j - 1]);
                } else {
                    state[i][j] = min(state[i - 1][j] + 1, state[i][j - 1] + 1, state[i - 1][j - 1] + 1);
                }
            }
        }
        return state[n - 1][m - 1];
    }

    public static int min(int a, int b, int c) {
        if (a > b) {
            return b > c ? c : b;
        }
        return a > c ? c : a;
    }

    public static void main(String[] args) {
        System.out.println(distance("abc","acc"));
    }

}
