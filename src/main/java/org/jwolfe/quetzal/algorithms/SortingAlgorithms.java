package org.jwolfe.quetzal.algorithms;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.jwolfe.quetzal.library.utilities.Utilities;

public class SortingAlgorithms {
    public static void quickSort(int[] items) {
        quickSort(items, 0, items.length - 1);
    }

    private static void quickSort(int[] items, int start, int end) {
        if (start < end) {
            int pivot = partition(items, start, end);

            quickSort(items, start, pivot - 1);
            quickSort(items, pivot + 1, end);
        }
    }

    private static int partition(int[] items, int start, int end) {
        int pivotElement = items[start];
        int i = start + 1;
        for (int j = start + 1; j <= end; j++) {
            if (items[j] < pivotElement) {
                Utilities.swap(items, i, j);
                i++;
            }
        }

        Utilities.swap(items, start, i - 1);
        return i - 1;
    }

    public static void mergeSort(int[] items) {
        mergeSort(items, 0, items.length - 1);
    }

    private static void mergeSort(int[] items, int start, int end) {
        if (start < end) {
            int mid = (start + end) / 2;

            mergeSort(items, start, mid);
            mergeSort(items, mid + 1, end);
            merge(items, start, mid, end);
        }
    }

    private static void merge(int[] items, int start, int mid, int end) {
        int[] temp = new int[end - start + 1];
        int lIndex = start;
        int rIndex = mid + 1;
        int tIndex = 0;

        while (lIndex <= mid && rIndex <= end) {
            if (items[lIndex] < items[rIndex]) {
                temp[tIndex++] = items[lIndex++];
            } else {
                temp[tIndex++] = items[rIndex++];
            }
        }

        while (lIndex <= mid) {
            temp[tIndex++] = items[lIndex++];
        }

        while (rIndex <= end) {
            temp[tIndex++] = items[rIndex++];
        }

        for (int i = start; i <= end; i++) {
            items[i] = temp[i - start];
        }
    }

    public static void heapSort(int[] items) {
        int n = items.length;

        for (int i = n / 2 - 1; i >= 0; i--) {
            maxHeapify(items, n, i);
        }

        for (int i = n - 1; i >= 0; i--) {
            Utilities.swap(items, 0, i);
            maxHeapify(items, i, 0);
        }
    }

    private static void maxHeapify(int[] items, int n, int i) {
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        int largest = i;

        if (left < n && items[left] > items[largest]) {
            largest = left;
        }

        if (right < n && items[right] > items[largest]) {
            largest = right;
        }

        if (largest != i) {
            Utilities.swap(items, i, largest);
            maxHeapify(items, n, largest);
        }
    }

    public static void insertionSort(int[] items) {
        insertionSort(items, 0, items.length - 1);
    }

    private static void insertionSort(int[] items, int start, int end) {
        for (int i = start; i <= end; i++) {
            int j = i;

            while (j > start
                    && items[j - 1] > items[j]) {
                Utilities.swap(items, j, j - 1);
                j--;
            }
        }
    }


    public static void selectionSort(int[] items) {
        selectionSort(items, 0, items.length - 1);
    }

    private static void selectionSort(int[] items, int start, int end) {
        for (int i = start; i < end; i++) {
            int minIndex = i;
            for (int j = i + 1; j <= end; j++) {
                if (items[j] < items[minIndex]) {
                    minIndex = j;
                }
            }

            Utilities.swap(items, i, minIndex);
        }
    }

    public static void bubbleSort(int[] items) {
        int n = items.length;
        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (items[j] > items[j + 1]) {
                    Utilities.swap(items, j, j + 1);
                    swapped = true;
                }
            }

            if (!swapped) {
                break;
            }
        }
    }

    public static void countingSort(int[] arr) {
        int n = arr.length;
        int max = getMaxValue(arr);

        int[] count = new int[max + 1];
        Arrays.fill(count, 0);

        for (int i = 0; i < n; i++) {
            count[arr[i]]++;
        }

        for (int i = 1; i < count.length; i++) {
            count[i] = count[i] + count[i - 1];
        }

        int[] result = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            result[count[arr[i]] - 1] = arr[i];
            count[arr[i]]--;
        }

        for (int i = 0; i < n; i++) {
            arr[i] = result[i];
        }
    }

    public static void countingSort(char[] arr) {
        int n = arr.length;

        int[] count = new int[256];
        Arrays.fill(count, 0);

        for (int i = 0; i < n; i++) {
            count[arr[i] - 'a']++;
        }

        for (int i = 1; i < count.length; i++) {
            count[i] = count[i] + count[i - 1];
        }

        char[] result = new char[n];
        for (int i = n - 1; i >= 0; i--) {
            result[count[arr[i] - 'a'] - 1] = arr[i];
            count[arr[i] - 'a']--;
        }

        for (int i = 0; i < n; i++) {
            arr[i] = result[i];
        }
    }

    private static void countingSort(int[] arr, int exp, int radix) {
        int n = arr.length;
        int max = getMaxValue(arr);

        int[] count = new int[radix + 1];
        Arrays.fill(count, 0);

        for (int i = 0; i < n; i++) {
            int countIndex = arr[i] / exp % radix;
            count[countIndex]++;
        }

        for (int i = 1; i < count.length; i++) {
            count[i] = count[i] + count[i - 1];
        }

        int[] result = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            int countIndex = arr[i] / exp % radix;
            result[count[countIndex] - 1] = arr[i];
            count[countIndex]--;
        }

        for (int i = 0; i < n; i++) {
            arr[i] = result[i];
        }
    }

    public static void radixSort(int[] arr) {
        int max = getMaxValue(arr);

        int radix = 10;
        for (int exp = 1; max / exp > 0; exp *= radix) {
            countingSort(arr, exp, radix);
        }
    }

    public static void bucketSort(double[] arr) {
        bucketSort(arr, 5);
    }

    private static void bucketSort(double[] arr, int bucketCount) {
        if (arr == null
                || arr.length == 1) {
            return;
        }

        List<Double>[] buckets = new LinkedList[bucketCount];
        for (int i = 0; i < bucketCount; i++) {
            buckets[i] = new LinkedList<>();
        }

        for (int i = 0; i < arr.length; i++) {
            int bucketIndex = (int) (arr[i] * bucketCount);
            buckets[bucketIndex].add(arr[i]);
        }

        for (int i = 0; i < bucketCount; i++) {
            List<Double> bucket = buckets[i];
            Collections.sort(bucket);
        }

        int index = 0;
        for (var bucket : buckets) {
            for (int i = 0; i < bucket.size(); i++) {
                arr[index] = bucket.get(i);
                index++;
            }
        }
    }

    public static void shellSort(int[] arr) {
        if (arr == null
                || arr.length == 1) {
            return;
        }

        int n = arr.length;
        for (int gap = n / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < n; i++) {
                int temp = arr[i];
                int j;
                for (j = i; (j >= gap) && arr[j - gap] > temp; j -= gap) {
                    arr[j] = arr[j - gap];
                }

                arr[j] = temp;
            }
        }
    }

    private static int getMaxValue(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            if (max < arr[i]) {
                max = arr[i];
            }
        }

        return max;
    }
}
