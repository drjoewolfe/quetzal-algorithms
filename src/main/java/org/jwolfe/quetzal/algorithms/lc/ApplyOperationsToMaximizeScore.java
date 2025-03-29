package org.jwolfe.quetzal.algorithms.lc;

import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;

public class ApplyOperationsToMaximizeScore {
    class Solution {
        private int MOD = 1_000_000_007;

        public int maximumScore(List<Integer> nums, int k) {
            if (nums == null || nums.size() == 0 || k < 1) {
                return 0;
            }

            int n = nums.size();

            int[] primeScores = new int[n];
            for (int i = 0; i < n; i++) {
                primeScores[i] = getPrimeFactorCount(nums.get(i));
            }

            int[] nextDominant = new int[n];
            int[] prevDominant = new int[n];

            Arrays.fill(nextDominant, n);
            Arrays.fill(prevDominant, -1);

            Stack<Integer> decreasingPrimeScoreStack = new Stack<>();
            for (int i = 0; i < n; i++) {
                while (!decreasingPrimeScoreStack.isEmpty()
                        && primeScores[decreasingPrimeScoreStack.peek()] < primeScores[i]) {
                    int index = decreasingPrimeScoreStack.pop();
                    nextDominant[index] = i;
                }

                if (!decreasingPrimeScoreStack.isEmpty()) {
                    prevDominant[i] = decreasingPrimeScoreStack.peek();
                }

                decreasingPrimeScoreStack.push(i);
            }

            long[] subArrayCount = new long[n];
            for (int i = 0; i < n; i++) {
                int left = prevDominant[i];
                int right = nextDominant[i];

                subArrayCount[i] = ((long) right - i) * (i - left);
            }

            long score = 1;

            PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> {
                if (a[0] == b[0]) {
                    return a[1] - b[1];
                }

                return b[0] - a[0];
            });

            for (int i = 0; i < n; i++) {
                heap.offer(new int[]{nums.get(i), i});
            }

            while (k > 0) {
                int[] element = heap.poll();
                int val = element[0];
                int index = element[1];

                long operations = Math.min((long) k, subArrayCount[index]);
                score = (score * power(val, operations)) % MOD;

                k -= operations;
            }

            return (int) score;
        }

        private int getPrimeFactorCount(int num) {
            int primeFactorCount = 0;
            for (int factor = 2; factor <= Math.sqrt(num); factor++) {
                if (num % factor == 0) {
                    primeFactorCount++;

                    while (num % factor == 0) {
                        num /= factor;
                    }
                }
            }

            if (num != 1) {
                primeFactorCount++;
            }

            return primeFactorCount;
        }

        private long power(long base, long exponent) {
            long ans = 1;

            while (exponent > 0) {
                if (exponent % 2 == 1) {
                    ans = (ans * base) % MOD;
                }

                base = (base * base) % MOD;
                exponent /= 2;
            }

            return ans;
        }
    }
}

//    2818. Apply Operations to Maximize Score
//    Hard
//    You are given an array nums of n positive integers and an integer k.
//
//    Initially, you start with a score of 1. You have to maximize your score by applying the following operation at most k times:
//
//    Choose any non-empty subarray nums[l, ..., r] that you haven't chosen previously.
//    Choose an element x of nums[l, ..., r] with the highest prime score. If multiple such elements exist, choose the one with the smallest index.
//    Multiply your score by x.
//    Here, nums[l, ..., r] denotes the subarray of nums starting at index l and ending at the index r, both ends being inclusive.
//
//    The prime score of an integer x is equal to the number of distinct prime factors of x. For example, the prime score of 300 is 3 since 300 = 2 * 2 * 3 * 5 * 5.
//
//    Return the maximum possible score after applying at most k operations.
//
//    Since the answer may be large, return it modulo 109 + 7.
//
//
//
//    Example 1:
//
//    Input: nums = [8,3,9,3,8], k = 2
//    Output: 81
//    Explanation: To get a score of 81, we can apply the following operations:
//    - Choose subarray nums[2, ..., 2]. nums[2] is the only element in this subarray. Hence, we multiply the score by nums[2]. The score becomes 1 * 9 = 9.
//    - Choose subarray nums[2, ..., 3]. Both nums[2] and nums[3] have a prime score of 1, but nums[2] has the smaller index. Hence, we multiply the score by nums[2]. The score becomes 9 * 9 = 81.
//    It can be proven that 81 is the highest score one can obtain.
//    Example 2:
//
//    Input: nums = [19,12,14,6,10,18], k = 3
//    Output: 4788
//    Explanation: To get a score of 4788, we can apply the following operations:
//    - Choose subarray nums[0, ..., 0]. nums[0] is the only element in this subarray. Hence, we multiply the score by nums[0]. The score becomes 1 * 19 = 19.
//    - Choose subarray nums[5, ..., 5]. nums[5] is the only element in this subarray. Hence, we multiply the score by nums[5]. The score becomes 19 * 18 = 342.
//    - Choose subarray nums[2, ..., 3]. Both nums[2] and nums[3] have a prime score of 2, but nums[2] has the smaller index. Hence, we multipy the score by nums[2]. The score becomes 342 * 14 = 4788.
//    It can be proven that 4788 is the highest score one can obtain.
//
//
//    Constraints:
//
//    1 <= nums.length == n <= 105
//    1 <= nums[i] <= 105
//    1 <= k <= min(n * (n + 1) / 2, 109)