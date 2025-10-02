package algorithms.sort;

import algorithms.metrics.MetricsTracker;
import algorithms.util.ArrayUtils;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class QuickSortTest {

    @Test
    public void testSortedArray() {
        int[] arr = {1,2,3,4,5};
        MetricsTracker tracker = new MetricsTracker();
        new QuickSort().sort(arr, tracker);
        assertTrue(ArrayUtils.isSorted(arr));
    }

    @Test
    void testRandomArray() {
        int[] arr = new Random().ints(200, -500, 500).toArray();
        MetricsTracker tracker = new MetricsTracker();
        new QuickSort().sort(arr, tracker);
        assertTrue(ArrayUtils.isSorted(arr));
    }

    @Test
    void testDuplicates() {
        int[] arr = {5, 5, 5, 5, 5};
        MetricsTracker tracker = new MetricsTracker();
        new QuickSort().sort(arr, tracker);
        assertTrue(ArrayUtils.isSorted(arr));
    }

    @Test void testEmptyArrayQuickSort() {
        int[] arr = {};
        new QuickSort().sort(arr, new MetricsTracker());
        assertTrue(ArrayUtils.isSorted(arr));
    }
}
