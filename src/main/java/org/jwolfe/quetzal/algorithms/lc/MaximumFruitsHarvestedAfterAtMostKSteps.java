package org.jwolfe.quetzal.algorithms.lc;

public class MaximumFruitsHarvestedAfterAtMostKSteps {
    class Solution {
        public int maxTotalFruits(int[][] fruits, int startPos, int k) {
            if (fruits == null || fruits.length == 0) {
                return 0;
            }

            int n = fruits.length;

            int[] prefixSums = new int[n + 1];
            int[] indices = new int[n];

            for (int i = 0; i < n; i++) {
                prefixSums[i + 1] = prefixSums[i] + fruits[i][1];
                indices[i] = fruits[i][0];
            }

            int maxFruitsGathered = 0;

            // x is the number of moves to one direction, before turning & moving in the other direction.
            // number of steps in the other direction is therefore (k - x)
            for (int x = 0; x <= k / 2; x++) {

                // Move left x steps, move right (k - x) steps
                int left = startPos - x;
                int right = (startPos - x) + (k - x);

                int start = lowerBound(indices, left);
                int end = upperBound(indices, right);
                int fruitsGathered = prefixSums[end] - prefixSums[start];
                maxFruitsGathered = Math.max(maxFruitsGathered, fruitsGathered);

                // Move right x steps, move left (k - x) steps
                right = startPos + x;
                left = (startPos + x) - (k - x);

                start = lowerBound(indices, left);
                end = upperBound(indices, right);
                fruitsGathered = prefixSums[end] - prefixSums[start];
                maxFruitsGathered = Math.max(maxFruitsGathered, fruitsGathered);
            }

            return maxFruitsGathered;
        }

        private int lowerBound(int[] indices, int val) {
            int left = 0;
            int right = indices.length - 1;
            int bound = right + 1;

            while (left <= right) {
                int mid = left + (right - left) / 2;

                if (indices[mid] >= val) {
                    bound = mid;
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }

            return bound;
        }

        private int upperBound(int[] indices, int val) {
            int left = 0;
            int right = indices.length - 1;
            int bound = right + 1;

            while (left <= right) {
                int mid = left + (right - left) / 2;

                if (indices[mid] > val) {
                    bound = mid;
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }

            return bound;
        }
    }
}

//    2106. Maximum Fruits Harvested After at Most K Steps
//    Hard
//    Fruits are available at some positions on an infinite x-axis. You are given a 2D integer array fruits where fruits[i] = [positioni, amounti] depicts amounti fruits at the position positioni. fruits is already sorted by positioni in ascending order, and each positioni is unique.
//
//    You are also given an integer startPos and an integer k. Initially, you are at the position startPos. From any position, you can either walk to the left or right. It takes one step to move one unit on the x-axis, and you can walk at most k steps in total. For every position you reach, you harvest all the fruits at that position, and the fruits will disappear from that position.
//
//    Return the maximum total number of fruits you can harvest.
//
//
//
//    Example 1:
//
//
//    Input: fruits = [[2,8],[6,3],[8,6]], startPos = 5, k = 4
//    Output: 9
//    Explanation:
//    The optimal way is to:
//    - Move right to position 6 and harvest 3 fruits
//    - Move right to position 8 and harvest 6 fruits
//    You moved 3 steps and harvested 3 + 6 = 9 fruits in total.
//    Example 2:
//
//
//    Input: fruits = [[0,9],[4,1],[5,7],[6,2],[7,4],[10,9]], startPos = 5, k = 4
//    Output: 14
//    Explanation:
//    You can move at most k = 4 steps, so you cannot reach position 0 nor 10.
//    The optimal way is to:
//    - Harvest the 7 fruits at the starting position 5
//    - Move left to position 4 and harvest 1 fruit
//    - Move right to position 6 and harvest 2 fruits
//    - Move right to position 7 and harvest 4 fruits
//    You moved 1 + 3 = 4 steps and harvested 7 + 1 + 2 + 4 = 14 fruits in total.
//    Example 3:
//
//
//    Input: fruits = [[0,3],[6,4],[8,5]], startPos = 3, k = 2
//    Output: 0
//    Explanation:
//    You can move at most k = 2 steps and cannot reach any position with fruits.
//
//
//    Constraints:
//
//    1 <= fruits.length <= 105
//    fruits[i].length == 2
//    0 <= startPos, positioni <= 2 * 105
//    positioni-1 < positioni for any i > 0 (0-indexed)
//    1 <= amounti <= 104
//    0 <= k <= 2 * 105