package algorithms.cli;

import algorithms.geometry.ClosestPair;
import algorithms.geometry.Point;
import algorithms.metrics.CsvWriter;
import algorithms.metrics.Metrics;
import algorithms.metrics.MetricsTracker;
import algorithms.select.DeterministicSelect;
import algorithms.sort.MergeSort;
import algorithms.sort.QuickSort;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainCli {
    public static void main(String[] args) throws IOException {
        if (args.length < 2) {
            System.err.println("Usage: java -jar app.jar <algorithm> <n>");
            System.out.println("Algorithms: mergesort, quicksort, select, closest, all");
            return;
        }
        String algorithm = args[0].toLowerCase();
        int n = Integer.parseInt(args[1]);

        MetricsTracker tracker = new MetricsTracker();
        Random random = new Random();

        List<String[]> results = new ArrayList<>();
        results.add(new String[]{"algorithm", "n", "time(ms)", "comparisons", "allocations", "maxDepth"});

        switch (algorithm) {
            case "mergesort" -> {
                int[] arr = random.ints(n, 0, 1_000_000).toArray();
                tracker.reset();
                long start = System.nanoTime();
                new MergeSort().sort(arr, tracker);   // ✅ через объект
                long elapsed = (System.nanoTime() - start) / 1_000_000;
                Metrics m = tracker.snapshot();
                results.add(new String[]{"mergesort", String.valueOf(n), String.valueOf(elapsed),
                        String.valueOf(m.getComparisons()), String.valueOf(m.getAllocations()), String.valueOf(m.getMaxDepth())});
            }
            case "quicksort" -> {
                int[] arr = random.ints(n, 0, 1_000_000).toArray();
                tracker.reset();
                long start = System.nanoTime();
                new QuickSort().sort(arr, tracker);   // ✅ через объект
                long elapsed = (System.nanoTime() - start) / 1_000_000;
                Metrics m = tracker.snapshot();
                results.add(new String[]{"quicksort", String.valueOf(n), String.valueOf(elapsed),
                        String.valueOf(m.getComparisons()), String.valueOf(m.getAllocations()), String.valueOf(m.getMaxDepth())});
            }
            case "select" -> {
                int[] arr = random.ints(n, 0, 1_000_000).toArray();
                int k = n / 2;
                tracker.reset();
                long start = System.nanoTime();
                int result = DeterministicSelect.select(arr, k, tracker);
                long elapsed = (System.nanoTime() - start) / 1_000_000;
                Metrics m = tracker.snapshot();
                results.add(new String[]{"select(k=" + k + ")", String.valueOf(n), String.valueOf(elapsed),
                        String.valueOf(m.getComparisons()), String.valueOf(m.getAllocations()), String.valueOf(m.getMaxDepth())});
                System.out.println("Selected element: " + result);
            }
            case "closest" -> {
                Point[] points = new Point[n];
                for (int i = 0; i < n; i++) {
                    points[i] = new Point(random.nextDouble() * 1000, random.nextDouble() * 1000);
                }
                tracker.reset();
                long start = System.nanoTime();
                double dist = ClosestPair.findClosestPair(points, tracker);
                long elapsed = (System.nanoTime() - start) / 1_000_000;
                Metrics m = tracker.snapshot();
                results.add(new String[]{"closest", String.valueOf(n), String.valueOf(elapsed),
                        String.valueOf(m.getComparisons()), String.valueOf(m.getAllocations()), String.valueOf(m.getMaxDepth())});
                System.out.println("Closest distance: " + dist);
            }
            case "all" -> {
                for (String a : new String[]{"mergesort", "quicksort", "select", "closest"}) {
                    main(new String[]{a, String.valueOf(n)});
                }
                return;
            }
            default -> {
                System.out.println("Unknown algorithm: " + algorithm);
                return;
            }
        }

        CsvWriter writer = new CsvWriter("metrics_" + algorithm + "_" + n + ".csv");
        writer.write(results);
        System.out.println("Results written to CSV.");
    }
}
