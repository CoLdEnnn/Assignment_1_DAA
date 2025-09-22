package benchmarks;


import algorithms.metrics.MetricsTracker;
import algorithms.select.DeterministicSelect;
import org.openjdk.jmh.annotations.*;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Thread)
public class SelectBenchmark {
    @Param({"100", "1000", "10000"})
    private int n;

    private int[] data;
    private int k;
    private Random random;

    @Setup(Level.Iteration)
    public void setup() {
        random = new Random();
        data = random.ints(n, 0, 1_000_000).toArray();
        k = n / 2;
    }

    @Benchmark
    public int testDeterministicSelect() {
        int[] copy = data.clone();
        MetricsTracker tracker = new MetricsTracker();
        return DeterministicSelect.select(copy, k, tracker);
    }

    @Benchmark
    public int testArraysSort() {
        int[] copy = data.clone();
        Arrays.sort(copy);
        return copy[k];
    }
}
