package algorithms.select;

import algorithms.metrics.MetricsTracker;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class DeterministicSelectTest {
    @Test
    void testSmallArray() {
        int[] arr = {7, 2, 9, 4, 1};
        MetricsTracker tracker = new MetricsTracker();
        int k = 2;
        int result = DeterministicSelect.select(arr.clone(), k, tracker);
        int[] sorted = arr.clone();
        Arrays.sort(sorted);
        assertEquals(sorted[k], result);
    }

    @Test
    void testRandomArray() {
        int[] arr = new Random().ints(100, 0, 1000).toArray();
        int k =50;
        MetricsTracker tracker = new MetricsTracker();
        int result = DeterministicSelect.select(arr.clone(), k, tracker);
        int[] sorted = arr.clone();
        Arrays.sort(sorted);
        assertEquals(sorted[k], result);
    }

    @Test
    void testFirstAndLast() {
        int[] arr = {10, 20, 30, 40, 50};
        MetricsTracker tracker = new MetricsTracker();
        assertEquals(10, DeterministicSelect.select(arr.clone(), 0, tracker));
        assertEquals(50, DeterministicSelect.select(arr.clone(), 4, tracker));
    }
}
