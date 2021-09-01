package org.jwolfe.quetzal.algorithms.lc;

import java.util.HashSet;
import java.util.Set;

public class ArrayNesting {
    class Solution {
        public int arrayNesting(int[] nums) {
            if(nums == null || nums.length == 0) {
                return 0;
            }

            int n = nums.length;
            int maxLength = 0;

            boolean[] visited = new boolean[n];
            for(int i = 0; i < n; i++) {
                if(visited[i]) {
                    continue;
                }

                int length = getLengthOfNestedArray(nums, n, i, visited);
                maxLength = Math.max(maxLength, length);
            }

            return maxLength;
        }

        private int getLengthOfNestedArray(int[] nums, int n, int index, boolean[] visited) {
            Set<Integer> set = new HashSet<>();

            while(!set.contains(nums[index])) {
                visited[index] = true;
                set.add(nums[index]);
                index = nums[index];
            }

            return set.size();
        }
    }

    class Solution_Brute {
        public int arrayNesting(int[] nums) {
            if(nums == null || nums.length == 0) {
                return 0;
            }

            int n = nums.length;
            int maxLength = 0;

            for(int i = 0; i < n; i++) {
                int length = getLengthOfNestedArray(nums, n, i);
                maxLength = Math.max(maxLength, length);
            }

            return maxLength;
        }

        private int getLengthOfNestedArray(int[] nums, int n, int index) {
            Set<Integer> set = new HashSet<>();

            while(!set.contains(nums[index])) {
                set.add(nums[index]);
                index = nums[index];
            }

            return set.size();
        }
    }

// [5,4,0,3,1,6,2]
}

//    565. Array Nesting
//    Medium
//    You are given an integer array nums of length n where nums is a permutation of the numbers in the range [0, n - 1].
//
//    You should build a set s[k] = {nums[k], nums[nums[k]], nums[nums[nums[k]]], ... } subjected to the following rule:
//
//    The first element in s[k] starts with the selection of the element nums[k] of index = k.
//    The next element in s[k] should be nums[nums[k]], and then nums[nums[nums[k]]], and so on.
//    We stop adding right before a duplicate element occurs in s[k].
//    Return the longest length of a set s[k].
//
//
//
//    Example 1:
//
//    Input: nums = [5,4,0,3,1,6,2]
//    Output: 4
//    Explanation:
//    nums[0] = 5, nums[1] = 4, nums[2] = 0, nums[3] = 3, nums[4] = 1, nums[5] = 6, nums[6] = 2.
//    One of the longest sets s[k]:
//    s[0] = {nums[0], nums[5], nums[6], nums[2]} = {5, 6, 2, 0}
//    Example 2:
//
//    Input: nums = [0,1,2]
//    Output: 1
//
//
//    Constraints:
//
//    1 <= nums.length <= 105
//    0 <= nums[i] < nums.length
//    All the values of nums are unique.