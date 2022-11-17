package org.jwolfe.quetzal.algorithms.lc;

public class RectangleArea {
    class Solution {
        public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
            long area1 = 1L * (C - A) * (D - B);
            long area2 = 1L * (G - E) * (H - F);

            long overlap = Math.max(Math.min(G, C) - Math.max(E, A), 0)
                    * Math.max(Math.min(H, D) - Math.max(F, B), 0);

            return (int) (area1 + area2 - overlap);
        }
    }

    class Solution_Correct_1 {
        public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
            long area1 = 1L * (C - A) * (D - B);
            long area2 = 1L * (G - E) * (H - F);

            long overlap = Math.max(Math.min(G, C) - Math.max(E, A), 0)
                    * Math.max(Math.min(H, D) - Math.max(F, B), 0);

            return (int) (area1 + area2 - overlap);
        }
    }
}

//    223. Rectangle Area
//    Medium
//    Given the coordinates of two rectilinear rectangles in a 2D plane, return the total area covered by the two rectangles.
//
//    The first rectangle is defined by its bottom-left corner (ax1, ay1) and its top-right corner (ax2, ay2).
//
//    The second rectangle is defined by its bottom-left corner (bx1, by1) and its top-right corner (bx2, by2).
//
//
//
//    Example 1:
//
//    Rectangle Area
//    Input: ax1 = -3, ay1 = 0, ax2 = 3, ay2 = 4, bx1 = 0, by1 = -1, bx2 = 9, by2 = 2
//    Output: 45
//    Example 2:
//
//    Input: ax1 = -2, ay1 = -2, ax2 = 2, ay2 = 2, bx1 = -2, by1 = -2, bx2 = 2, by2 = 2
//    Output: 16
//
//
//    Constraints:
//
//    -104 <= ax1 <= ax2 <= 104
//    -104 <= ay1 <= ay2 <= 104
//    -104 <= bx1 <= bx2 <= 104
//    -104 <= by1 <= by2 <= 104