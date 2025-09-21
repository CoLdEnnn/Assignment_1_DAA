package Algorithms.util;

import java.util.Random;

public class ArrayUtils {
    private static final Random random = new Random();

    public static void swap(int[] arr, int i, int j) {
        if (i != j) {
            int tmp  = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }
    }

    public static void shuffle(int[] arr) {
        for (int i = arr.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            swap(arr, i, j);
        }
    }

    public static boolean isSorted(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i - 1] > arr[i]) return false;
        }
        return true;
    }
}
