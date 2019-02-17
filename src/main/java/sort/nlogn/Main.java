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
        int i = 0, j = mid + 1 - l, iEnd = mid - l, jEnd = r - l;
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

    public static void quickSort(int[] arr) {
        _quickSort(arr, 0, arr.length - 1);
    }

    private static void _quickSort(int[] arr, int l, int r) {
        if (l >= r) {
            return;
        }
        int index = partitionRev(arr, l, r);
        _quickSort(arr, l, index - 1);
        _quickSort(arr, index + 1, r);
    }

    private static int partition(int[] arr, int l, int r) {
        int pivot = arr[l];
        int i = l + 1, j = r;
        while (true) {
            while (i <= r && arr[i] <= pivot) {
                i++;
            }
            while (j > l && arr[j] >= pivot) {
                j--;
            }
            if (j < i) {
                //所有数据都走完了
                break;
            }
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            i++;
            j--;
        }
        arr[l] = arr[j];
        arr[j] = pivot;
        return j;
    }


    private static int partitionRev(int[] arr, int l, int r) {
        int pivot = arr[l];
        int i = l + 1, j = r;
        while (true) {
            while (i <= r && arr[i] >= pivot) {
                i++;
            }
            while (j > l && arr[j] <= pivot) {
                j--;
            }
            if (j < i) {
                //所有数据都走完了
                break;
            }
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            i++;
            j--;
        }
        arr[l] = arr[j];
        arr[j] = pivot;
        return j;
    }

    public static int findN(int[] arr, int k) {
        if (arr.length < k) {
            return -1;
        }
        int l = 0, r = arr.length - 1;
        while (true) {
            int index = partitionRev(arr, l, r);
            int temp = index + 1;
            if (temp == k) {
                return arr[index];
            } else if (temp < k) {
                l = index + 1;
            } else {
                l = index - 1;
            }
        }
    }


    public static void main(String[] args) {
        int[] arr = {6, 5, 4, 3, 2, 1};
        quickSort(arr);
        System.out.println(findN(arr,1));
    }
}
