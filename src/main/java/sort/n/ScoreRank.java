package sort.n;

import java.util.Arrays;

public class ScoreRank {

    private int[] c = new int[6];
    public ScoreRank(int[] arr){
        int[] count = new int[c.length];
        for (int i : arr) {
            count[i]++;
        }
        c[c.length-1] = 0;
        for (int i = c.length-2; i >= 0; i--) {
            c[i] = c[i+1] + count[i+1];
        }
        System.out.println(Arrays.toString(c));
    }

    public int getRank(int score){
        return c[score] + 1;
    }

    public static void main(String[] args) {
        ScoreRank scoreRank = new ScoreRank(new int[]{1, 2, 3, 4, 5, 1, 2, 4, 4, 5});
        System.out.println(scoreRank.getRank(3));
    }
}
