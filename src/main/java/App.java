package algorithms;

import algorithms.sort.SortSelector;
import algorithms.sort.SortMetrics;

public class App {
    public static void main(String[] args) {
        int[] array = {5, 2, 9, 1, 7, 3};

        SortSelector selector = new SortSelector();
        SortMetrics best = selector.chooseBest(array);

        System.out.println("Best algorithm: " + best);
    }
}
