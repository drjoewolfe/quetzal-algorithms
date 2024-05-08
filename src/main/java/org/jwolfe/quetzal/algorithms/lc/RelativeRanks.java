package org.jwolfe.quetzal.algorithms.lc;

import java.util.PriorityQueue;

public class RelativeRanks {
    class Solution {
        public String[] findRelativeRanks(int[] nums) {
            if(nums == null || nums.length == 0) {
                return new String[0];
            }

            int n = nums.length;
            String[] ranks = new String[n];

            PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> b[0] - a[0]);
            for(int i = 0; i < n; i++) {
                queue.offer(new int[] {nums[i], i});
            }

            int rank = 1;
            while(!queue.isEmpty()) {
                int index = queue.poll()[1];

                if(rank == 1) {
                    ranks[index] = "Gold Medal";
                } else if(rank == 2) {
                    ranks[index] = "Silver Medal";
                } else if(rank == 3) {
                    ranks[index] = "Bronze Medal";
                } else {
                    ranks[index] = String.valueOf(rank);
                }

                rank++;
            }

            return ranks;
        }
    }

    class Solution_Correct_1 {
        public String[] findRelativeRanks(int[] nums) {
            if(nums == null || nums.length == 0) {
                return new String[0];
            }

            int n = nums.length;
            String[] ranks = new String[n];

            PriorityQueue<NumberWithIndex> minHeap = new PriorityQueue<>((x, y) -> y.number - x.number);
            for(int i = 0; i < nums.length; i++) {
                minHeap.offer(new NumberWithIndex(nums[i], i));
            }

            for(int rank = 1; rank <= n; rank++) {
                NumberWithIndex nwi = minHeap.poll();
                if(rank > 3) {
                    ranks[nwi.index] = String.valueOf(rank);
                } else if(rank == 3) {
                    ranks[nwi.index] = "Bronze Medal";
                } else if(rank == 2) {
                    ranks[nwi.index] = "Silver Medal";
                } else {
                    ranks[nwi.index] = "Gold Medal";
                }
            }

            return ranks;
        }

        class NumberWithIndex {
            int number;
            int index;

            public NumberWithIndex(int number, int index) {
                this.number = number;
                this.index = index;
            }
        }
    }
}

//    506. Relative Ranks
//    Easy
//    You are given an integer array score of size n, where score[i] is the score of the ith athlete in a competition. All the scores are guaranteed to be unique.
//
//    The athletes are placed based on their scores, where the 1st place athlete has the highest score, the 2nd place athlete has the 2nd highest score, and so on. The placement of each athlete determines their rank:
//
//    The 1st place athlete's rank is "Gold Medal".
//    The 2nd place athlete's rank is "Silver Medal".
//    The 3rd place athlete's rank is "Bronze Medal".
//    For the 4th place to the nth place athlete, their rank is their placement number (i.e., the xth place athlete's rank is "x").
//    Return an array answer of size n where answer[i] is the rank of the ith athlete.
//
//
//
//    Example 1:
//
//    Input: score = [5,4,3,2,1]
//    Output: ["Gold Medal","Silver Medal","Bronze Medal","4","5"]
//    Explanation: The placements are [1st, 2nd, 3rd, 4th, 5th].
//    Example 2:
//
//    Input: score = [10,3,8,9,4]
//    Output: ["Gold Medal","5","Bronze Medal","Silver Medal","4"]
//    Explanation: The placements are [1st, 5th, 3rd, 2nd, 4th].
//
//
//
//    Constraints:
//
//    n == score.length
//    1 <= n <= 104
//    0 <= score[i] <= 106
//    All the values in score are unique.