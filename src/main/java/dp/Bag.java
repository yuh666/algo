package dp;

/**
 * 背包问题用dp解决就是 把回溯的全部可能情况限制在了给定的限制值w内
 *
 * @author yuh
 * @date 2019-04-08 09:04
 **/
public class Bag {

    public static int max(int[] items, int w) {
        boolean[][] state = new boolean[items.length][w + 1];
        state[0][0] = true;
        state[0][items[0]] = true;
        for (int i = 1; i < items.length; i++) {
            //将上面一个item处理完的情况copy下来
            for (int j = 0; j <= w; j++) {
                if (state[i - 1][j]) {
                    state[i][j] = true;
                }
            }
            //将第i个item添加到不超过w的地方
            for (int j = 0; j <= w - items[i]; j++) {
                if (state[i - 1][j]) {
                    //此时的j为满足的情况 即j+item[i]<=w
                    state[i][j + items[i]] = true;
                }
            }
        }
        for (int i = w; i >= 0; i--) {
            if (state[items.length - 1][i]) {
                return i;
            }
        }
        return -1;
    }

    public static int max1(int[] items, int w) {
        boolean[] state = new boolean[w + 1];
        state[0] = true;
        state[items[0]] = true;
        for (int i = 1; i < items.length; i++) {
            //将第i个item添加到不超过w的地方
            for (int j = w - items[i]; j >= 0; j--) {
                if (state[j]) {
                    //此时的j为满足的情况 即j+item[i]<=w
                    state[j + items[i]] = true;
                }
            }
        }
        for (int i = w; i >= 0; i--) {
            if (state[i]) {
                return i;
            }
        }
        return -1;
    }


    public static void main(String[] args) {
        int[] arr = {3,4,8};
        System.out.println(max1(arr,7));
    }

}
