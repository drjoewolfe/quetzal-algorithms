package org.jwolfe.quetzal.algorithms.lc;

public class CountElementsWithStrictlySmallerAndGreaterElements {
    class Solution {
        public int countElements(int[] nums) {
            if(nums == null || nums.length < 3) {
                return 0;
            }

            int n = nums.length;

            int minElement = nums[0];
            int minElementCount = 1;

            int maxElement = nums[0];
            int maxElementCount = 1;

            for(int i = 1; i < n; i++) {
                int val = nums[i];

                if(minElement > val) {
                    minElement = val;
                    minElementCount = 1;
                } else if(minElement == val) {
                    minElementCount++;
                }

                if(maxElement < val) {
                    maxElement = val;
                    maxElementCount = 1;
                } else if(maxElement == val) {
                    maxElementCount++;
                }
            }

            if(minElement == maxElement) {
                return 0;
            }

            return n - minElementCount - maxElementCount;
        }
    }
}

//    2148. Count Elements With Strictly Smaller and Greater Elements
//    Easy
//    Given an integer array nums, return the number of elements that have both a strictly smaller and a strictly greater element appear in nums.
//
//
//
//    Example 1:
//
//    Input: nums = [11,7,2,15]
//    Output: 2
//    Explanation: The element 7 has the element 2 strictly smaller than it and the element 11 strictly greater than it.
//    Element 11 has element 7 strictly smaller than it and element 15 strictly greater than it.
//    In total there are 2 elements having both a strictly smaller and a strictly greater element appear in nums.
//    Example 2:
//
//    Input: nums = [-3,3,3,90]
//    Output: 2
//    Explanation: The element 3 has the element -3 strictly smaller than it and the element 90 strictly greater than it.
//    Since there are two elements with the value 3, in total there are 2 elements having both a strictly smaller and a strictly greater element appear in nums.
//
//
//    Constraints:
//
//    1 <= nums.length <= 100
//    -105 <= nums[i] <= 105