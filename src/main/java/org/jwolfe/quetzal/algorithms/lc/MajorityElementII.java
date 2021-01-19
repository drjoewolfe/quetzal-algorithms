package org.jwolfe.quetzal.algorithms.lc;

import java.util.ArrayList;
import java.util.List;

public class MajorityElementII {
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