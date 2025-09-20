package Algorithms.sort;

import Algorithms.metrics.MetricsTracker;

public class InsertionSort {
    public static void sort(int[] arr, int left, int right, MetricsTracker tracker) {
        for (int i = left + 1; i <= right; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= left) {
                tracker.incComparisons();
                if (arr[j] > key) {
                    arr[j + 1] = arr[j];
                    j--;
                } else {
                    break;
                }
            }
            arr[j + 1] = key;
        }
    }
}
