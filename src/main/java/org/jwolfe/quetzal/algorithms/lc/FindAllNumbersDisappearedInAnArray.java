package org.jwolfe.quetzal.algorithms.lc;

import java.util.ArrayList;
import java.util.List;

public class FindAllNumbersDisappearedInAnArray {
    class Solution {
        public List<Integer> findDisappearedNumbers(int[] nums) {
            List<Integer> disappearedNumbers = new ArrayList<>();
            if(nums == null || nums.length == 0) {
                return disappearedNumbers;
            }

            for(int i = 0; i < nums.length; i++) {
                int val = Math.abs(nums[i]);
                if(nums[val - 1] > 0) {
                    nums[val - 1] *= -1;
                }
            }

            for(int i = 0; i < nums.length; i++) {
                if(nums[i] > 0) {
                    disappearedNumbers.add(i + 1);
                }
            }

            return disappearedNumbers;
        }
    }

    class Solution_Correct_1 {
        public List<Integer> findDisappearedNumbers(int[] nums) {
            List<Integer> results = new ArrayList<>();

            if(nums == null || nums.length == 0) {
                return results;
            }

            for(int i = 0; i < nums.length; i++) {
                int index = Math.abs(nums[i]) - 1;
                if(nums[index] > 0) {
                    nums[index] *= -1;
                }
            }

            for(int i = 0; i < nums.length; i++) {
                if(nums[i] > 0) {
                    results.add(i + 1);
                }
            }

            return results;
        }
    }
}

//    448. Find All Numbers Disappeared in an Array
//    Easy
//    Given an array nums of n integers where nums[i] is in the range [1, n], return an array of all the integers in the range [1, n] that do not appear in nums.
//
//
//
//    Example 1:
//
//    Input: nums = [4,3,2,7,8,2,3,1]
//    Output: [5,6]
//    Example 2:
//
//    Input: nums = [1,1]
//    Output: [2]
//
//
//    Constraints:
//
//    n == nums.length
//    1 <= n <= 105
//    1 <= nums[i] <= n
//
//
//    Follow up: Could you do it without extra space and in O(n) runtime? You may assume the returned list does not count as extra space.