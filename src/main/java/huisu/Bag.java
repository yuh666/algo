package huisu;

public class Bag {

    private static int cw = Integer.MIN_VALUE;

    public static void max(int w, int cws, int[] items, int c) {
        if (c == items.length || cws == w) {
            if (cws > cw) {
                cw = cws;
            }
            return;
        }
        //不放入
        max(w, cws, items, c + 1);
        if (cws + items[c] <= w) {
            max(w, cws + items[c], items, c + 1);
        }
    }

    public static void main(String[] args) {
        int[] arr = {3,5,9};
        max(10,0,arr,0);
        System.out.println(cw);
    }
}
