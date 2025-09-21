package Algorithms.sort;

import Algorithms.metrics.MetricsTracker;

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
            swap(arr, pivotIndex, right);

            int pivot = partition(arr, left, right ,tracker);

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

    private static int partition(int[] arr, int left, int right, MetricsTracker tracker) {
        int pivotValue = arr[right];
        int storeIndex = left;

        for (int i = left; i < right; i++) {
            tracker.incComparisons();
            if (arr[i] < pivotValue) {
                swap(arr, i, storeIndex);
                storeIndex++;
            }
        }
        swap(arr, storeIndex, right);
        return storeIndex;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
