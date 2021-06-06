package org.jwolfe.quetzal.algorithms.lc;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class LongestConsecutiveSequence {
    class Solution {
        public int longestConsecutive(int[] nums) {
            if(nums == null || nums.length == 0) {
                return 0;
            }

            Set<Integer> set = new HashSet<>();
            for(int n : nums) {
                set.add(n);
            }

            int longestConsecutiveStreak = 1;
            int currentConsecutiveStreak = 1;

            for(int i = 0; i < nums.length; i++) {
                int number = nums[i];

                if(set.contains(number - 1)) {
                    continue;
                }

                currentConsecutiveStreak = 1;
                while(set.contains(++number)) {
                    currentConsecutiveStreak++;
                }

                longestConsecutiveStreak = Math.max(longestConsecutiveStreak, currentConsecutiveStreak);
            }

            return longestConsecutiveStreak;
        }

        public int longestConsecutive_Sorting(int[] nums) {
            if(nums == null || nums.length == 0) {
                return 0;
            }

            Arrays.sort(nums);

            int longestConsecutiveStreak = 1;
            int currentConsecutiveStreak = 1;

            int n = nums.length;

            for(int i = 1; i < n; i++) {
                if(nums[i] == nums[i - 1]) {
                    continue;
                }

                if(nums[i] == nums[i - 1] + 1) {
                    currentConsecutiveStreak++;
                } else {
                    longestConsecutiveStreak = Math.max(longestConsecutiveStreak, currentConsecutiveStreak);
                    currentConsecutiveStreak = 1;
                }
            }

            return Math.max(longestConsecutiveStreak, currentConsecutiveStreak);
        }

        public int longestConsecutive_Brute(int[] nums) {
            if(nums == null || nums.length == 0) {
                return 0;
            }

            int longestConsecutiveSequenceLength = 0;

            for(int i = 0; i < nums.length; i++) {
                int number = nums[i];
                int currentConsecutiveSequenceLength = 1;

                while(containsNumber(nums, ++number)) {
                    currentConsecutiveSequenceLength++;
                }

                longestConsecutiveSequenceLength = Math.max(longestConsecutiveSequenceLength, currentConsecutiveSequenceLength);
            }

            return longestConsecutiveSequenceLength;
        }

        private boolean containsNumber(int[] nums, int number) {
            for(int i = 0; i < nums.length; i++) {
                if(nums[i] == number) {
                    return true;
                }
            }

            return false;
        }
    }
}

//    128. Longest Consecutive Sequence
//    Medium
//    Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence.
//
//    You must write an algorithm that runs in O(n) time.
//
//
//
//    Example 1:
//
//    Input: nums = [100,4,200,1,3,2]
//    Output: 4
//    Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.
//    Example 2:
//
//    Input: nums = [0,3,7,2,5,8,4,6,0,1]
//    Output: 9
//
//
//    Constraints:
//
//    0 <= nums.length <= 105
//    -109 <= nums[i] <= 109