package Algorithms.sort;

import Algorithms.metrics.MetricsTracker;
import Algorithms.util.ArrayUtils;
import Algorithms.util.PartitionUtils;

import java.util.Random;

public class QuickSort {
    private static final Random random = new Random();

    public static void sort(int[] arr, MetricsTracker tracker) {
        quickSort(arr, 0, arr.length - 1, tracker);
    }

    private static void quickSort(int[] arr, int left, int right, MetricsTracker tracker) {
        if (left < right) {
            tracker.enterRecursion();

            int pivotIndex = left + random.nextInt(right - left + 1);
            ArrayUtils.swap(arr, pivotIndex, right);

            int pivot = PartitionUtils.partition(arr, left, right ,tracker);

            if(pivot - left< right - pivot) {
                quickSort(arr, left, pivot - 1, tracker);
                left = pivot + 1;
            } else {
                quickSort(arr, pivot + 1, right, tracker);
                right = pivot - 1;
            }
            tracker.exitRecursion();
        }
    }
}
