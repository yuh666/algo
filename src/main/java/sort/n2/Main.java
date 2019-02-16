package sort.n2;

import java.util.Arrays;

public class Main {

    public static void selectSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int min = arr[i];
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[i]) {
                    min = j;
                }
            }
            int temp = arr[min];
            arr[min] = arr[i];
            arr[i] = temp;
        }
    }

    public static void bubbleSort(int[] arr) {
        //总的比较次数
        for (int i = 0; i < arr.length - 1; i++) {
            boolean end = true;
            //到l-1 否则j+1会越界 -i是因为每次会确定一个最大值
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    end = false;
                }
                if (end) {
                    break;
                }
            }
        }
    }

    public static void insertSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int i1 = arr[i];
            int j = i;
            while (j > 0 && i1 < arr[j - 1]) {
                //往后移动
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = i1;
        }
    }


    public static void main(String[] args) {
        int[] arr = {6, 5, 4, 3, 2, 1};
        insertSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
