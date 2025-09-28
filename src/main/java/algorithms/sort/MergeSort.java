package algorithms.sort;

import algorithms.metrics.MetricsTracker;

public class MergeSort implements SortAlgorithm {
    private static final int CUTOFF = 16;

    @Override
    public void sort(int[] arr, MetricsTracker tracker) {
        int[] buffer = new int[arr.length];
        sort(arr, buffer, 0, arr.length - 1, tracker);
    }

    private void sort(int[] arr, int[] buffer, int left, int right, MetricsTracker tracker) {
        if (right - left <= CUTOFF) {
            InsertionSort.insertionSort(arr, left, right, tracker);
            return;
        }
        tracker.enterRecursion();
        int mid = (left + right) >>> 1;
        sort(arr, buffer, left, mid, tracker);
        sort(arr, buffer, mid + 1, right, tracker);
        merge(arr, buffer, left, mid, right, tracker);
        tracker.exitRecursion();
    }

    private void merge(int[] arr, int[] buffer, int left, int mid, int right, MetricsTracker tracker) {
        int i = left, j = mid + 1, k = left;
        while (i <= mid && j <= right) {
            tracker.incComparisons();
            if (arr[i] <= arr[j]) buffer[k++] = arr[i++];
            else buffer[k++] = arr[j++];
        }
        while (i <= mid) buffer[k++] = arr[i++];
        while (j <= right) buffer[k++] = arr[j++];
        for (int idx = left; idx <= right; idx++) arr[idx] = buffer[idx];
    }

    @Override
    public String name() {
        return "MergeSort";
    }
}
