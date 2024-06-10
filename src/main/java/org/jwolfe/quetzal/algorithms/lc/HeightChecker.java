package org.jwolfe.quetzal.algorithms.lc;

import java.util.Arrays;

public class HeightChecker {
    class Solution {
        public int heightChecker(int[] heights) {
            if (heights == null || heights.length < 2) {
                return 0;
            }

            int[] expected = heights.clone();
            Arrays.sort(expected);

            int count = 0;
            for (int i = 0; i < heights.length; i++) {
                if (heights[i] != expected[i]) {
                    count++;
                }
            }

            return count;
        }
    }

    class Solution_Correct_1 {
        public int heightChecker(int[] heights) {
            if (heights == null || heights.length == 0) {
                return 0;
            }

            int[] target = heights.clone();
            Arrays.sort(target);

            int changes = 0;
            for (int i = 0; i < heights.length; i++) {
                if (heights[i] != target[i]) {
                    changes++;
                }
            }

            return changes;
        }
    }
}

//    1051. Height Checker
//    Easy
//    A school is trying to take an annual photo of all the students. The students are asked to stand in a single file line in non-decreasing order by height. Let this ordering be represented by the integer array expected where expected[i] is the expected height of the ith student in line.
//
//    You are given an integer array heights representing the current order that the students are standing in. Each heights[i] is the height of the ith student in line (0-indexed).
//
//    Return the number of indices where heights[i] != expected[i].
//
//
//
//    Example 1:
//
//    Input: heights = [1,1,4,2,1,3]
//    Output: 3
//    Explanation:
//    heights:  [1,1,4,2,1,3]
//    expected: [1,1,1,2,3,4]
//    Indices 2, 4, and 5 do not match.
//    Example 2:
//
//    Input: heights = [5,1,2,3,4]
//    Output: 5
//    Explanation:
//    heights:  [5,1,2,3,4]
//    expected: [1,2,3,4,5]
//    All indices do not match.
//    Example 3:
//
//    Input: heights = [1,2,3,4,5]
//    Output: 0
//    Explanation:
//    heights:  [1,2,3,4,5]
//    expected: [1,2,3,4,5]
//    All indices match.
//
//
//    Constraints:
//
//    1 <= heights.length <= 100
//    1 <= heights[i] <= 100