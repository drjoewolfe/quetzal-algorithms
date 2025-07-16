package org.jwolfe.quetzal.algorithms.lc;

public class FindTheMaximumLengthOfValidSubsequenceI {
    class Solution {
        public int maximumLength(int[] nums) {
            if (nums == null || nums.length < 2) {
                return 0;
            }

            int n = nums.length;
            int evenCount = 0;
            int oddEvenCount = 1;
            for (int i = 0; i < n; i++) {
                int num = nums[i];

                if (num % 2 == 0) {
                    evenCount++;
                }

                if (i > 0 && ((nums[i - 1] % 2) != (nums[i] % 2))) {
                    oddEvenCount++;
                }
            }

            int oddCount = n - evenCount;
            return Math.max(oddCount,
                    Math.max(evenCount, oddEvenCount));
        }
    }
}

//    3201. Find the Maximum Length of Valid Subsequence I
//    Medium
//    You are given an integer array nums.
//    A subsequence sub of nums with length x is called valid if it satisfies:
//
//    (sub[0] + sub[1]) % 2 == (sub[1] + sub[2]) % 2 == ... == (sub[x - 2] + sub[x - 1]) % 2.
//    Return the length of the longest valid subsequence of nums.
//
//    A subsequence is an array that can be derived from another array by deleting some or no elements without changing the order of the remaining elements.
//
//
//
//    Example 1:
//
//    Input: nums = [1,2,3,4]
//
//    Output: 4
//
//    Explanation:
//
//    The longest valid subsequence is [1, 2, 3, 4].
//
//    Example 2:
//
//    Input: nums = [1,2,1,1,2,1,2]
//
//    Output: 6
//
//    Explanation:
//
//    The longest valid subsequence is [1, 2, 1, 2, 1, 2].
//
//    Example 3:
//
//    Input: nums = [1,3]
//
//    Output: 2
//
//    Explanation:
//
//    The longest valid subsequence is [1, 3].
//
//
//
//    Constraints:
//
//    2 <= nums.length <= 2 * 105
//    1 <= nums[i] <= 107