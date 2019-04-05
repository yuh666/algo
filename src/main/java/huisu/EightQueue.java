package huisu;

import java.util.Arrays;

/**
 *
 * 回溯算法就是选择一条路走下去 走不下去就回到路口选择其他路走下去 能走下去就取消其他路口的选择
 */
public class EightQueue {

    private static int[] result = new int[8];
    private static boolean end = false;

    static {
        Arrays.fill(result, -1);
    }

    private static void calRow(int row) {
        if (row == 8) {
            end = true;
            printQueue();
            return;
        }
        for (int i = 0; i < 8; i++) {
            if (end) {
                break;
            }
            if (isOK(row, i)) {
                result[row] = i;
                calRow(row + 1);
            }
        }
    }

    private static boolean isOK(int row, int col) {
        int leftUp = col - 1, rightUp = col + 1;
        for (int i = row - 1; i >= 0; i--) {
            if (result[i] == col) {
                return false;
            }
            if (leftUp >= 0 && result[i] == leftUp) {
                return false;
            }
            if (rightUp < 8 && result[i] == rightUp) {
                return false;
            }
            leftUp--;
            rightUp++;
        }
        return true;
    }

    private static void printQueue() {
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result.length; j++) {
                if (j == result[i]) {
                    System.out.print("  Q  ");
                } else {
                    System.out.print("  □  ");
                }
            }
            System.out.println();
        }
    }


    public static boolean check(){
        for (int i = 0; i < result.length; i++) {
            if(!isOK(i,result[i])){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        calRow(0);
        System.out.println(check());
    }
}
