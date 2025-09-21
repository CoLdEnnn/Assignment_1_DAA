package Algorithms.select;

import Algorithms.metrics.MetricsTracker;
import Algorithms.util.ArrayUtils;
import Algorithms.util.PartitionUtils;

import java.util.Arrays;

public class DeterministicSelect {
    public static int select(int[] arr, int k, MetricsTracker tracker) {
        if (k < 0 || k >= arr.length) {
            throw new IllegalArgumentException(k + " out of range");
        }
        return select(arr, 0, arr.length - 1, k, tracker);
    }

    private static int select(int[] arr, int left, int right, int k, MetricsTracker tracker) {
        while (true) {
            if (left == right) {
                return arr[left];
            }
            tracker.enterRecursion();

            int pivotIndex = medianOfMedians(arr, left, right, tracker);
            ArrayUtils.swap(arr, pivotIndex, right);
            int pivotFinal = PartitionUtils.partition(arr, left, right, tracker);

            if (k == pivotFinal) {
                tracker.exitRecursion();
                return arr[k];
            } else if (k < pivotFinal) {
                right = pivotFinal - 1;
            } else {
                left = pivotFinal + 1;
            }

            tracker.exitRecursion();
        }
    }

    private static int medianOfMedians(int[] arr, int left, int right, MetricsTracker tracker) {
        int n = right - left + 1;
        if (n < 5) {
            Arrays.sort(arr, left, right + 1);
            return left + n/2;
        }

        int numGroups = (int) Math.ceil((double) n/5);
        for (int i =0; i<numGroups; i++) {
            int groupLeft = left + i * 5;
            int groupRight = Math.min(groupLeft + 4, right);
            Arrays.sort(arr, groupLeft, groupRight + 1);
            int medianIndex = groupLeft + (groupRight - groupLeft) / 2;
            ArrayUtils.swap(arr, left + i, medianIndex);
        }
        return medianOfMedians(arr, left, left + numGroups - 1, tracker);
    }
}
