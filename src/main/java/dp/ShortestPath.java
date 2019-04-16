package dp;


/**
 * @author yuh
 * @date 2019-04-16 13:38
 **/
public class ShortestPath {

    public static int path(int[][] arr) {
        int[][] state = new int[arr.length][arr[0].length];
        int sum = 0;
        for (int i = 0; i < state[0].length; i++) {
            sum += arr[0][i];
            state[0][i] = sum;
        }
        sum = state[0][0];
        for (int i = 1; i < state.length; i++) {
            sum += arr[i][0];
            state[i][0] = sum;
        }
        for (int i = 1; i < state.length; i++) {
            for (int j = 1; j < state[0].length; j++) {
                state[i][j] = arr[i][j] + Math.min(state[i][j - 1], state[i - 1][j]);
            }
        }
        return state[state.length - 1][state[0].length - 1];
    }


    public static int path1(int[][] arr) {
        return _path1(arr, arr.length - 1, arr[0].length - 1);
    }

    public static int _path1(int[][] arr, int i, int j) {
        if (i == 0 && j == 0) {
            return arr[0][0];
        }
        int left = Integer.MAX_VALUE;
        if (j > 0) {
            left = _path1(arr, i, j - 1);
        }
        int up = Integer.MAX_VALUE;
        if (i > 0) {
            up = _path1(arr, i - 1, j);
        }
        return arr[i][j] + Math.min(left, up);
    }


    public static void main(String[] args) {
        int path = path1(new int[][]{
                {1, 2, 3},
                {1, 2, 3}});
        System.out.println(path);
    }
}
