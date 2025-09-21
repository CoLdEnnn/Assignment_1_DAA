package algorithms.geometry;

import algorithms.metrics.MetricsTracker;

import java.util.Arrays;
import java.util.Comparator;

public class ClosestPair {
    public static double findClosestPair(Point[] points, MetricsTracker tracker) {
        Point[] pointsByX = points.clone();
        Arrays.sort(pointsByX, Comparator.comparingDouble(p -> p.x));
        Point[] pointsByY = points.clone();
        Arrays.sort(pointsByY, Comparator.comparingDouble(p -> p.y));
        return closest(pointsByX, pointsByY, 0, points.length - 1, tracker);
    }

    private static double closest(Point[] px, Point [] py, int left, int right, MetricsTracker tracker) {
        if (right - left <= 3) {
            return bruteForce(px, left, right, tracker);
        }
        int mid = (left + right) / 2;
        Point midPoint = px[mid];

        Point[] pyl = new Point[mid - left + 1];
        Point[] pyr = new Point[right - mid];
        int li = 0, ri = 0;
        for (Point p : py) {
            if (p.x <= midPoint.x && li < pyl.length) pyl[li++] = p;
            else pyr[ri++] = p;
        }

        tracker.enterRecursion();
        double dl = closest(px, pyl, left, mid, tracker);
        double dr = closest(py, pyr, mid + 1, right, tracker);
        tracker.exitRecursion();

        double d = Math.min(dl, dr);
        return Math.min(d, stripClosest(py, midPoint.x, d, tracker));
    }

    private static double bruteForce(Point[] points, int left, int right, MetricsTracker tracker) {
        double min = Double.POSITIVE_INFINITY;
        for (int i = left; i<= right; i++) {
            for (int j = i + 1; j<= right; j++) {
                tracker.incComparisons();
                min = Math.min(min, distance(points[i], points[j]));
            }
        }
        return min;
    }

    private static double stripClosest(Point[] py, double midX, double d, MetricsTracker tracker) {
        Point[] strip = Arrays.stream(py)
                .filter(p -> Math.abs(p.x - midX) < d)
                .toArray(Point[]::new);

        double min = d;
        for (int i = 0; i < strip.length; i++) {
            for (int j = i + 1; j < strip.length && (strip[j].y - strip[i].y) < min; j++) {
                tracker.incComparisons();
                min = Math.min(min, distance(strip[i], strip[j]));
            }
        }
        return min;
    }

    private static double distance(Point p1, Point p2) {
        double dx  =  p1.x - p2.x;
        double dy  =  p1.y - p2.y;
        return Math.sqrt(dx * dx + dy * dy);
    }
}
