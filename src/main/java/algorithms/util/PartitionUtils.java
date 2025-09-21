package algorithms.util;

import algorithms.metrics.MetricsTracker;

public class PartitionUtils {
    public static int partition(int[] arr, int left, int right, MetricsTracker tracker){
        int pivotValue = arr[right];
        int storeIndex = left;

        for (int i = left; i < right; i++) {
            tracker.incComparisons();
            if (arr[i] < pivotValue) {
                ArrayUtils.swap(arr, storeIndex, i);
                storeIndex++;
            }
        }
        ArrayUtils.swap(arr, storeIndex, right);
        return storeIndex;
    }
}
