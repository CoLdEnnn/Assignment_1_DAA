package algorithms.sort;

import algorithms.metrics.MetricsTracker;

public interface SortAlgorithm {
    void sort(int[] arr, MetricsTracker tracker);
    String name(); // удобно, чтобы выводить какой алгоритм
}
