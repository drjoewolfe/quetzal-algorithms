package org.jwolfe.quetzal.algorithms.lc;

public class RearrangeArrayElementsBySign {
    class Solution {
        public int[] rearrangeArray(int[] nums) {
            if(nums == null || nums.length == 0 || nums.length % 2 != 0) {
                return nums;
            }

            int n = nums.length;

            int[] results = new int[n];
            int pi = 0;
            int ni = 1;

            for(int i = 0; i < n; i++) {
                int val = nums[i];
                if(val > 0) {
                    results[pi] = val;
                    pi += 2;
                } else {
                    results[ni] = val;
                    ni += 2;
                }
            }

            return results;
        }
    }
}

//    2149. Rearrange Array Elements by Sign
//    Medium
//    You are given a 0-indexed integer array nums of even length consisting of an equal number of positive and negative integers.
//
//    You should rearrange the elements of nums such that the modified array follows the given conditions:
//
//    Every consecutive pair of integers have opposite signs.
//    For all integers with the same sign, the order in which they were present in nums is preserved.
//    The rearranged array begins with a positive integer.
//    Return the modified array after rearranging the elements to satisfy the aforementioned conditions.
//
//
//
//    Example 1:
//
//    Input: nums = [3,1,-2,-5,2,-4]
//    Output: [3,-2,1,-5,2,-4]
//    Explanation:
//    The positive integers in nums are [3,1,2]. The negative integers are [-2,-5,-4].
//    The only possible way to rearrange them such that they satisfy all conditions is [3,-2,1,-5,2,-4].
//    Other ways such as [1,-2,2,-5,3,-4], [3,1,2,-2,-5,-4], [-2,3,-5,1,-4,2] are incorrect because they do not satisfy one or more conditions.
//    Example 2:
//
//    Input: nums = [-1,1]
//    Output: [1,-1]
//    Explanation:
//    1 is the only positive integer and -1 the only negative integer in nums.
//    So nums is rearranged to [1,-1].
//
//
//    Constraints:
//
//    2 <= nums.length <= 2 * 105
//    nums.length is even
//    1 <= |nums[i]| <= 105
//    nums consists of equal number of positive and negative integers.