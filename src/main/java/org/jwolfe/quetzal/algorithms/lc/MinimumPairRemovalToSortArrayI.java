package org.jwolfe.quetzal.algorithms.lc;

import java.util.ArrayList;
import java.util.List;

public class MinimumPairRemovalToSortArrayI {
    class Solution {
        public int minimumPairRemoval(int[] nums) {
            if (nums == null || nums.length < 2) {
                return 0;
            }

            List<Integer> list = new ArrayList<>();
            for (int x : nums) {
                list.add(x);
            }

            int minOperations = 0;
            while (list.size() > 1) {
                boolean isAscending = true;

                int minSum = Integer.MAX_VALUE;
                int minSumIndex = -1;

                for (int i = 0; i < list.size() - 1; i++) {
                    int a = list.get(i);
                    int b = list.get(i + 1);

                    if (a > b) {
                        isAscending = false;
                    }

                    int sum = a + b;
                    if (sum < minSum) {
                        minSum = sum;
                        minSumIndex = i;
                    }
                }

                if (isAscending) {
                    break;
                }

                minOperations++;
                list.set(minSumIndex, minSum);
                list.remove(minSumIndex + 1);
            }

            return minOperations;
        }
    }
}

//    3507. Minimum Pair Removal to Sort Array I
//    Easy
//    Given an array nums, you can perform the following operation any number of times:
//
//    Select the adjacent pair with the minimum sum in nums. If multiple such pairs exist, choose the leftmost one.
//    Replace the pair with their sum.
//    Return the minimum number of operations needed to make the array non-decreasing.
//
//    An array is said to be non-decreasing if each element is greater than or equal to its previous element (if it exists).
//
//
//
//    Example 1:
//
//    Input: nums = [5,2,3,1]
//
//    Output: 2
//
//    Explanation:
//
//    The pair (3,1) has the minimum sum of 4. After replacement, nums = [5,2,4].
//    The pair (2,4) has the minimum sum of 6. After replacement, nums = [5,6].
//    The array nums became non-decreasing in two operations.
//
//    Example 2:
//
//    Input: nums = [1,2,2]
//
//    Output: 0
//
//    Explanation:
//
//    The array nums is already sorted.
//
//
//
//    Constraints:
//
//    1 <= nums.length <= 50
//    -1000 <= nums[i] <= 1000