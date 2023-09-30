package org.jwolfe.quetzal.algorithms.lc;

import java.util.Stack;

public class OneThreeTwoPattern {
    class Solution {
        public boolean find132pattern(int[] nums) {
            if(nums == null || nums.length < 3) {
                return false;
            }

            Stack<int[]> stack = new Stack<>();
            for(int i = 0; i < nums.length; i++) {
                int val = nums[i];

                if(stack.isEmpty()
                        || stack.peek()[0] >= val) {
                    stack.push(new int[] {val, val});
                } else {
                    int[] top = stack.pop();
                    if(top[1] > val) {
                        return true;
                    }

                    top[1] = val;

                    while(!stack.isEmpty()
                            && stack.peek()[1] <= val) {
                        stack.pop();
                    }

                    if(!stack.isEmpty()
                            && stack.peek()[0] < val) {
                        return true;
                    }

                    stack.push(top);
                }
            }

            return false;
        }
    }

    class Solution_Correct_1 {
        public boolean find132pattern(int[] nums) {
            if(nums == null || nums.length < 3) {
                return false;
            }

            int n = nums.length;
            Stack<MinMax> stack = new Stack<>();
            for(int i = 0; i < n; i++) {
                int val = nums[i];

                if(stack.isEmpty()
                        || stack.peek().min >= val) {
                    stack.push(new MinMax(val, val));
                } else {
                    var prev = stack.pop();
                    if(prev.max > val) {
                        return true;
                    } else {
                        prev.max = val;
                        while(!stack.isEmpty()
                                && stack.peek().max <= val) {
                            stack.pop();
                        }

                        if(!stack.isEmpty()
                                && stack.peek().min < val) {
                            return true;
                        }

                        stack.push(prev);
                    }
                }
            }

            return false;
        }

        public  class MinMax {
            int min;
            int max;

            public MinMax(int min, int max) {
                this.min = min;
                this.max = max;
            }
        }
    }

    class Solution_Brute {
        public boolean find132pattern(int[] nums) {
            if(nums == null || nums.length < 3) {
                return false;
            }

            int n = nums.length;
            for(int i = 0; i < n - 2; i++) {
                for(int j = i + 1; j < n - 1; j++) {
                    for(int k = j + 1; k < n; k++) {
                        if(nums[i] < nums[k] && nums[k] < nums[j]) {
                            return true;
                        }
                    }
                }
            }

            return false;
        }
    }

// [1,2,3,4]
// [1,0,1,-4,-3]
// [-2,1,-2]
// [4, 3, 2, 1]
}

//    456. 132 Pattern
//    Medium
//    Given an array of n integers nums, a 132 pattern is a subsequence of three integers nums[i], nums[j] and nums[k] such that i < j < k and nums[i] < nums[k] < nums[j].
//
//    Return true if there is a 132 pattern in nums, otherwise, return false.
//
//
//
//    Example 1:
//
//    Input: nums = [1,2,3,4]
//    Output: false
//    Explanation: There is no 132 pattern in the sequence.
//    Example 2:
//
//    Input: nums = [3,1,4,2]
//    Output: true
//    Explanation: There is a 132 pattern in the sequence: [1, 4, 2].
//    Example 3:
//
//    Input: nums = [-1,3,2,0]
//    Output: true
//    Explanation: There are three 132 patterns in the sequence: [-1, 3, 2], [-1, 3, 0] and [-1, 2, 0].
//
//
//    Constraints:
//
//    n == nums.length
//    1 <= n <= 2 * 105
//    -109 <= nums[i] <= 109