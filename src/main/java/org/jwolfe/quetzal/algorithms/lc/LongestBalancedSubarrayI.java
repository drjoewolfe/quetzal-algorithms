package org.jwolfe.quetzal.algorithms.lc;

import java.util.HashSet;
import java.util.Set;

public class LongestBalancedSubarrayI {
    class Solution {
        public int longestBalanced(int[] nums) {
            if (nums == null || nums.length < 2) {
                return 0;
            }

            int n = nums.length;
            int maxLength = 0;
            for (int i = 0; i < n - 1; i++) {
                Set<Integer> odd = new HashSet<>();
                Set<Integer> even = new HashSet<>();

                for (int j = i; j < n; j++) {
                    int x = nums[j];
                    if (x % 2 == 0) {
                        even.add(x);
                    } else {
                        odd.add(x);
                    }

                    if (even.size() == odd.size()) {
                        // Balanced subarray
                        maxLength = Math.max(maxLength, j - i + 1);
                    }
                }
            }

            return maxLength;
        }
    }
}

//    3719. Longest Balanced Subarray I
//    Medium
//    You are given an integer array nums.
//
//    A subarray is called balanced if the number of distinct even numbers in the subarray is equal to the number of distinct odd numbers.
//
//    Return the length of the longest balanced subarray.
//
//
//
//    Example 1:
//
//    Input: nums = [2,5,4,3]
//
//    Output: 4
//
//    Explanation:
//
//    The longest balanced subarray is [2, 5, 4, 3].
//    It has 2 distinct even numbers [2, 4] and 2 distinct odd numbers [5, 3]. Thus, the answer is 4.
//    Example 2:
//
//    Input: nums = [3,2,2,5,4]
//
//    Output: 5
//
//    Explanation:
//
//    The longest balanced subarray is [3, 2, 2, 5, 4].
//    It has 2 distinct even numbers [2, 4] and 2 distinct odd numbers [3, 5]. Thus, the answer is 5.
//    Example 3:
//
//    Input: nums = [1,2,3,2]
//
//    Output: 3
//
//    Explanation:
//
//    The longest balanced subarray is [2, 3, 2].
//    It has 1 distinct even number [2] and 1 distinct odd number [3]. Thus, the answer is 3.
//
//
//    Constraints:
//
//    1 <= nums.length <= 1500
//    1 <= nums[i] <= 105