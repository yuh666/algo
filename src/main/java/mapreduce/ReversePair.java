package mapreduce;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author yuh
 * @date 2019-04-02 09:25
 **/
public class ReversePair {

    public static int countReversePair(int[] arr) {
        AtomicInteger cnt = new AtomicInteger();
        _countReversePair(arr, 0, arr.length - 1, cnt);
        return cnt.intValue();
    }

    private static void _countReversePair(int[] arr, int l, int r, AtomicInteger cnt) {
        if (l >= r) {
            return;
        }
        int mid = (l + r) >> 1;
        _countReversePair(arr, l, mid, cnt);
        _countReversePair(arr, mid + 1, r, cnt);
        merge(arr, l, mid, r, cnt);
    }

    private static void merge(int[] arr, int l, int mid, int r, AtomicInteger cnt) {
        int[] aux = new int[r - l + 1];
        System.arraycopy(arr, l, aux, 0, aux.length);
        int k = l, i = 0, p = mid - l, j = p + 1, e = aux.length - 1;
        while (i <= p || j <= e) {
            if (i > p) {
                arr[k] = aux[j];
                j++;
            } else if (j > e) {
                arr[k] = aux[i];
                i++;
            } else if (aux[i] <= aux[j]) {
                arr[k] = aux[i];
                i++;
            } else {
                arr[k] = aux[j];
                j++;
                cnt.addAndGet(p - i + 1);
            }
            k++;
        }
    }


    public static void main(String[] args) {
        //分治求逆序对 就是后面的和前面的比就行了
        int[] arr = {4, 3, 2, 1};
        int i = countReversePair(arr);
        System.out.println(i);
        System.out.println(Arrays.toString(arr));
    }
}
