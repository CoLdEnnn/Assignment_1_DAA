package algorithms.geometry;

import algorithms.metrics.MetricsTracker;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClosestPairTest {
    @Test
    void testSimplePoints() {
        Point[] points = {
                new Point(0,0),
                new Point(3,4),
                new Point(7,7),
                new Point(1,1)
        };
        MetricsTracker tracker = new MetricsTracker();
        double d = ClosestPair.findClosestPair(points, tracker);
        assertEquals(Math.sqrt(2), d, 1e-9);
    }

    @Test
    void testRandomPoints() {
        Point[] points = new Point[100];
        for (int i = 0; i < 100; i++) {
            points[i] = new Point(Math.random()*1000,Math.random()*1000);
        }
        MetricsTracker tracker = new MetricsTracker();
        double d = ClosestPair.findClosestPair(points, tracker);
        assertTrue(d >= 0);
    }

    @Test
    void testTwoPoints() {
        Point[] points = {new Point(0,0), new Point(3,4)};
        MetricsTracker tracker = new MetricsTracker();
        double d = ClosestPair.findClosestPair(points, tracker);
        assertEquals(5.0, d, 1e-9);
    }

}
