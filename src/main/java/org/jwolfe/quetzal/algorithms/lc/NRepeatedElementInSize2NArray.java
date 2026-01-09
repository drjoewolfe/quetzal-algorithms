package org.jwolfe.quetzal.algorithms.lc;

public class NRepeatedElementInSize2NArray {
    class Solution {
        public int repeatedNTimes(int[] nums) {
            for (int i = 0; i < nums.length - 1; i++) {
                if (nums[i] == nums[i + 1]
                        || (i + 2 < nums.length && nums[i] == nums[i + 2])
                        || (i + 3 < nums.length && nums[i] == nums[i + 3])) {
                    return nums[i];
                }
            }

            return -1;
        }
    }

    class Solution_Incorrect {
        public int repeatedNTimes(int[] nums) {
            int candidate = nums[0];
            int count = 1;

            for (int i = 1; i < nums.length; i++) {
                if (nums[i] == candidate) {
                    count++;
                } else {
                    count--;
                    if (count == 0) {
                        candidate = nums[i];
                        count = 1;
                    }
                }
            }

            return candidate;
        }
    }
}

//    961. N-Repeated Element in Size 2N Array
//    Easy
//    You are given an integer array nums with the following properties:
//
//    nums.length == 2 * n.
//    nums contains n + 1 unique elements.
//    Exactly one element of nums is repeated n times.
//    Return the element that is repeated n times.
//
//
//
//    Example 1:
//
//    Input: nums = [1,2,3,3]
//    Output: 3
//    Example 2:
//
//    Input: nums = [2,1,2,5,3,2]
//    Output: 2
//    Example 3:
//
//    Input: nums = [5,1,5,2,5,3,5,4]
//    Output: 5
//
//
//    Constraints:
//
//    2 <= n <= 5000
//    nums.length == 2 * n
//    0 <= nums[i] <= 104
//    nums contains n + 1 unique elements and one of them is repeated exactly n times.