package org.jwolfe.quetzal.algorithms.lc;

import java.util.*;

public class MakeArrayZeroBySubtractingEqualAmounts {
    class Solution {
        public int minimumOperations(int[] nums) {
            if(nums == null || nums.length == 0) {
                return 0;
            }

            Set<Integer> elements = new HashSet<>();
            for(int a : nums) {
                if(a == 0) {
                    continue;
                }

                elements.add(a);
            }

            return elements.size();
        }
    }

    class Solution_Correct_2 {
        public int minimumOperations(int[] nums) {
            if(nums == null || nums.length == 0) {
                return 0;
            }

            Map<Integer, Integer> frequencies = new HashMap<>();
            for(int a : nums) {
                if(a == 0) {
                    continue;
                }

                frequencies.put(a, frequencies.getOrDefault(a, 0) + 1);
            }

            return frequencies.size();
        }
    }


    class Solution_Correct_1 {
        public int minimumOperations(int[] nums) {
            if(nums == null || nums.length == 0) {
                return 0;
            }

            PriorityQueue<Integer> heap = new PriorityQueue<>();
            for(int a : nums) {
                if(a != 0) {
                    heap.offer(a);
                }
            }

            int operations = 0;
            while(!heap.isEmpty()) {
                int min = heap.poll();

                for(int i = 0; i < nums.length; i++) {
                    if(nums[i] != 0) {
                        nums[i] -= min;
                    }
                }

                heap.clear();
                for(int a : nums) {
                    if(a != 0) {
                        heap.offer(a);
                    }
                }

                operations++;
            }

            return operations;
        }
    }
}

//    2357. Make Array Zero by Subtracting Equal Amounts
//    Easy
//    You are given a non-negative integer array nums. In one operation, you must:
//
//    Choose a positive integer x such that x is less than or equal to the smallest non-zero element in nums.
//    Subtract x from every positive element in nums.
//    Return the minimum number of operations to make every element in nums equal to 0.
//
//
//
//    Example 1:
//
//    Input: nums = [1,5,0,3,5]
//    Output: 3
//    Explanation:
//    In the first operation, choose x = 1. Now, nums = [0,4,0,2,4].
//    In the second operation, choose x = 2. Now, nums = [0,2,0,0,2].
//    In the third operation, choose x = 2. Now, nums = [0,0,0,0,0].
//    Example 2:
//
//    Input: nums = [0]
//    Output: 0
//    Explanation: Each element in nums is already 0 so no operations are needed.
//
//
//    Constraints:
//
//    1 <= nums.length <= 100
//    0 <= nums[i] <= 100