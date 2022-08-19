package org.jwolfe.quetzal.algorithms.lc;

import java.util.HashMap;
import java.util.Map;

public class SplitArrayIntoConsecutiveSubsequences {
    class Solution {
        public boolean isPossible(int[] nums) {
            if(nums == null || nums.length < 3) {
                return false;
            }

            Map<Integer, Integer> frequency = new HashMap<>();
            Map<Integer, Integer> need = new HashMap<>();

            for(int val : nums) {
                frequency.put(val, frequency.getOrDefault(val, 0) + 1);
                need.put(val, 0);
            }

            for(int i = 0; i < nums.length; i++) {
                int a = nums[i];

                if(frequency.get(a) == 0) {
                    // This element has been exhausted
                    continue;
                }

                frequency.put(a, frequency.get(a) - 1);

                int b = a + 1;
                int c = b + 1;
                int d = c + 1;

                if(need.get(a) > 0) {
                    // A subsequence needs this element
                    need.put(a, need.get(a) - 1);
                    if(frequency.containsKey(b)) {
                        need.put(b, need.get(b) + 1);
                    }

                    continue;
                }

                if(!frequency.containsKey(b) || !frequency.containsKey(c)) {
                    // 3 consecutive elements do not exist. Cannot create the subsequence
                    return false;
                }

                if(frequency.get(b) > 0 && frequency.get(c) > 0) {
                    // New subsequence
                    frequency.put(b, frequency.get(b) - 1);
                    frequency.put(c, frequency.get(c) - 1);

                    if(frequency.containsKey(d)) {
                        need.put(d, need.get(d) + 1);
                    }
                } else {
                    return false;
                }
            }

            return true;
        }
    }

    // [1,2,3,3,4,5]
}

//    659. Split Array into Consecutive Subsequences
//    Medium
//
//    3636
//
//    711
//
//    Add to List
//
//    Share
//    You are given an integer array nums that is sorted in non-decreasing order.
//
//    Determine if it is possible to split nums into one or more subsequences such that both of the following conditions are true:
//
//    Each subsequence is a consecutive increasing sequence (i.e. each integer is exactly one more than the previous integer).
//    All subsequences have a length of 3 or more.
//    Return true if you can split nums according to the above conditions, or false otherwise.
//
//    A subsequence of an array is a new array that is formed from the original array by deleting some (can be none) of the elements without disturbing the relative positions of the remaining elements. (i.e., [1,3,5] is a subsequence of [1,2,3,4,5] while [1,3,2] is not).
//
//
//
//    Example 1:
//
//    Input: nums = [1,2,3,3,4,5]
//    Output: true
//    Explanation: nums can be split into the following subsequences:
//    [1,2,3,3,4,5] --> 1, 2, 3
//    [1,2,3,3,4,5] --> 3, 4, 5
//    Example 2:
//
//    Input: nums = [1,2,3,3,4,4,5,5]
//    Output: true
//    Explanation: nums can be split into the following subsequences:
//    [1,2,3,3,4,4,5,5] --> 1, 2, 3, 4, 5
//    [1,2,3,3,4,4,5,5] --> 3, 4, 5
//    Example 3:
//
//    Input: nums = [1,2,3,4,4,5]
//    Output: false
//    Explanation: It is impossible to split nums into consecutive increasing subsequences of length 3 or more.
//
//
//    Constraints:
//
//    1 <= nums.length <= 104
//    -1000 <= nums[i] <= 1000
//    nums is sorted in non-decreasing order.
