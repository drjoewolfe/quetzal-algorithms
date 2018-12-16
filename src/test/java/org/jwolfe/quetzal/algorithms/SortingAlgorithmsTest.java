package org.jwolfe.quetzal.algorithms;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class SortingAlgorithmsTest {
    int[] itemList1;
    int[] sortedItemList1 = new int[] {1, 3, 5, 6, 10, 21, 32, 43, 49, 54, 65, 67, 76, 81, 88, 98, 99, 100};

    double[] doubleItemList1;
    double[] sortedDoubleItemList1 = new double[] {0.1234, 0.3434, 0.565, 0.656, 0.665, 0.897};

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        itemList1 = new int[]{100, 10, 1, 3, 5, 6, 99, 43, 32, 21, 67, 88, 98, 65, 54, 76, 49, 81};
        doubleItemList1 = new double[] {0.897, 0.565, 0.656, 0.1234, 0.665, 0.3434};
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }

    @org.junit.jupiter.api.Test
    void quickSort() {
        System.out.println(Arrays.toString(itemList1));
        SortingAlgorithms.quickSort(itemList1);
        System.out.println(Arrays.toString(itemList1));

        assertArrayEquals(sortedItemList1, itemList1);
    }

    @org.junit.jupiter.api.Test
    void mergeSort() {
        System.out.println(Arrays.toString(itemList1));
        SortingAlgorithms.mergeSort(itemList1);
        System.out.println(Arrays.toString(itemList1));

        assertArrayEquals(sortedItemList1, itemList1);
    }

    @org.junit.jupiter.api.Test
    void heapSort() {
        System.out.println(Arrays.toString(itemList1));
        SortingAlgorithms.heapSort(itemList1);
        System.out.println(Arrays.toString(itemList1));

        assertArrayEquals(sortedItemList1, itemList1);
    }

    @org.junit.jupiter.api.Test
    void insertionSort() {
        System.out.println(Arrays.toString(itemList1));
        SortingAlgorithms.insertionSort(itemList1);
        System.out.println(Arrays.toString(itemList1));

        assertArrayEquals(sortedItemList1, itemList1);
    }

    @org.junit.jupiter.api.Test
    void selectionSort() {
        System.out.println(Arrays.toString(itemList1));
        SortingAlgorithms.selectionSort(itemList1);
        System.out.println(Arrays.toString(itemList1));

        assertArrayEquals(sortedItemList1, itemList1);
    }

    @Test
    void bubbleSort() {
        System.out.println(Arrays.toString(itemList1));
        SortingAlgorithms.bubbleSort(itemList1);
        System.out.println(Arrays.toString(itemList1));

        assertArrayEquals(sortedItemList1, itemList1);
    }

    @Test
    void countingSort() {
        char arr[] = {'c', 'o', 'u', 'n', 't', 'i', 'n', 'g', 's', 'o', 'r', 't'};
        System.out.println(Arrays.toString(arr));
        SortingAlgorithms.countingSort(arr);
        System.out.println(Arrays.toString(arr));

        System.out.println(Arrays.toString(itemList1));
        SortingAlgorithms.countingSort(itemList1);
        System.out.println(Arrays.toString(itemList1));
        assertArrayEquals(sortedItemList1, itemList1);
    }

    @Test
    void radixSort() {
        System.out.println(Arrays.toString(itemList1));
        SortingAlgorithms.radixSort(itemList1);
        System.out.println(Arrays.toString(itemList1));

        assertArrayEquals(sortedItemList1, itemList1);
    }

    @Test
    void bucketSort() {
        System.out.println(Arrays.toString(doubleItemList1));
        SortingAlgorithms.bucketSort(doubleItemList1);
        System.out.println(Arrays.toString(doubleItemList1));

        assertArrayEquals(sortedDoubleItemList1, doubleItemList1);
    }

    @Test
    void shellSort() {
        System.out.println(Arrays.toString(itemList1));
        SortingAlgorithms.shellSort(itemList1);
        System.out.println(Arrays.toString(itemList1));

        assertArrayEquals(sortedItemList1, itemList1);
    }
}