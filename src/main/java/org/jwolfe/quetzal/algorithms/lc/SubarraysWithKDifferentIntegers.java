package org.jwolfe.quetzal.algorithms.lc;

import java.util.HashMap;
import java.util.Map;

public class SubarraysWithKDifferentIntegers {
    class Solution {
        public int subarraysWithKDistinct(int[] nums, int k) {
            if(nums == null || k < 1 || nums.length < k) {
                return 0;
            }

            return subarraysWithUtmostKDistinct(nums, k)
                    - subarraysWithUtmostKDistinct(nums, k - 1);
        }

        public int subarraysWithUtmostKDistinct(int[] nums, int k) {
            int count = 0;

            int n = nums.length;
            int left = 0;
            int right = 0;
            Map<Integer, Integer> map = new HashMap<>();

            for(; right < n; right++) {
                int element = nums[right];
                map.put(element, map.getOrDefault(element, 0) + 1);

                while(map.size() > k) {
                    int leftElement = nums[left];
                    int leftElementCount = map.get(leftElement);

                    if(leftElementCount == 1) {
                        map.remove(leftElement);
                    } else {
                        map.put(leftElement, leftElementCount - 1);
                    }

                    left++;
                }

                count += (right - left + 1);
            }

            return count;
        }
    }
}

//    992. Subarrays with K Different Integers
//    Hard
//    Given an integer array nums and an integer k, return the number of good subarrays of nums.
//
//    A good array is an array where the number of different integers in that array is exactly k.
//
//    For example, [1,2,3,1,2] has 3 different integers: 1, 2, and 3.
//    A subarray is a contiguous part of an array.
//
//
//
//    Example 1:
//
//    Input: nums = [1,2,1,2,3], k = 2
//    Output: 7
//    Explanation: Subarrays formed with exactly 2 different integers: [1,2], [2,1], [1,2], [2,3], [1,2,1], [2,1,2], [1,2,1,2]
//    Example 2:
//
//    Input: nums = [1,2,1,3,4], k = 3
//    Output: 3
//    Explanation: Subarrays formed with exactly 3 different integers: [1,2,1,3], [2,1,3], [1,3,4].
//
//
//    Constraints:
//
//    1 <= nums.length <= 2 * 104
//    1 <= nums[i], k <= nums.length