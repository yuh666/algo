package sort.nlogn;

import java.util.*;

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

    static class Node implements Comparable<Node> {
        int val;
        int listIndex;
        int index;

        public Node(int val, int listIndex, int index) {
            this.val = val;
            this.listIndex = listIndex;
            this.index = index;
        }

        public int compareTo(Node o) {
            return this.val - o.val;
        }
    }

    public static List<Integer> mergeList(List<Integer>... lists) {
        if (lists == null || lists.length == 0) {
            return new ArrayList<Integer>(0);
        }
        PriorityQueue<Node> queue = new PriorityQueue<Node>(lists.length);
        List<List<Integer>> listList = new ArrayList<List<Integer>>();
        for (List<Integer> list : lists) {
            if (list == null || list.isEmpty()) {
                continue;
            }
            listList.add(list);
        }
        for (int i = 0; i < listList.size(); i++) {
            queue.add(new Node(listList.get(i).get(0), i, 0));
        }
        List<Integer> list = new ArrayList<Integer>();
        while (!queue.isEmpty()) {
            Node poll = queue.poll();
            list.add(poll.val);
            List<Integer> list1 = listList.get(poll.listIndex);
            if (list1.size() > poll.index + 1) {
                queue.add(new Node(list1.get(poll.index + 1), poll.listIndex, poll.index + 1));
            }
        }
        return list;
    }


    public static void heapSort(int[] arr) {
        //构建一个大顶堆
        int length = arr.length;
        for (int i = length / 2; i >= 0; i--) {
            shiftDown(arr, i, length);
        }
        //逐个取出放到最后
        for (int i = arr.length - 1; i > 0; i--) {
            swap(arr, i, 0);
            shiftDown(arr, 0, i);
        }
    }

    private static void shiftDown(int[] arr, int index, int limit) {
        while (2 * index + 1 < limit) {
            int k = 2 * index + 1;
            if (k + 1 < limit && arr[k] < arr[k + 1]) {
                k++;
            }
            if (arr[k] <= arr[index]) {
                break;
            }
            swap(arr, index, k);
            index = k;
        }
    }

    private static void swap(int[] arr, int i1, int i2) {
        int temp = arr[i1];
        arr[i1] = arr[i2];
        arr[i2] = temp;
    }


    public static <T> void sort(List<T> list ,Comparator<? super T> c) {
        Object[] a = list.toArray();
        mergeSort(a, (Comparator) c);
        ListIterator<T> i = list.listIterator();
        for (Object e : a) {
            i.next();
            i.set((T) e);
        }
    }

    private static <T> void mergeSort(T[] a, Comparator<? super T> c) {
        T[] aux = a.clone();
        mergeSort(aux, a, 0, a.length, 0, c);
    }

    private static void mergeSort(Object[] src,
                                  Object[] dest,
                                  int low, int high, int off,
                                  Comparator c) {
        int length = high - low;

        // Insertion sort on smallest arrays
        if (length < 7) {
            for (int i = low; i < high; i++) {
                for (int j = i; j > low && c.compare(dest[j - 1], dest[j]) > 0; j--) {
                    swap(dest, j, j - 1);
                }
            }
            return;
        }

        // Recursively sort halves of dest into src
        int destLow = low;
        int destHigh = high;
        low += off;
        high += off;
        int mid = (low + high) >>> 1;
        mergeSort(dest, src, low, mid, -off, c);
        mergeSort(dest, src, mid, high, -off, c);

        // If list is already sorted, just copy from src to dest.  This is an
        // optimization that results in faster sorts for nearly ordered lists.
        if (c.compare(src[mid - 1], src[mid]) <= 0) {
            System.arraycopy(src, low, dest, destLow, length);
            return;
        }

        // Merge sorted halves (now in src) into dest
        for (int i = destLow, p = low, q = mid; i < destHigh; i++) {
            if (q >= high || p < mid && c.compare(src[p], src[q]) <= 0) {
                dest[i] = src[p++];
            } else {
                dest[i] = src[q++];
            }
        }
    }

    private static void swap(Object[] x, int a, int b) {
        Object t = x[a];
        x[a] = x[b];
        x[b] = t;
    }


    public static void main(String[] args) {
        int[] arr = {6, 5, 6, 3, 2, 1};
        System.out.println(Arrays.toString(arr));
//        System.out.println(findN(arr, 1));

//        ArrayList<Integer> list1 = new ArrayList<Integer>();
//        ArrayList<Integer> list2 = new ArrayList<Integer>();
//        ArrayList<Integer> list3 = new ArrayList<Integer>();
//        for (int i = 0; i < 3; i++) {
//            list1.add(i);
//            list2.add(i);
//            list3.add(i);
//        }
//        System.out.println(mergeList(list1, list2, list3));

        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i%3);
        }
        System.out.println(list);
        sort(list, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1-o2;
            }
        });
        System.out.println(list);
    }
}
