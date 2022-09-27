package org.jwolfe.quetzal.algorithms.lc;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class IntersectionOfMultipleArrays {
    class Solution {
        public List<Integer> intersection(int[][] nums) {
            List<Integer> results = new ArrayList<>();
            if(nums == null || nums.length == 0 || nums[0].length == 0) {
                return results;
            }

            Map<Integer, Integer> map = new TreeMap<>();
            for(int[] row : nums) {
                for(int val : row) {
                    map.put(val, map.getOrDefault(val, 0) + 1);
                }
            }

            int n = nums.length;
            for(var entry : map.entrySet()) {
                if(entry.getValue() == n) {
                    results.add(entry.getKey());
                }
            }

            return results;
        }
    }
}

//    2248. Intersection of Multiple Arrays
//    Easy
//    Given a 2D integer array nums where nums[i] is a non-empty array of distinct positive integers, return the list of integers that are present in each array of nums sorted in ascending order.
//
//
//    Example 1:
//
//    Input: nums = [[3,1,2,4,5],[1,2,3,4],[3,4,5,6]]
//    Output: [3,4]
//    Explanation:
//    The only integers present in each of nums[0] = [3,1,2,4,5], nums[1] = [1,2,3,4], and nums[2] = [3,4,5,6] are 3 and 4, so we return [3,4].
//    Example 2:
//
//    Input: nums = [[1,2,3],[4,5,6]]
//    Output: []
//    Explanation:
//    There does not exist any integer present both in nums[0] and nums[1], so we return an empty list [].
//
//
//    Constraints:
//
//    1 <= nums.length <= 1000
//    1 <= sum(nums[i].length) <= 1000
//    1 <= nums[i][j] <= 1000
//    All the values of nums[i] are unique.
