package algorithms.sort;

public class SortMetrics {
    private final long timeNanos;
    private final String algorithm;

    public SortMetrics(long timeNanos, String algorithm) {
        this.timeNanos = timeNanos;
        this.algorithm = algorithm;
    }

    public long getTimeNanos() {
        return timeNanos;
    }

    public String getAlgorithm() {
        return algorithm;
    }

    @Override
    public String toString() {
        return algorithm + " took " + timeNanos + " ns";
    }
}
