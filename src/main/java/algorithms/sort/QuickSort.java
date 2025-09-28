package algorithms.sort;

import algorithms.metrics.MetricsTracker;
import algorithms.util.ArrayUtils;
import algorithms.util.PartitionUtils;

import java.util.Random;

public class QuickSort implements SortAlgorithm {
    private static final Random random = new Random();

    @Override
    public void sort(int[] arr, MetricsTracker tracker) {
        quickSort(arr, 0, arr.length - 1, tracker);
    }

    private void quickSort(int[] arr, int left, int right, MetricsTracker tracker) {
        if (left < right) {
            tracker.enterRecursion();
            int pivotIndex = left + random.nextInt(right - left + 1);
            ArrayUtils.swap(arr, pivotIndex, right);
            int pivot = PartitionUtils.partition(arr, left, right, tracker);

            if (pivot - left < right - pivot) {
                quickSort(arr, left, pivot - 1, tracker);
                left = pivot + 1;
            } else {
                quickSort(arr, pivot + 1, right, tracker);
                right = pivot - 1;
            }
            tracker.exitRecursion();
        }
    }

    @Override
    public String name() {
        return "QuickSort";
    }
}
