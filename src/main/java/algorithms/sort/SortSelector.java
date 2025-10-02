package algorithms.sort;

import algorithms.metrics.MetricsTracker;

import java.util.ArrayList;
import java.util.List;

public class SortSelector {
    private final List<SortAlgorithm> algorithms = new ArrayList<>();

    public SortSelector() {
        algorithms.add(new MergeSort());
        algorithms.add(new QuickSort());
        algorithms.add(new InsertionSort()); // для маленьких массивов
    }

    public SortMetrics chooseBest(int[] array) {
        SortMetrics best = null;

        for (SortAlgorithm algo : algorithms) {
            int[] copy = array.clone();
            MetricsTracker tracker = new MetricsTracker();

            long start = System.nanoTime();
            algo.sort(copy, tracker);
            long end = System.nanoTime();

            SortMetrics metrics = new SortMetrics(end - start, algo.name());

            if (best == null || metrics.getTimeNanos() < best.getTimeNanos()) {
                best = metrics;
            }
        }

        return best;
    }

}
