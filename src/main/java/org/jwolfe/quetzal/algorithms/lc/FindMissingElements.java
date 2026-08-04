package org.jwolfe.quetzal.algorithms.lc;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FindMissingElements {
    class Solution {
        public List<Integer> findMissingElements(int[] nums) {
            List<Integer> ans = new ArrayList<>();
            if (nums == null || nums.length == 0) {
                return ans;
            }

            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;
            Set<Integer> set = new HashSet<>();

            for (int val : nums) {
                min = Math.min(min, val);
                max = Math.max(max, val);
                set.add(val);
            }

            for (int val = min + 1; val < max; val++) {
                if (!set.contains(val)) {
                    ans.add(val);
                }
            }

            return ans;
        }
    }
}

//    3731. Find Missing Elements
//    Solved
//    Easy
//    Topics
//    premium lock icon
//    Companies
//    Hint
//    You are given an integer array nums consisting of unique integers.
//
//    Originally, nums contained every integer within a certain range. However, some integers might have gone missing from the array.
//
//    The smallest and largest integers of the original range are still present in nums.
//
//    Return a sorted list of all the missing integers in this range. If no integers are missing, return an empty list.
//
//
//
//    Example 1:
//
//    Input: nums = [1,4,2,5]
//
//    Output: [3]
//
//    Explanation:
//
//    The smallest integer is 1 and the largest is 5, so the full range should be [1,2,3,4,5]. Among these, only 3 is missing.
//
//    Example 2:
//
//    Input: nums = [7,8,6,9]
//
//    Output: []
//
//    Explanation:
//
//    The smallest integer is 6 and the largest is 9, so the full range is [6,7,8,9]. All integers are already present, so no integer is missing.
//
//    Example 3:
//
//    Input: nums = [5,1]
//
//    Output: [2,3,4]
//
//    Explanation:
//
//    The smallest integer is 1 and the largest is 5, so the full range should be [1,2,3,4,5]. The missing integers are 2, 3, and 4.
//
//
//
//    Constraints:
//
//    2 <= nums.length <= 100
//    1 <= nums[i] <= 100