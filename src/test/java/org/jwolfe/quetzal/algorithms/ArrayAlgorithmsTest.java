package org.jwolfe.quetzal.algorithms;

import org.junit.jupiter.api.Test;
import org.jwolfe.quetzal.library.utilities.Utilities;

import static org.junit.jupiter.api.Assertions.*;

class ArrayAlgorithmsTest {
    @Test
    void binarySearch() {
        int[] arr = {2, 5, 8, 12, 16, 23, 38, 56, 72, 91};
        int val = 23;

        int index = ArrayAlgorithms.binarySearch(arr, val);
        System.out.println(index);
        assertEquals(5, index);
    }

    @Test
    void binarySearchIterative() {
        int[] arr = {2, 5, 8, 12, 16, 23, 38, 56, 72, 91};
        int val = 23;

        int index = ArrayAlgorithms.binarySearchIterative(arr, val);
        System.out.println(index);
        assertEquals(5, index);
    }

    @Test
    void binarySearchRecursive() {
        int[] arr = {2, 5, 8, 12, 16, 23, 38, 56, 72, 91};
        int val = 23;

        int index = ArrayAlgorithms.binarySearchRecursive(arr, val);
        System.out.println(index);
        assertEquals(5, index);
    }

    @Test
    void equilibriumIndex() {
        int[] arr;
        int equilibriumIndex;

        arr = Utilities.constructArray(-7, 1, 5, 2, -4, 3, 0);
        equilibriumIndex = ArrayAlgorithms.equilibriumIndex(arr);
        System.out.println(equilibriumIndex);
        assertEquals(3, equilibriumIndex);
    }

    @Test
    void jumpSearch() {
        int[] arr;
        int val;
        int index;

        arr = Utilities.constructArray(2, 5, 8, 12, 16, 23, 38, 56, 72, 91);
        val = 23;
        index = ArrayAlgorithms.jumpSearch(arr, val);
        System.out.println(index);
        assertEquals(5, index);

        arr = Utilities.constructArray(0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610);
        val = 55;
        index = ArrayAlgorithms.jumpSearch(arr, val);
        System.out.println(index);
        assertEquals(10, index);

    }

    @Test
    void searchInAlmostSortedArray() {
        int[] arr;
        int index;

        arr = Utilities.constructArray(3, 2, 10, 4, 40);
        index = ArrayAlgorithms.searchInAlmostSortedArray(arr, 4);
        assertEquals(3, index);

        arr = Utilities.constructArray(10, 3, 40, 20, 50, 80, 70);
        index = ArrayAlgorithms.searchInAlmostSortedArray(arr, 40);
        assertEquals(2, index);

        arr = Utilities.constructArray(10, 3, 40, 20, 50, 80, 70);
        index = ArrayAlgorithms.searchInAlmostSortedArray(arr, 90);
        assertEquals(-1, index);
    }
}