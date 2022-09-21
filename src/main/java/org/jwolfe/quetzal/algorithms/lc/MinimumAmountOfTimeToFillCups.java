package org.jwolfe.quetzal.algorithms.lc;

import java.util.PriorityQueue;

public class MinimumAmountOfTimeToFillCups {
    class Solution {
        public int fillCups(int[] amount) {
            if(amount == null || amount.length != 3) {
                return 0;
            }

            PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);
            if(amount[0] > 0) {
                maxHeap.offer(amount[0]);
            }

            if(amount[1] > 0) {
                maxHeap.offer(amount[1]);
            }

            if(amount[2] > 0) {
                maxHeap.offer(amount[2]);
            }

            int seconds = 0;
            while(!maxHeap.isEmpty()) {
                int max = maxHeap.poll();
                int secondMax = !maxHeap.isEmpty() ? maxHeap.poll() : 0;

                max--;
                if(max > 0) {
                    maxHeap.offer(max);
                }

                secondMax--;
                if(secondMax > 0) {
                    maxHeap.offer(secondMax);
                }

                seconds++;
            }

            return seconds;
        }
    }
}

//    2335. Minimum Amount of Time to Fill Cups
//    Easy
//    You have a water dispenser that can dispense cold, warm, and hot water. Every second, you can either fill up 2 cups with different types of water, or 1 cup of any type of water.
//
//    You are given a 0-indexed integer array amount of length 3 where amount[0], amount[1], and amount[2] denote the number of cold, warm, and hot water cups you need to fill respectively. Return the minimum number of seconds needed to fill up all the cups.
//
//
//
//    Example 1:
//
//    Input: amount = [1,4,2]
//    Output: 4
//    Explanation: One way to fill up the cups is:
//    Second 1: Fill up a cold cup and a warm cup.
//    Second 2: Fill up a warm cup and a hot cup.
//    Second 3: Fill up a warm cup and a hot cup.
//    Second 4: Fill up a warm cup.
//    It can be proven that 4 is the minimum number of seconds needed.
//    Example 2:
//
//    Input: amount = [5,4,4]
//    Output: 7
//    Explanation: One way to fill up the cups is:
//    Second 1: Fill up a cold cup, and a hot cup.
//    Second 2: Fill up a cold cup, and a warm cup.
//    Second 3: Fill up a cold cup, and a warm cup.
//    Second 4: Fill up a warm cup, and a hot cup.
//    Second 5: Fill up a cold cup, and a hot cup.
//    Second 6: Fill up a cold cup, and a warm cup.
//    Second 7: Fill up a hot cup.
//    Example 3:
//
//    Input: amount = [5,0,0]
//    Output: 5
//    Explanation: Every second, we fill up a cold cup.
//
//
//    Constraints:
//
//    amount.length == 3
//    0 <= amount[i] <= 100
