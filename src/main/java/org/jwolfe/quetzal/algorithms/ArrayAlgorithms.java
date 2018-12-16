package org.jwolfe.quetzal.algorithms;

public class ArrayAlgorithms {
    public static int binarySearch(int[] arr, int val) {
        return binarySearchIterative(arr, val);
    }

    public static int binarySearchIterative(int[] arr, int val) {
        int left = 0;
        int right = arr.length - 1;

        return binarySearch(arr, val, left, right);
    }

    public static int binarySearch(int[] arr, int val, int left, int right) {
        while(left <= right) {
            int mid = (left + right) / 2;

            if(arr[mid] == val)
                return mid;
            else if(arr[mid] < val) {
                left = mid + 1;
            }
            else {
                right = mid - 1;
            }
        }

        return -1;
    }

    public static int binarySearchRecursive(int[] arr, int val) {
        return binarySearchRecursive(arr, val, 0, arr.length - 1);
    }

    private static int binarySearchRecursive(int[] arr, int val, int left, int right) {
        if(left >= right) {
            return  -1;
        }

        int mid = (left + right) / 2;

        if (arr[mid] == val)
            return mid;
        else if (arr[mid] < val) {
            return binarySearchRecursive(arr, val, mid +1, right);
        }
        else {
            return binarySearchRecursive(arr, val, left, mid - 1 );
        }
    }

    public static int equilibriumIndex(int[] array) {
        // Equilibrium index of an array is an index such that the sum of elements at lower indexes is equal to the sum of elements at higher indexes.
        // Approach 1: 2 loops for calculating leftSum & rightSum inside the master loop.

        // Approach 2
        int totalSum = 0;
        for(int i = 0; i < array.length; i++) {
            totalSum += array[i];
        }

        int leftSum = 0;
        int rightSum = totalSum;
        for(int i = 0; i < array.length; i++) {
            int value = array[i];

            rightSum -= value;
            if(leftSum == rightSum) {
                return i;
            }

            leftSum += value;
        }

        return -1;
    }

    public static int jumpSearch(int[] arr, int value) {
        int length = arr.length;
        int step = (int) Math.sqrt(length);

        return jumpSearch(arr, value, step);
    }

    private static int jumpSearch(int[] arr, int value, int step) {
        int length = arr.length;
        int blockStart = 0;
        int blockEnd = step;

        // Jump search to a block
        while(arr[Math.min(blockEnd, length) - 1] < value) {
            blockStart = blockEnd;
            blockEnd += step;

            if(blockStart >= length) {
                return -1;
            }
        }

        // Linear search within a block
        while(arr[blockStart] < value) {
            blockStart++;

            if(blockStart >= Math.min(blockEnd, length)) {
                return -1;
            }
        }

        if(arr[blockStart] == value) {
            return blockStart;
        }

        return -1;
    }

    public static int searchInAlmostSortedArray(int[] arr, int x) {
        // Post mixing the sorted array, arr[i] may be present at arr[i+1] or arr[i-1].
        if(arr == null
                || arr.length == 0) {
            return -1;
        }

        return searchInAlmostSortedArray(arr, x, 0, arr.length - 1);
    }

    private static int searchInAlmostSortedArray(int[] arr, int x, int left, int right) {
        if(left > right) {
            return -1;
        }

        int mid = (left + right) / 2;
        if(arr[mid] == x) {
            return mid;
        }
        else if(mid >= 0
                && arr[mid-1] == x) {
            return mid - 1;
        }
        else if(mid <= right
                && arr[mid + 1] == x) {
            return mid + 1;
        }

        if(arr[mid] > x) {
            return searchInAlmostSortedArray(arr, x, left, mid - 2);
        }
        else {
            return searchInAlmostSortedArray(arr, x, mid + 2, right);
        }
    }
}
