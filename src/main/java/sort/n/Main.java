package sort.n;

import java.util.Arrays;

public class Main {

    public static void countSort(int[] arr) {
        //find max
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (max < arr[i]) {
                max = arr[i];
            }
        }
        int[] c = new int[max + 1];
        //count
        for (int i : arr) {
            c[i]++;
        }
        //count with pre
        for (int i = 1; i < c.length; i++) {
            c[i] += c[i - 1];
        }
        //new arr
        int[] r = new int[arr.length];
        for (int i = arr.length - 1; i >= 0; i--) {
            int index = c[arr[i]] - 1;
            r[index] = arr[i];
            c[arr[i]]--;
        }
        //copy
        System.arraycopy(r,0,arr,0,arr.length);
    }

    public static void main(String[] args) {
        int[] arr = {5,5,5,5,5,5,5,5,5,5,5,5,5,5,3,3,3,3,3,3,4,4,4,4,4,4,4};
        System.out.println(arr.length);
        countSort(arr);
        System.out.println(arr.length);
        System.out.println(Arrays.toString(arr));
    }
}
