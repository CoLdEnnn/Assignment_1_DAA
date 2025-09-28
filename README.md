# Assignment_1_DAA
## Overview
Repository with implementations and experiments for:
- MergeSort (D&C, Master Case 2)
- QuickSort (random pivot, smaller-first recursion)
- Deterministic Select (Median-of-Medians, O(n))
- Closest Pair (2D, O(n log n))

### Learning Goals
- Implement classic divide-and-conquer algorithms (MergeSort, QuickSort, Deterministic Select, Closest Pair of Points).
- Analyse running-time recurrences using **Master Theorem** and **Akra–Bazzi**.
- Collect metrics (execution time, recursion depth, comparisons, allocations).
- Communicate results via this `README.md` report and clean Git history.


## Recurrence Analysis

### MergeSort
- Recurrence: T(n) = 2T(n/2) + Θ(n).
- Master Theorem: a = 2, b = 2, f(n) = Θ(n) → Case 2.
- Solution: T(n) = Θ(n log n).

### QuickSort (randomized)
- Expected recurrence: T(n) ≈ 2T(n/2) + Θ(n).
- Expected complexity: Θ(n log n).
- Worst-case: Θ(n²) (very unlikely with random pivot).
- Recursion depth is bounded by O(log n) due to smaller-first recursion.

### Deterministic Select (Median-of-Medians)
- Recurrence: T(n) = T(n/5) + T(7n/10) + Θ(n).
- Using Akra–Bazzi intuition, this solves to T(n) = Θ(n).
- Guarantees linear-time selection.

### Closest Pair of Points
- Recurrence: T(n) = 2T(n/2) + Θ(n).
- Master Theorem: Case 2.
- Solution: T(n) = Θ(n log n).

## Architecture Notes
- **Metrics tracking**: A `MetricsTracker` is used to count comparisons, allocations, and recursion depth. This allows fair comparisons between algorithms.
- **MergeSort**: Uses a reusable buffer to avoid repeated allocations and switches to InsertionSort for small arrays (cutoff ≈ 16).
- **QuickSort**: Implements randomized pivot selection. To bound recursion depth, it always recurses into the smaller partition first and iterates over the larger one. This guarantees O(log n) stack depth in typical cases.
- **Deterministic Select (Median-of-Medians)**: Groups elements in 5s, finds median-of-medians as pivot, partitions in-place, and recurses only on the needed side. Always recurses into the smaller side for depth control.
- **Closest Pair of Points**: Standard divide-and-conquer in 2D. Sorts by x, recurses on halves, and checks the vertical strip in O(n). Uses the 7–8 neighbors check in y-sorted order.

