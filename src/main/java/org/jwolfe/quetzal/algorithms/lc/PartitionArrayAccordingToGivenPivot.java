package org.jwolfe.quetzal.algorithms.lc;

public class PartitionArrayAccordingToGivenPivot {
    class Solution {
        public int[] pivotArray(int[] nums, int pivot) {
            if (nums == null || nums.length == 0) {
                return null;
            }

            int n = nums.length;
            int[] ans = new int[n];

            int si = 0;
            int gi = n - 1;

            for (int i = 0, j = n - 1; i < n; i++, j--) {
                int vali = nums[i];
                int valj = nums[j];

                if (vali < pivot) {
                    ans[si++] = vali;
                }

                if (valj > pivot) {
                    ans[gi--] = valj;
                }
            }

            while (si <= gi) {
                ans[si++] = pivot;
            }

            return ans;
        }
    }

    class Solution_Incorrect_1 {
        public int[] pivotArray(int[] nums, int pivot) {
            if (nums == null || nums.length == 0) {
                return null;
            }

            int n = nums.length;
            int i = 0;
            for (int j = 0; j < n; j++) {
                int val = nums[j];

                if (val < pivot) {
                    swap(nums, i, j);
                    i++;
                }
            }

            swap(nums, i, n - 1);
            return nums;
        }

        private void swap(int[] nums, int i, int j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }
}

//    2161. Partition Array According to Given Pivot
//    Medium
//    You are given a 0-indexed integer array nums and an integer pivot. Rearrange nums such that the following conditions are satisfied:
//
//    Every element less than pivot appears before every element greater than pivot.
//    Every element equal to pivot appears in between the elements less than and greater than pivot.
//    The relative order of the elements less than pivot and the elements greater than pivot is maintained.
//    More formally, consider every pi, pj where pi is the new position of the ith element and pj is the new position of the jth element. If i < j and both elements are smaller (or larger) than pivot, then pi < pj.
//    Return nums after the rearrangement.
//
//
//
//    Example 1:
//
//    Input: nums = [9,12,5,10,14,3,10], pivot = 10
//    Output: [9,5,3,10,10,12,14]
//    Explanation:
//    The elements 9, 5, and 3 are less than the pivot so they are on the left side of the array.
//    The elements 12 and 14 are greater than the pivot so they are on the right side of the array.
//    The relative ordering of the elements less than and greater than pivot is also maintained. [9, 5, 3] and [12, 14] are the respective orderings.
//    Example 2:
//
//    Input: nums = [-3,4,3,2], pivot = 2
//    Output: [-3,2,4,3]
//    Explanation:
//    The element -3 is less than the pivot so it is on the left side of the array.
//    The elements 4 and 3 are greater than the pivot so they are on the right side of the array.
//    The relative ordering of the elements less than and greater than pivot is also maintained. [-3] and [4, 3] are the respective orderings.
//
//
//    Constraints:
//
//    1 <= nums.length <= 105
//    -106 <= nums[i] <= 106
//    pivot equals to an element of nums.