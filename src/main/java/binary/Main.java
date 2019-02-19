package binary;

public class Main {

    /**
     * 直接相等
     *
     * @param arr
     * @param k
     * @return
     */
    public static int binarySearch(int[] arr, int k) {
        int low = 0, high = arr.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] > k) {
                high = mid - 1;
            } else if (arr[mid] < k) {
                low = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    /**
     * 第一个相等的
     *
     * @param arr
     * @param k
     * @return
     */
    public static int binarySearchEqFirst(int[] arr, int k) {
        int low = 0, high = arr.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] > k) {
                high = mid - 1;
            } else if (arr[mid] < k) {
                low = mid + 1;
            } else {
                if (mid == 0 || arr[mid - 1] != k) {
                    return mid;
                }
                high = mid - 1;
            }
        }
        return -1;
    }

    /**
     * 第一个相等的
     *
     * @param arr
     * @param k
     * @return
     */
    public static int binarySearchEqLast(int[] arr, int k) {
        int low = 0, high = arr.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] > k) {
                high = mid - 1;
            } else if (arr[mid] < k) {
                low = mid + 1;
            } else {
                if (mid == arr.length - 1 || arr[mid + 1] != k) {
                    return mid;
                }
                low = mid + 1;
            }
        }
        return -1;
    }

    /**
     * 第一个大于等于的
     *
     * @param arr
     * @param k
     * @return
     */
    public static int binarySearchGteFirst(int[] arr, int k) {
        int low = 0, high = arr.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] < k) {
                low = mid + 1;
            } else {
                if (mid == 0 || arr[mid - 1] < k) {
                    return mid;
                }
                high = mid - 1;
            }
        }
        return -1;
    }

    /**
     * 最后一个小于等于的
     *
     * @param arr
     * @param k
     * @return
     */
    public static int binarySearchLteLast(int[] arr, int k) {
        int low = 0, high = arr.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] > k) {
                high = mid - 1;
            } else {
                if (mid == arr.length - 1 || arr[mid + 1] > k) {
                    return mid;
                }
                low = mid + 1;
            }
        }
        return -1;
    }

    public static double sqrt(double num) {
        double low, high;
        if (num < 1) {
            low = 0;
            high = 1;
        } else {
            low = 1;
            high = num;
        }
        while (low <= high) {
            double mid = low + (high - low) / 2;
            if (Math.abs(mid * mid - num) < 0.00001) {
                return ((int) mid * 100000) / 100000D;
            } else if (mid * mid < num) {
                low = mid;
            } else {
                high = mid;
            }
        }
        return -1D;
    }


    public static void main(String[] args) {
//        int[] arr = {1, 2, 2, 3, 4, 5, 8, 10};
//        System.out.println(binarySearchLteLast(arr, 2));
        System.out.println(sqrt(4));
    }


}
