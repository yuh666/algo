package sort.nlogn;

import java.util.Arrays;

public class Main {

    public static void mergeSort(int[] arr) {
        _mergeSort(arr, 0, arr.length - 1);
    }

    private static void _mergeSort(int[] arr, int l, int r) {
        if (l >= r) {
            return;
        }
        int mid = (l + r) / 2;
        _mergeSort(arr, l, mid);
        _mergeSort(arr, mid + 1, r);
        merge(arr, l, mid, r);
    }

    private static void merge(int[] arr, int l, int mid, int r) {
        int[] temp = new int[r - l + 1];
        System.arraycopy(arr, l, temp, 0, r - l + 1);
        int i = 0, j = mid+1-l, iEnd = mid-l, jEnd = r-l;
        while (i <= iEnd || j <= jEnd) {
            if (i > iEnd) {
                arr[l] = temp[j];
                j++;
            } else if (j > jEnd) {
                arr[l] = temp[i];
                i++;
            } else if (temp[j] < temp[i]) {
                arr[l] = temp[j];
                j++;
            } else {
                arr[l] = temp[i];
                i++;
            }
            l++;
        }
    }

    public static void main(String[] args) {
        int[] arr = {6,5,4,3,2,1};
        mergeSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
