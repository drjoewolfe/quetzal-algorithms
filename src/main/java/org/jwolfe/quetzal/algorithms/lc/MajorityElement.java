package org.jwolfe.quetzal.algorithms.lc;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MajorityElement {
    class Solution {
        public int majorityElement(int[] nums) {
            if(nums == null || nums.length == 0) {
                return -1;
            }

            int n = nums.length;
            int candidate = nums[0];
            int count = 1;

            for(int i = 1; i < n; i++) {
                int val = nums[i];

                if(candidate == val) {
                    count++;
                } else if(count == 0) {
                    candidate = val;
                    count = 1;
                } else {
                    count--;
                }
            }

            count = 0;
            for(int i = 0; i < n; i++) {
                if(nums[i] == candidate) {
                    count++;
                }
            }

            return count >= (n / 2) ? candidate : -1;
        }
    }

    class Solution_Correct_2 {
        public int majorityElement(int[] nums) {
            if(nums == null || nums.length == 0) {
                return -1;
            }

            int n = nums.length;
            int candidate = nums[0];
            int count = 1;
            for(int i = 1; i < n; i++) {
                int current = nums[i];
                if(current == candidate) {
                    count++;
                } else {
                    count--;
                    if(count == 0) {
                        candidate = current;
                        count = 1;
                    }
                }
            }

            count = 0;
            for(int i = 0; i < n; i++) {
                if(nums[i] == candidate) {
                    count++;
                }
            }

            return count >= (n / 2) ? candidate : -1;
        }
    }

    class Solution_Correct_1 {
        public int majorityElement(int[] nums) {
            if(nums == null || nums.length == 0) {
                return -1;
            }

            int length = nums.length;

            // Find Candidate
            int candidate = 0;
            int candidateCount = 0;
            for(int i = 0; i < length; i++) {
                if(candidateCount == 0) {
                    candidate = nums[i];
                    candidateCount = 1;
                } else if(nums[i] == candidate) {
                    candidateCount++;
                } else {
                    candidateCount--;
                }
            }

            // Verify Candidate
            candidateCount = 0;
            for(int i = 0; i < length; i++) {
                if(nums[i] == candidate) {
                    candidateCount++;
                }
            }

            int majorityCount = length % 2 == 0 ? length / 2: length / 2 + 1;

            return candidateCount >= majorityCount ? candidate : -1;
        }
    }

    class Solution_Sorting {
        public int majorityElement(int[] nums) {
            if(nums == null || nums.length == 0) {
                return -1;
            }

            Arrays.sort(nums);
            return nums[nums.length / 2];
        }
    }

    class Solution_Map {
        public int majorityElement(int[] nums) {
            if(nums == null) {
                return -1;
            }

            Map<Integer, Integer> map = new HashMap<>();

            for(int i = 0; i < nums.length; i++) {
                int num = nums[i];
                map.put(num, map.getOrDefault(num, 0) + 1);
            }

            int length = nums.length;
            int majorityCount = length % 2 == 0 ? length / 2: length / 2 + 1;
            for(int num : map.keySet()) {
                if(map.get(num) >= majorityCount) {
                    return num;
                }
            }

            return -1;
        }
    }

// [3,2,3]
// [3,3,4]
}

//    169. Majority Element
//    Easy
//    Given an array nums of size n, return the majority element.
//
//    The majority element is the element that appears more than ⌊n / 2⌋ times. You may assume that the majority element always exists in the array.
//
//
//
//    Example 1:
//
//    Input: nums = [3,2,3]
//    Output: 3
//    Example 2:
//
//    Input: nums = [2,2,1,1,1,2,2]
//    Output: 2
//
//
//    Constraints:
//
//    n == nums.length
//    1 <= n <= 5 * 104
//    -231 <= nums[i] <= 231 - 1
//
//
//    Follow-up: Could you solve the problem in linear time and in O(1) space?
