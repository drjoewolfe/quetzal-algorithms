package org.jwolfe.quetzal.algorithms.lc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CountEqualAndDivisiblePairsInAnArray {
    class Solution {
        public int countPairs(int[] nums, int k) {
            if(nums == null || nums.length < 2) {
                return 0;
            }

            int n = nums.length;
            int count = 0;
            for(int i = 0; i < n; i++) {
                for(int j = i + 1; j < n; j++) {
                    if(nums[i] == nums[j]
                            && (i * j % k == 0)) {
                        count++;
                    }
                }
            }

            return count;
        }
    }

    class Solution_Map {
        public int countPairs(int[] nums, int k) {
            if(nums == null || nums.length < 2) {
                return 0;
            }

            Map<Integer, List<Integer>> map = new HashMap<>();
            for(int i = 0; i < nums.length; i++) {
                int val = nums[i];

                if(!map.containsKey(val)) {
                    map.put(val, new ArrayList<>());
                }

                map.get(val).add(i);
            }

            int count = 0;
            for(var entry : map.entrySet()) {
                List<Integer> indices = entry.getValue();

                for(int i = 0; i < indices.size(); i++) {
                    int index1 = indices.get(i);
                    for(int j = i + 1; j < indices.size(); j++) {
                        int index2 = indices.get(j);

                        if(index1 * index2 % k == 0) {
                            count++;
                        }
                    }
                }
            }

            return count;
        }
    }
}

//    2176. Count Equal and Divisible Pairs in an Array
//    Easy
//    Given a 0-indexed integer array nums of length n and an integer k, return the number of pairs (i, j) where 0 <= i < j < n, such that nums[i] == nums[j] and (i * j) is divisible by k.
//
//
//    Example 1:
//
//    Input: nums = [3,1,2,2,2,1,3], k = 2
//    Output: 4
//    Explanation:
//    There are 4 pairs that meet all the requirements:
//    - nums[0] == nums[6], and 0 * 6 == 0, which is divisible by 2.
//    - nums[2] == nums[3], and 2 * 3 == 6, which is divisible by 2.
//    - nums[2] == nums[4], and 2 * 4 == 8, which is divisible by 2.
//    - nums[3] == nums[4], and 3 * 4 == 12, which is divisible by 2.
//    Example 2:
//
//    Input: nums = [1,2,3,4], k = 1
//    Output: 0
//    Explanation: Since no value in nums is repeated, there are no pairs (i,j) that meet all the requirements.
//
//
//    Constraints:
//
//    1 <= nums.length <= 100
//    1 <= nums[i], k <= 100