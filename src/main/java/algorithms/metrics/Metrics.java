package algorithms.metrics;

public class Metrics {
    private long comparisons = 0;
    private long allocations = 0;
    private int maxDepth = 0;

    public void incComparisons() {comparisons++;}
    public void incAllocations() {allocations++;}

    public void updateDepth(int currentDepth) {
        if (currentDepth > maxDepth) {
            maxDepth = currentDepth;
        }
    }

    public long getComparisons() {return comparisons;}
    public long getAllocations() {return allocations;}
    public int getMaxDepth() {return maxDepth;}

    public void reset() {
        comparisons = 0;
        allocations = 0;
        maxDepth = 0;
    }
}
