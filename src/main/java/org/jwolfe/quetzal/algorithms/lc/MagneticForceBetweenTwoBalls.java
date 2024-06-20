package org.jwolfe.quetzal.algorithms.lc;

import java.util.Arrays;

public class MagneticForceBetweenTwoBalls {
    class Solution {
        public int maxDistance(int[] position, int m) {
            if (m < 1 || position == null || position.length < m) {
                return 0;
            }

            Arrays.sort(position);

            int n = position.length;
            int left = position[0];
            int right = position[n - 1];

            int maxGap = 0;
            while (left <= right) {
                int mid = left + (right - left) / 2;
                int gap = mid - position[0];

                if (canPlaceAllBalls(position, m, gap)) {
                    maxGap = gap;
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }

            return maxGap;
        }

        private boolean canPlaceAllBalls(int[] position, int m, int gap) {
            int left = position[0];
            m--;

            for (int i = 0; i < position.length && m > 0; i++) {
                int pos = position[i];

                if (left + gap > pos) {
                    continue;
                } else {
                    m--;
                    left = pos;

                    if (m == 0) {
                        break;
                    }
                }
            }

            return m == 0;
        }
    }

    class Solution_Correct_1 {
        public int maxDistance(int[] position, int m) {
            if (position == null || position.length < 2 || position.length < m) {
                return 0;
            }

            Arrays.sort(position);
            int n = position.length;

            int left = position[0];
            int right = position[n - 1];

            int maxGap = 0;
            while (left <= right) {
                int mid = left + (right - left) / 2;
                int gap = mid - position[0];
                if (canPlaceAllBalls(position, n, m, gap)) {
                    maxGap = gap;
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }

            return maxGap;
        }

        private boolean canPlaceAllBalls(int[] position, int n, int m, int gap) {
            int left = position[0];
            m--;

            for (int i = 1; i < n && m > 0; i++) {
                if (left + gap > position[i]) {
                    continue;
                }

                m--;
                left = position[i];
            }

            return m == 0;
        }
    }

// [5,4,3,2,1,1000000000]
// 2

// [1,2,3,4,7]
// 3

// [1,2,3,4,5,6,7,8,9,10]
// 4
}

//    1552. Magnetic Force Between Two Balls
//    Medium
//    In the universe Earth C-137, Rick discovered a special form of magnetic force between two balls if they are put in his new invented basket. Rick has n empty baskets, the ith basket is at position[i], Morty has m balls and needs to distribute the balls into the baskets such that the minimum magnetic force between any two balls is maximum.
//
//    Rick stated that magnetic force between two different balls at positions x and y is |x - y|.
//
//    Given the integer array position and the integer m. Return the required force.
//
//
//
//    Example 1:
//
//
//    Input: position = [1,2,3,4,7], m = 3
//    Output: 3
//    Explanation: Distributing the 3 balls into baskets 1, 4 and 7 will make the magnetic force between ball pairs [3, 3, 6]. The minimum magnetic force is 3. We cannot achieve a larger minimum magnetic force than 3.
//    Example 2:
//
//    Input: position = [5,4,3,2,1,1000000000], m = 2
//    Output: 999999999
//    Explanation: We can use baskets 1 and 1000000000.
//
//
//    Constraints:
//
//    n == position.length
//    2 <= n <= 105
//    1 <= position[i] <= 109
//    All integers in position are distinct.
//    2 <= m <= position.length