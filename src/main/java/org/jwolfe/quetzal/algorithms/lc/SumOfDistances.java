package org.jwolfe.quetzal.algorithms.lc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SumOfDistances {
    class Solution {
        public long[] distance(int[] nums) {
            if (nums == null || nums.length == 0) {
                return new long[0];
            }

            int n = nums.length;
            long[] results = new long[n];

            Map<Integer, List<Integer>> indicesForValues = new HashMap<>();
            for (int i = 0; i < n; i++) {
                int val = nums[i];

                if (!indicesForValues.containsKey(val)) {
                    indicesForValues.put(val, new ArrayList<>());
                }

                indicesForValues.get(val).add(i);
            }

            for (List<Integer> indices : indicesForValues.values()) {
                int size = indices.size();

                long total = 0;
                for (int index : indices) {
                    total += index;
                }

                long prefixSum = 0;
                for (int i = 0; i < size; i++) {
                    int index = indices.get(i);
                    results[index] = total - 2 * prefixSum + 1L * index * (2 * i - size);

                    prefixSum += index;
                }
            }

            return results;
        }
    }

    class Solution_TLE {
        public long[] distance(int[] nums) {
            if (nums == null || nums.length == 0) {
                return new long[0];
            }

            int n = nums.length;
            long[] results = new long[n];

            Map<Integer, List<Integer>> indicesForValues = new HashMap<>();
            for (int i = 0; i < n; i++) {
                int val = nums[i];

                if (!indicesForValues.containsKey(val)) {
                    indicesForValues.put(val, new ArrayList<>());
                }

                indicesForValues.get(val).add(i);
            }

            for (List<Integer> indices : indicesForValues.values()) {
                int size = indices.size();
                for (int i = 0; i < size; i++) {
                    int index = indices.get(i);

                    long distanceSum = 0;
                    for (int j = 0; j < size; j++) {
                        distanceSum += Math.abs(indices.get(j) - index);
                    }

                    results[index] = distanceSum;
                }
            }

            return results;
        }
    }
}

//    2615. Sum of Distances
//    Medium
//    You are given a 0-indexed integer array nums. There exists an array arr of length nums.length, where arr[i] is the sum of |i - j| over all j such that nums[j] == nums[i] and j != i. If there is no such j, set arr[i] to be 0.
//
//    Return the array arr.
//
//
//
//    Example 1:
//
//    Input: nums = [1,3,1,1,2]
//    Output: [5,0,3,4,0]
//    Explanation:
//    When i = 0, nums[0] == nums[2] and nums[0] == nums[3]. Therefore, arr[0] = |0 - 2| + |0 - 3| = 5.
//    When i = 1, arr[1] = 0 because there is no other index with value 3.
//    When i = 2, nums[2] == nums[0] and nums[2] == nums[3]. Therefore, arr[2] = |2 - 0| + |2 - 3| = 3.
//    When i = 3, nums[3] == nums[0] and nums[3] == nums[2]. Therefore, arr[3] = |3 - 0| + |3 - 2| = 4.
//    When i = 4, arr[4] = 0 because there is no other index with value 2.
//
//    Example 2:
//
//    Input: nums = [0,5,3]
//    Output: [0,0,0]
//    Explanation: Since each element in nums is distinct, arr[i] = 0 for all i.
//
//
//    Constraints:
//
//    1 <= nums.length <= 105
//    0 <= nums[i] <= 109
//
//
//    Note: This question is the same as 2121: Intervals Between Identical Elements.