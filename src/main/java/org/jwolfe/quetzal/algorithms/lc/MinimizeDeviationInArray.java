package org.jwolfe.quetzal.algorithms.lc;

import java.util.PriorityQueue;
import java.util.TreeSet;

public class MinimizeDeviationInArray {
    class Solution {
        public int minimumDeviation(int[] nums) {
            if(nums == null || nums.length < 2) {
                return 0;
            }

            PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);
            int min = Integer.MAX_VALUE;
            for(int val : nums) {
                if(val % 2 != 0) {
                    val = val * 2;
                }

                maxHeap.offer(val);
                min = Math.min(min, val);
            }

            int minDeviation = Integer.MAX_VALUE;
            while(maxHeap.size() > 1) {
                int max = maxHeap.poll();
                minDeviation = Math.min(minDeviation, max - min);

                if(max % 2 != 0) {
                    break;
                }

                max /= 2;
                min = Math.min(min, max);
                maxHeap.offer(max);
            }

            return minDeviation;
        }
    }

    class Solution_Correct_1 {
        public int minimumDeviation(int[] nums) {
            if(nums == null || nums.length < 2) {
                return 0;
            }

            int n = nums.length;
            TreeSet<Integer> set = new TreeSet<>();
            for(int i = 0; i < n; i++) {
                int element = nums[i];
                if(element % 2 == 0) {
                    set.add(element);
                } else {
                    set.add(element * 2);
                }
            }

            int minDiff = Integer.MAX_VALUE;
            while(true) {
                int min = set.first();
                int max = set.last();

                int diff = max - min;
                minDiff = Math.min(minDiff, diff);


                if(max % 2 == 0) {
                    set.remove(max);

                    max /= 2;
                    set.add(max);
                } else {
                    break;
                }
            }

            return minDiff;
        }
    }

// [1,2,3,4]
}

//    1675. Minimize Deviation in Array
//    Hard
//    You are given an array nums of n positive integers.
//
//    You can perform two types of operations on any element of the array any number of times:
//
//    If the element is even, divide it by 2.
//    For example, if the array is [1,2,3,4], then you can do this operation on the last element, and the array will be [1,2,3,2].
//    If the element is odd, multiply it by 2.
//    For example, if the array is [1,2,3,4], then you can do this operation on the first element, and the array will be [2,2,3,4].
//    The deviation of the array is the maximum difference between any two elements in the array.
//
//    Return the minimum deviation the array can have after performing some number of operations.
//
//
//
//    Example 1:
//
//    Input: nums = [1,2,3,4]
//    Output: 1
//    Explanation: You can transform the array to [1,2,3,2], then to [2,2,3,2], then the deviation will be 3 - 2 = 1.
//    Example 2:
//
//    Input: nums = [4,1,5,20,3]
//    Output: 3
//    Explanation: You can transform the array after two operations to [4,2,5,5,3], then the deviation will be 5 - 2 = 3.
//    Example 3:
//
//    Input: nums = [2,10,8]
//    Output: 3
//
//
//    Constraints:
//
//    n == nums.length
//    2 <= n <= 5 * 104
//    1 <= nums[i] <= 109