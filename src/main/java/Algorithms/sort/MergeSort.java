package Algorithms.sort;

import Algorithms.metrics.MetricsTracker;

public class MergeSort {
    private static final int CUTOFF = 16;

    public static void sort(int[] arr, MetricsTracker tracker) {
        int[] buffer = new int[arr.length];
        sort(arr, buffer, 0, arr.length - 1, tracker, 0);
    }

    private static void sort(int[] arr, int[] buffer, int left, int right, MetricsTracker tracker, int depth) {
        if (right - left <= CUTOFF) {
            InsertionSort.sort(arr, left, right, tracker);
            return;
        }

        tracker.enterRecursion();
        int mid = (left + right) >>> 1;
        sort(arr, buffer, left, mid, tracker, depth + 1);
        sort(arr, buffer, mid + 1, right, tracker, depth + 1);

        merge(arr, buffer, left, mid, right, tracker);
        tracker.exitRecursion();
    }

    private static void merge(int[] arr, int[] buffer, int left, int mid, int right, MetricsTracker tracker) {
        int i = left;
        int j = mid + 1;
        int k = left;

        while (i <= mid && j <= right) {
            tracker.incComparisons();
            if (arr[i] <= arr[j]) {
                buffer[k++] = arr[i++];
            } else {
                buffer[k++] = arr[j++];
            }
        }

        while (i <= mid) {
            buffer[k++] = arr[i++];
        }
        while (j <= right) {
            buffer[k++] = arr[j++];
        }

        for (int idx = left; idx <= right; idx++) {
            arr[idx] = buffer[idx];
        }
    }
}
