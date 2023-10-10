package org.jwolfe.quetzal.algorithms.lc;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class MinimumNumberOfOperationsToMakeArrayContinuous {
    class Solution {
        public int minOperations(int[] nums) {
            if(nums == null || nums.length == 0) {
                return 0;
            }

            int n = nums.length;
            int minOperations = n;
            int[] nums2 = uniqueSort(nums);
            for(int i = 0; i < nums2.length; i++) {
                int left = nums2[i];
                int right = left + n - 1;
                int j = binarySearchForNextGreater(nums2, right);

                int size = j - i;
                int operations = n - size;
                minOperations = Math.min(minOperations, operations);
            }

            return minOperations;
        }

        private int[] uniqueSort(int[] nums) {
            Set<Integer> set = new HashSet<>();
            for(int val : nums) {
                set.add(val);
            }

            int[] nums2 = new int[set.size()];
            int index = 0;
            for(int val : set) {
                nums2[index++] = val;
            }

            Arrays.sort(nums2);
            return nums2;
        }

        private int binarySearchForNextGreater(int[] nums, int target) {
            int left = 0;
            int right = nums.length;

            while(left < right) {
                int mid = left + (right - left) / 2;
                int val = nums[mid];

                if(val > target) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }

            return left;
        }
    }

// [4,2,5,3]
// [1,2,3,5,6]
}

//    2009. Minimum Number of Operations to Make Array Continuous
//    Hard
//    You are given an integer array nums. In one operation, you can replace any element in nums with any integer.
//
//    nums is considered continuous if both of the following conditions are fulfilled:
//
//    All elements in nums are unique.
//    The difference between the maximum element and the minimum element in nums equals nums.length - 1.
//    For example, nums = [4, 2, 5, 3] is continuous, but nums = [1, 2, 3, 5, 6] is not continuous.
//
//    Return the minimum number of operations to make nums continuous.
//
//
//
//    Example 1:
//
//    Input: nums = [4,2,5,3]
//    Output: 0
//    Explanation: nums is already continuous.
//    Example 2:
//
//    Input: nums = [1,2,3,5,6]
//    Output: 1
//    Explanation: One possible solution is to change the last element to 4.
//    The resulting array is [1,2,3,5,4], which is continuous.
//    Example 3:
//
//    Input: nums = [1,10,100,1000]
//    Output: 3
//    Explanation: One possible solution is to:
//    - Change the second element to 2.
//    - Change the third element to 3.
//    - Change the fourth element to 4.
//    The resulting array is [1,2,3,4], which is continuous.
//
//
//    Constraints:
//
//    1 <= nums.length <= 105
//    1 <= nums[i] <= 109