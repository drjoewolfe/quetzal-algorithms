package org.jwolfe.quetzal.algorithms.lc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MajorityElementII {
    class Solution {
        public List<Integer> majorityElement(int[] nums) {
            List<Integer> answer = new ArrayList<>();

            if(nums == null || nums.length == 0) {
                return answer;
            }

            int target = nums.length / 3;

            int candidate1 = Integer.MAX_VALUE;
            int count1 = 0;
            int candidate2 = Integer.MAX_VALUE;
            int count2 = 0;

            for(int a : nums) {
                if(count1 == 0 && a != candidate2) {
                    candidate1 = a;
                    count1 = 1;
                } else if(count2 == 0 && a != candidate1) {
                    candidate2 = a;
                    count2 = 1;
                } else if(a == candidate1) {
                    count1++;
                } else if(a == candidate2) {
                    count2++;
                } else {
                    count1--;
                    count2--;
                }
            }

            count1 = 0;
            count2 = 0;
            for(int a : nums) {
                if(a == candidate1) {
                    count1++;
                } else if(a == candidate2) {
                    count2++;
                }
            }


            if(count1 > target) {
                answer.add(candidate1);
            }

            if(count2 > target) {
                answer.add(candidate2);
            }

            return answer;
        }
    }

    class Solution_Correct_1 {
        public List<Integer> majorityElement(int[] nums) {
            List<Integer> elements = new ArrayList<>();

            if(nums == null || nums.length == 0) {
                return elements;
            }

            int n = nums.length;
            int target = n / 3;

            int candidate1 = -1;
            int count1 = 0;

            int candidate2 = -1;
            int count2 = 0;

            for(int a : nums) {
                if(a == candidate1) {
                    count1++;
                } else if(a == candidate2) {
                    count2++;
                } else if(count1 == 0) {
                    candidate1 = a;
                    count1 = 1;
                } else if(count2 == 0) {
                    candidate2 = a;
                    count2 = 1;
                } else {
                    count1--;
                    count2--;
                }
            }

            count1 = 0;
            count2 = 0;
            for(int a : nums) {
                if(a == candidate1) {
                    count1++;
                } else if(a == candidate2) {
                    count2++;
                }
            }

            if(count1 > target) {
                elements.add(candidate1);
            }

            if(count2 > target) {
                elements.add(candidate2);
            }

            return elements;
        }
    }

    class Solution_WithSpace {
        public List<Integer> majorityElement(int[] nums) {
            List<Integer> elements = new ArrayList<>();

            if(nums == null || nums.length == 0) {
                return elements;
            }

            Map<Integer, Integer> frequencies = new HashMap<>();
            for(int a : nums) {
                frequencies.put(a, frequencies.getOrDefault(a, 0) + 1);
            }

            int n = nums.length;
            int target = n / 3;

            for(var entry : frequencies.entrySet()) {
                if(entry.getValue() > target) {
                    elements.add(entry.getKey());
                }
            }

            return elements;
        }
    }

// [3,2,3]
}

//    229. Majority Element II
//
//    Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times.
//
//    Follow-up: Could you solve the problem in linear time and in O(1) space?
//
//
//
//    Example 1:
//
//    Input: nums = [3,2,3]
//    Output: [3]
//    Example 2:
//
//    Input: nums = [1]
//    Output: [1]
//    Example 3:
//
//    Input: nums = [1,2]
//    Output: [1,2]
//
//
//    Constraints:
//
//    1 <= nums.length <= 5 * 104
//    -109 <= nums[i] <= 109