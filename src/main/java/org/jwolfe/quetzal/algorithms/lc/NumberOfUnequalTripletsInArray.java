package org.jwolfe.quetzal.algorithms.lc;

import java.util.HashMap;
import java.util.Map;

public class NumberOfUnequalTripletsInArray {
    class Solution {
        public int unequalTriplets(int[] nums) {
            if(nums == null || nums.length < 3) {
                return 0;
            }

            int n = nums.length;
            Map<Integer, Integer> map = new HashMap<>();
            for(int a : nums) {
                map.put(a, map.getOrDefault(a, 0) + 1);
            }

            if(map.size() < 3) {
                return 0;
            }

            int answer = 0;
            int left = 0;
            int right = nums.length;
            for(var entry : map.entrySet()) {
                int count = entry.getValue();
                right -= count;
                answer += left * count * right;
                left += count;
            }

            return answer;
        }
    }

    class Solution_Correct_1 {
        public int unequalTriplets(int[] nums) {
            if(nums == null || nums.length < 3) {
                return 0;
            }

            int n = nums.length;
            int count = 0;
            for(int i = 0; i < n - 2; i++) {
                int a = nums[i];
                for(int j = i + 1; j < n - 1; j++) {
                    int b = nums[j];
                    if(a == b) {
                        continue;
                    }

                    for(int k = j + 1; k < n; k++) {
                        int c = nums[k];

                        if(a == c || b == c) {
                            continue;
                        }

                        count++;
                    }
                }
            }

            return count;
        }
    }

// [4,4,2,4,3]
}

//    2475. Number of Unequal Triplets in Array
//    Easy
//    You are given a 0-indexed array of positive integers nums. Find the number of triplets (i, j, k) that meet the following conditions:
//
//    0 <= i < j < k < nums.length
//    nums[i], nums[j], and nums[k] are pairwise distinct.
//    In other words, nums[i] != nums[j], nums[i] != nums[k], and nums[j] != nums[k].
//    Return the number of triplets that meet the conditions.
//
//
//
//    Example 1:
//
//    Input: nums = [4,4,2,4,3]
//    Output: 3
//    Explanation: The following triplets meet the conditions:
//    - (0, 2, 4) because 4 != 2 != 3
//    - (1, 2, 4) because 4 != 2 != 3
//    - (2, 3, 4) because 2 != 4 != 3
//    Since there are 3 triplets, we return 3.
//    Note that (2, 0, 4) is not a valid triplet because 2 > 0.
//    Example 2:
//
//    Input: nums = [1,1,1,1,1]
//    Output: 0
//    Explanation: No triplets meet the conditions so we return 0.
//
//
//    Constraints:
//
//    3 <= nums.length <= 100
//    1 <= nums[i] <= 1000