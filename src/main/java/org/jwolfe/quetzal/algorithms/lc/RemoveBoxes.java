package org.jwolfe.quetzal.algorithms.lc;

public class RemoveBoxes {
    class Solution {
        public int removeBoxes(int[] boxes) {
            if(boxes == null || boxes.length == 0) {
                return 0;
            }

            int n = boxes.length;
            return removeBoxes(boxes, 0, 0, n - 1, new Integer[n][n][n + 1]);
        }

        private int removeBoxes(int[] boxes, int streak, int left, int right, Integer[][][] memo) {
            if(left > right) {
                return 0;
            }

            if(memo[left][right][streak] != null) {
                return memo[left][right][streak];
            }

            // If we just break at this point. Streak += 1.
            int score = ((streak + 1) * (streak + 1)) + removeBoxes(boxes, 0, left + 1, right, memo);

            for(int k = left + 1; k <= right; k++) {
                if(boxes[k] == boxes[left]) {
                    // Consider a streak with the left & i. Middle & later elements have been removed before
                    score = Math.max(score,
                            removeBoxes(boxes, 0, left + 1, k - 1, memo) + removeBoxes(boxes, streak + 1, k, right, memo));
                }
            }

            memo[left][right][streak] = score;
            return score;
        }
    }


    class Solution_Recursive {
        public int removeBoxes(int[] boxes) {
            if(boxes == null || boxes.length == 0) {
                return 0;
            }

            int n = boxes.length;
            return removeBoxes(boxes, 0, 0, n - 1);
        }

        private int removeBoxes(int[] boxes, int streak, int left, int right) {
            if(left > right) {
                return 0;
            }

            // If we just break at this point. Streak += 1.
            int score = ((streak + 1) * (streak + 1)) + removeBoxes(boxes, 0, left + 1, right);

            for(int k = left + 1; k <= right; k++) {
                if(boxes[k] == boxes[left]) {
                    // Consider a streak with the left & i. Middle & later elements have been removed before
                    score = Math.max(score,
                            removeBoxes(boxes, 0, left + 1, k - 1) + removeBoxes(boxes, streak + 1, k, right));
                }
            }

            return score;
        }
    }

// [1,3,2,2,2,3,4,3,1]
}

//    546. Remove Boxes
//    Hard
//    You are given several boxes with different colors represented by different positive numbers.
//
//    You may experience several rounds to remove boxes until there is no box left. Each time you can choose some continuous boxes with the same color (i.e., composed of k boxes, k >= 1), remove them and get k * k points.
//
//    Return the maximum points you can get.
//
//
//
//    Example 1:
//
//    Input: boxes = [1,3,2,2,2,3,4,3,1]
//    Output: 23
//    Explanation:
//    [1, 3, 2, 2, 2, 3, 4, 3, 1]
//    ----> [1, 3, 3, 4, 3, 1] (3*3=9 points)
//    ----> [1, 3, 3, 3, 1] (1*1=1 points)
//    ----> [1, 1] (3*3=9 points)
//    ----> [] (2*2=4 points)
//    Example 2:
//
//    Input: boxes = [1,1,1]
//    Output: 9
//    Example 3:
//
//    Input: boxes = [1]
//    Output: 1
//
//
//    Constraints:
//
//    1 <= boxes.length <= 100
//    1 <= boxes[i] <= 100
