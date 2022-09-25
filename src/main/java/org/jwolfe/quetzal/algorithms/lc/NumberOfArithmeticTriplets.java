package org.jwolfe.quetzal.algorithms.lc;

import java.util.HashSet;
import java.util.Set;

public class NumberOfArithmeticTriplets {
    class Solution {
        public int arithmeticTriplets(int[] nums, int diff) {
            if(nums == null || nums.length < 3 || diff < 1) {
                return 0;
            }

            int tripletCount = 0;
            for(int i = 0; i < nums.length - 1; i++) {
                int a = nums[i];
                int b = a + diff;

                int index = binarySearch(nums, b, i + 1);
                if(index != -1) {
                    int c = b + diff;
                    index = binarySearch(nums, c, index + 1);

                    if(index != -1) {
                        tripletCount++;
                    }
                }
            }

            return tripletCount;
        }

        private int binarySearch(int[] nums, int target, int left) {
            int right = nums.length - 1;

            while(left <= right) {
                int mid = left + (right - left) / 2;

                int num = nums[mid];
                if(num == target) {
                    return mid;
                } else if(num < target) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }

            return -1;
        }
    }

    class Solution_Correct_1 {
        public int arithmeticTriplets(int[] nums, int diff) {
            if(nums == null || nums.length < 3 || diff < 1) {
                return 0;
            }

            Set<Integer> set = new HashSet<>();
            for(int a : nums) {
                set.add(a);
            }

            int tripletCount = 0;
            for(int i = 0; i < nums.length - 2; i++) {
                int a = nums[i];
                int b = a + diff;
                int c = b + diff;

                if(set.contains(b) && set.contains(c)) {
                    tripletCount++;
                }
            }

            return tripletCount;
        }
    }
}

//    2367. Number of Arithmetic Triplets
//    Easy
//    You are given a 0-indexed, strictly increasing integer array nums and a positive integer diff. A triplet (i, j, k) is an arithmetic triplet if the following conditions are met:
//
//    i < j < k,
//    nums[j] - nums[i] == diff, and
//    nums[k] - nums[j] == diff.
//    Return the number of unique arithmetic triplets.
//
//
//
//    Example 1:
//
//    Input: nums = [0,1,4,6,7,10], diff = 3
//    Output: 2
//    Explanation:
//    (1, 2, 4) is an arithmetic triplet because both 7 - 4 == 3 and 4 - 1 == 3.
//    (2, 4, 5) is an arithmetic triplet because both 10 - 7 == 3 and 7 - 4 == 3.
//    Example 2:
//
//    Input: nums = [4,5,6,7,8,9], diff = 2
//    Output: 2
//    Explanation:
//    (0, 2, 4) is an arithmetic triplet because both 8 - 6 == 2 and 6 - 4 == 2.
//    (1, 3, 5) is an arithmetic triplet because both 9 - 7 == 2 and 7 - 5 == 2.
//
//
//    Constraints:
//
//    3 <= nums.length <= 200
//    0 <= nums[i] <= 200
//    1 <= diff <= 50
//    nums is strictly increasing.