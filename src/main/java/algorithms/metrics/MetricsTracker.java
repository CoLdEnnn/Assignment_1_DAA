package algorithms.metrics;

public class MetricsTracker {
    private final Metrics metrics = new Metrics();
    private int currentDepth = 0;

    public void enterRecursion() {
        currentDepth++;
        metrics.updateDepth(currentDepth);
    }

    public void exitRecursion() {
        currentDepth--;
    }

    public void incComparisons() {
        metrics.incComparisons();
    }

    public void incAllocations() {
        metrics.incAllocations();
    }

    public Metrics snapshot() {
        return metrics;
    }

    public void reset() {
        metrics.reset();
        currentDepth = 0;
    }
}
