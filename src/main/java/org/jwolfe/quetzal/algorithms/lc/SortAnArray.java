package org.jwolfe.quetzal.algorithms.lc;

public class SortAnArray {
    class Solution {
        public int[] sortArray(int[] nums) {
            if (nums == null || nums.length == 0) {
                return nums;
            }

            mergesort(nums, 0, nums.length - 1);
            return nums;
        }

        private void mergesort(int[] nums, int start, int end) {
            if (start >= end) {
                return;
            }

            int mid = (start + end) / 2;
            mergesort(nums, start, mid);
            mergesort(nums, mid + 1, end);
            merge(nums, start, mid, end);
        }

        private void merge(int[] nums, int start, int mid, int end) {
            int n = end - start + 1;
            int[] temp = new int[n];

            int left = start;
            int right = mid + 1;
            int index = 0;

            while (left <= mid && right <= end) {
                if (nums[left] < nums[right]) {
                    temp[index++] = nums[left++];
                } else {
                    temp[index++] = nums[right++];
                }
            }

            while (left <= mid) {
                temp[index++] = nums[left++];
            }

            while (right <= end) {
                temp[index++] = nums[right++];
            }

            for (index = 0; index < n; index++) {
                nums[start + index] = temp[index];
            }
        }
    }

    class Solution_Quick_2 {
        public int[] sortArray(int[] nums) {
            if (nums == null || nums.length == 0) {
                return nums;
            }

            quicksort(nums, 0, nums.length - 1);
            return nums;
        }

        private void quicksort(int[] nums, int left, int right) {
            if (left >= right) {
                return;
            }

            int pivot = partition(nums, left, right);
            quicksort(nums, left, pivot - 1);
            quicksort(nums, pivot + 1, right);
        }

        private int partition(int[] nums, int left, int right) {
            int pivotElement = nums[right];

            int i = left - 1;
            for (int j = left; j < right; j++) {
                if (nums[j] < pivotElement) {
                    swap(nums, ++i, j);
                }
            }

            swap(nums, ++i, right);
            return i;
        }

        private void swap(int[] nums, int left, int right) {
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
        }
    }

    class Solution_Merge {
        public int[] sortArray(int[] nums) {
            if (nums == null || nums.length == 0) {
                return nums;
            }

            mergeSort(nums, 0, nums.length - 1);
            return nums;
        }

        private void mergeSort(int[] nums, int start, int end) {
            if (start >= end) {
                return;
            }

            int mid = (start + end) / 2;

            mergeSort(nums, start, mid);
            mergeSort(nums, mid + 1, end);
            merge(nums, start, mid, end);
        }

        private void merge(int[] nums, int start, int mid, int end) {
            int length = end - start + 1;
            int[] temp = new int[length];

            int left = start;
            int right = mid + 1;
            int index = 0;

            while (left <= mid && right <= end) {
                if (nums[left] < nums[right]) {
                    temp[index++] = nums[left++];
                } else {
                    temp[index++] = nums[right++];
                }
            }

            while (left <= mid) {
                temp[index++] = nums[left++];
            }

            while (right <= end) {
                temp[index++] = nums[right++];
            }

            for (int i = 0; i < length; i++) {
                nums[start + i] = temp[i];
            }
        }


        private void swap(int[] nums, int i, int j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }

    class Solution_Quick {
        public int[] sortArray(int[] nums) {
            if (nums == null || nums.length == 0) {
                return nums;
            }

            quickSort(nums, 0, nums.length - 1);
            return nums;
        }

        private void quickSort(int[] nums, int start, int end) {
            if (start >= end) {
                return;
            }

            int pivot = partition(nums, start, end);
            quickSort(nums, start, pivot - 1);
            quickSort(nums, pivot + 1, end);
        }

        private int partition(int[] nums, int start, int end) {
            int pivotElement = nums[end];

            int i = start - 1;
            for (int j = start; j < end; j++) {
                if (nums[j] < pivotElement) {
                    swap(nums, ++i, j);
                }
            }

            swap(nums, ++i, end);
            return i;
        }

        private void swap(int[] nums, int i, int j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }

// [5,2,3,1]
}

//    912. Sort an Array
//    Medium
//    Given an array of integers nums, sort the array in ascending order and return it.
//
//    You must solve the problem without using any built-in functions in O(nlog(n)) time complexity and with the smallest space complexity possible.
//
//
//
//    Example 1:
//
//    Input: nums = [5,2,3,1]
//    Output: [1,2,3,5]
//    Explanation: After sorting the array, the positions of some numbers are not changed (for example, 2 and 3), while the positions of other numbers are changed (for example, 1 and 5).
//    Example 2:
//
//    Input: nums = [5,1,1,2,0,0]
//    Output: [0,0,1,1,2,5]
//    Explanation: Note that the values of nums are not necessairly unique.
//
//
//    Constraints:
//
//    1 <= nums.length <= 5 * 104
//    -5 * 104 <= nums[i] <= 5 * 104
