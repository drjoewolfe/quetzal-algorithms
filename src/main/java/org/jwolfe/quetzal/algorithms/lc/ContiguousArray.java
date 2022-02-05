package org.jwolfe.quetzal.algorithms.lc;

import java.util.HashMap;
import java.util.Map;

public class ContiguousArray {
    class Solution {
        public int findMaxLength(int[] nums) {
            if(nums == null || nums.length < 2) {
                return 0;
            }

            int n = nums.length;

            int maxLength = 0;
            Map<Integer, Integer> map = new HashMap<>();
            map.put(0, -1);

            int sum = 0;
            for(int i = 0; i < n; i++) {
                sum += (nums[i] == 0 ? -1 : 1);

                if(map.containsKey(sum)) {
                    maxLength = Math.max(maxLength, i - map.get(sum));
                } else {
                    map.put(sum, i);
                }
            }

            return maxLength;
        }
    }

    class Solution_Brute {
        public int findMaxLength(int[] nums) {
            if(nums == null || nums.length < 2) {
                return 0;
            }

            int n = nums.length;

            int maxLength = 0;
            for(int i = 0; i < n; i++) {
                int oneCount = 0;
                int zeroCount = 0;
                for(int j = i; j < n; j++) {
                    int val = nums[j];
                    if(val == 0) {
                        zeroCount++;
                    } else {
                        oneCount++;
                    }

                    if(oneCount == zeroCount) {
                        maxLength = Math.max(maxLength, j - i + 1);
                    }
                }
            }

            return maxLength;
        }
    }


    class Solution_Correct_1 {
        public int findMaxLength(int[] nums) {
            if(nums == null || nums.length == 0) {
                return 0;
            }

            Map<Integer, Integer> map = new HashMap<>();

            int maxLength = 0;
            int runningSum = 0;
            map.put(0, -1);

            for(int i = 0; i < nums.length; i++) {
                runningSum += (nums[i] == 0) ? -1 : 1;

                if(map.containsKey(runningSum)) {
                    int startIndex = map.get(runningSum);
                    maxLength = Math.max(maxLength, i - startIndex);
                } else {
                    map.put(runningSum, i);
                }
            }

            return maxLength;
        }
    }
}

//    525. Contiguous Array
//    Medium
//    Given a binary array nums, return the maximum length of a contiguous subarray with an equal number of 0 and 1.
//
//
//
//    Example 1:
//
//    Input: nums = [0,1]
//    Output: 2
//    Explanation: [0, 1] is the longest contiguous subarray with an equal number of 0 and 1.
//    Example 2:
//
//    Input: nums = [0,1,0]
//    Output: 2
//    Explanation: [0, 1] (or [1, 0]) is a longest contiguous subarray with equal number of 0 and 1.
//
//
//    Constraints:
//
//    1 <= nums.length <= 105
//    nums[i] is either 0 or 1.
