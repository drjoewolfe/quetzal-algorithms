package org.jwolfe.quetzal.algorithms.lc;

import java.util.PriorityQueue;

public class MaximalScoreAfterApplyingKOperations {
    class Solution {
        public long maxKelements(int[] nums, int k) {
            if(nums == null || k == 0 || nums.length == 0) {
                return 0;
            }

            PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> a < b ? 1 : a == b ? 0 : -1);
            for(int a : nums) {
                maxHeap.offer(a);
            }

            long score = 0;
            for(int i = 0; i < k; i++) {
                int val = maxHeap.poll();
                score += val;

                if(val % 3 == 0) {
                    val /= 3;
                } else {
                    val /= 3;
                    val += 1;
                }

                maxHeap.offer(val);
            }

            return score;
        }
    }

// [10,10,10,10,10]
// 5

// [822539285,410243778,843804474,77808436,604773488,91882183,983148575,707291538,210570310,256880418,744202637,272026597,850035362,815964585,161539308,807997984,975169847,373595653,740848840,596578711,634140518,805359728,893113934,983095872,740000028,424404126,645488102,398147052,399134531,455173503,762937622,135977920,886567539,210644714,441883025,440353920,151652262,540667241,41680120,612170826,757875616,630417303,829178537,765944292,440167660,811916049,764614502,101017579,296944070,726251399]
// 53
}

//    2530. Maximal Score After Applying K Operations
//    Medium
//    You are given a 0-indexed integer array nums and an integer k. You have a starting score of 0.
//
//    In one operation:
//
//    choose an index i such that 0 <= i < nums.length,
//    increase your score by nums[i], and
//    replace nums[i] with ceil(nums[i] / 3).
//    Return the maximum possible score you can attain after applying exactly k operations.
//
//    The ceiling function ceil(val) is the least integer greater than or equal to val.
//
//
//
//    Example 1:
//
//    Input: nums = [10,10,10,10,10], k = 5
//    Output: 50
//    Explanation: Apply the operation to each array element exactly once. The final score is 10 + 10 + 10 + 10 + 10 = 50.
//    Example 2:
//
//    Input: nums = [1,10,3,3,3], k = 3
//    Output: 17
//    Explanation: You can do the following operations:
//    Operation 1: Select i = 1, so nums becomes [1,4,3,3,3]. Your score increases by 10.
//    Operation 2: Select i = 1, so nums becomes [1,2,3,3,3]. Your score increases by 4.
//    Operation 3: Select i = 2, so nums becomes [1,1,1,3,3]. Your score increases by 3.
//    The final score is 10 + 4 + 3 = 17.
//
//
//    Constraints:
//
//    1 <= nums.length, k <= 105
//    1 <= nums[i] <= 109