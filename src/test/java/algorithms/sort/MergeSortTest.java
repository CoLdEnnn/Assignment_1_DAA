package algorithms.sort;

import algorithms.metrics.MetricsTracker;
import algorithms.util.ArrayUtils;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;

class MergeSortTest {
    @Test
    void testSortedArray() {
        int[] arr = {1,2,3,4,5};
        MetricsTracker tracker = new MetricsTracker();
        new MergeSort().sort(arr, tracker); // ✅ через объект
        assertTrue(ArrayUtils.isSorted(arr));
    }

    @Test
    void testRandomArray() {
        int[] arr = new Random().ints(100, 0, 1000).toArray();
        MetricsTracker tracker = new MetricsTracker();
        new MergeSort().sort(arr, tracker); // ✅
        assertTrue(ArrayUtils.isSorted(arr));
    }

    @Test
    void testCutoffInsertionSort() {
        int[] arr = {5, 4, 3, 2, 1};
        MetricsTracker tracker = new MetricsTracker();
        new MergeSort().sort(arr, tracker); // ✅
        assertTrue(ArrayUtils.isSorted(arr));
    }

}
