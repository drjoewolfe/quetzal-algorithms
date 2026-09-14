package org.jwolfe.quetzal.algorithms.lc;

public class RectangleOverlap {
    class Solution {
        public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
            int b1 = rec1[0];
            int l1 = rec1[1];
            int t1 = rec1[2];
            int r1 = rec1[3];

            int b2 = rec2[0];
            int l2 = rec2[1];
            int t2 = rec2[2];
            int r2 = rec2[3];

            if (t1 <= b2 || b1 >= t2 || l1 >= r2 || r1 <= l2) {
                return false;
            }

            return true;
        }
    }
}

//    836. Rectangle Overlap
//    Easy
//    An axis-aligned rectangle is represented as a list [x1, y1, x2, y2], where (x1, y1) is the coordinate of its bottom-left corner, and (x2, y2) is the coordinate of its top-right corner. Its top and bottom edges are parallel to the X-axis, and its left and right edges are parallel to the Y-axis.
//
//    Two rectangles overlap if the area of their intersection is positive. To be clear, two rectangles that only touch at the corner or edges do not overlap.
//
//    Given two axis-aligned rectangles rec1 and rec2, return true if they overlap, otherwise return false.
//
//
//
//    Example 1:
//
//    Input: rec1 = [0,0,2,2], rec2 = [1,1,3,3]
//    Output: true
//    Example 2:
//
//    Input: rec1 = [0,0,1,1], rec2 = [1,0,2,1]
//    Output: false
//    Example 3:
//
//    Input: rec1 = [0,0,1,1], rec2 = [2,2,3,3]
//    Output: false
//
//
//    Constraints:
//
//    rec1.length == 4
//    rec2.length == 4
//    -109 <= rec1[i], rec2[i] <= 109
//    rec1 and rec2 represent a valid rectangle with a non-zero area.