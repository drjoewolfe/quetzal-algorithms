package org.jwolfe.quetzal.algorithms.lc;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class FindScoreOfAnArrayAfterMarkingAllElements {
    class Solution {
        public long findScore(int[] nums) {
            if (nums == null || nums.length == 0) {
                return 0;
            }

            int n = nums.length;
            PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> {
                if (a[0] == b[0]) {
                    return a[1] - b[1];
                }

                return a[0] - b[0];
            });
            for (int i = 0; i < n; i++) {
                heap.offer(new int[]{nums[i], i});
            }

            Set<Integer> marked = new HashSet<>();
            long score = 0;
            while (marked.size() < n) {
                int[] next = heap.poll();
                int index = next[1];

                if (marked.contains(index)) {
                    continue;
                }

                int val = next[0];
                score += val;

                marked.add(index);
                if (index > 0) {
                    marked.add(index - 1);
                }

                if (index < n - 1) {
                    marked.add(index + 1);
                }
            }

            return score;
        }
    }

// [2,5,6,6,10]
// [2,3,5,1,3,2]
// [2,1,3,4,5,2]
}

//    2593. Find Score of an Array After Marking All Elements
//    Medium
//    You are given an array nums consisting of positive integers.
//
//    Starting with score = 0, apply the following algorithm:
//
//    Choose the smallest integer of the array that is not marked. If there is a tie, choose the one with the smallest index.
//    Add the value of the chosen integer to score.
//    Mark the chosen element and its two adjacent elements if they exist.
//    Repeat until all the array elements are marked.
//    Return the score you get after applying the above algorithm.
//
//
//
//    Example 1:
//
//    Input: nums = [2,1,3,4,5,2]
//    Output: 7
//    Explanation: We mark the elements as follows:
//    - 1 is the smallest unmarked element, so we mark it and its two adjacent elements: [2,1,3,4,5,2].
//    - 2 is the smallest unmarked element, so we mark it and its left adjacent element: [2,1,3,4,5,2].
//    - 4 is the only remaining unmarked element, so we mark it: [2,1,3,4,5,2].
//    Our score is 1 + 2 + 4 = 7.
//    Example 2:
//
//    Input: nums = [2,3,5,1,3,2]
//    Output: 5
//    Explanation: We mark the elements as follows:
//    - 1 is the smallest unmarked element, so we mark it and its two adjacent elements: [2,3,5,1,3,2].
//    - 2 is the smallest unmarked element, since there are two of them, we choose the left-most one, so we mark the one at index 0 and its right adjacent element: [2,3,5,1,3,2].
//    - 2 is the only remaining unmarked element, so we mark it: [2,3,5,1,3,2].
//    Our score is 1 + 2 + 2 = 5.
//
//
//    Constraints:
//
//    1 <= nums.length <= 105
//    1 <= nums[i] <= 106