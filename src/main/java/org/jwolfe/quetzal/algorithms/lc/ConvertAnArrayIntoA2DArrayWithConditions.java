package org.jwolfe.quetzal.algorithms.lc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConvertAnArrayIntoA2DArrayWithConditions {
    class Solution {
        public List<List<Integer>> findMatrix(int[] nums) {
            List<List<Integer>> results = new ArrayList<>();
            if(nums == null || nums.length == 0) {
                return results;
            }

            int n = nums.length;
            int[] frequencies = new int[n + 1];
            for(int a : nums) {
                int frequency = ++frequencies[a];

                if(frequency > results.size()) {
                    results.add(new ArrayList<>());
                }

                results.get(frequency - 1).add(a);
            }

            return results;
        }
    }

    class Solution_Correct_1 {
        public List<List<Integer>> findMatrix(int[] nums) {
            List<List<Integer>> results = new ArrayList<>();
            if(nums == null || nums.length == 0) {
                return results;
            }

            Map<Integer, Integer> countMap = new HashMap<>();
            int maxCount = 0;
            for(int a : nums) {
                countMap.put(a, countMap.getOrDefault(a, 0) + 1);
                maxCount = Math.max(maxCount, countMap.get(a));
            }

            for(int i = 0; i < maxCount; i++) {
                results.add(new ArrayList<>());
            }

            for(var entry : countMap.entrySet()) {
                int key = entry.getKey();
                int count = entry.getValue();

                for(int i = 0; i < count; i++) {
                    results.get(i).add(key);
                }
            }

            return results;
        }
    }
}

//    2610. Convert an Array Into a 2D Array With Conditions
//    Medium
//    You are given an integer array nums. You need to create a 2D array from nums satisfying the following conditions:
//
//    The 2D array should contain only the elements of the array nums.
//    Each row in the 2D array contains distinct integers.
//    The number of rows in the 2D array should be minimal.
//    Return the resulting array. If there are multiple answers, return any of them.
//
//    Note that the 2D array can have a different number of elements on each row.
//
//
//
//    Example 1:
//
//    Input: nums = [1,3,4,1,2,3,1]
//    Output: [[1,3,4,2],[1,3],[1]]
//    Explanation: We can create a 2D array that contains the following rows:
//    - 1,3,4,2
//    - 1,3
//    - 1
//    All elements of nums were used, and each row of the 2D array contains distinct integers, so it is a valid answer.
//    It can be shown that we cannot have less than 3 rows in a valid array.
//    Example 2:
//
//    Input: nums = [1,2,3,4]
//    Output: [[4,3,2,1]]
//    Explanation: All elements of the array are distinct, so we can keep all of them in the first row of the 2D array.
//
//
//    Constraints:
//
//    1 <= nums.length <= 200
//    1 <= nums[i] <= nums.length