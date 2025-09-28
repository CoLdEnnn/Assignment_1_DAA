package algorithms.sort;

import algorithms.metrics.MetricsTracker;

public class InsertionSort implements SortAlgorithm {
    @Override
    public void sort(int[] arr, MetricsTracker tracker) {
        insertionSort(arr, 0, arr.length - 1, tracker);
    }

    static void insertionSort(int[] arr, int left, int right, MetricsTracker tracker) {
        for (int i = left + 1; i <= right; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= left) {
                tracker.incComparisons();
                if (arr[j] > key) {
                    arr[j + 1] = arr[j];
                    j--;
                } else break;
            }
            arr[j + 1] = key;
        }
    }

    @Override
    public String name() {
        return "InsertionSort";
    }
}
